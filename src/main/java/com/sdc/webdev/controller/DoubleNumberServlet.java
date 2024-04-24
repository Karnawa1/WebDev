package com.sdc.webdev.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DoubleNumberServlet", urlPatterns = {"/doubleNumber"})
public class DoubleNumberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String numberStr = request.getParameter("number");
        if (numberStr == null || numberStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Number is required");
            return;
        }

        try {
            int number = Integer.parseInt(numberStr.trim());

            // Additional validation can go here, e.g., check if number is non-negative or within a specific range
            if (number < 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Number must be non-negative");
                return;
            }

            int doubledNumber = number * 2;

            // Set attributes in the request scope
            request.setAttribute("originalNumber", number);
            request.setAttribute("doubledNumber", doubledNumber);

            // Forward request to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/results.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        }
    }
}
