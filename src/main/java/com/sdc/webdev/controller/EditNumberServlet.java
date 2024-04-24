package com.sdc.webdev.controller;

import com.sdc.webdev.dao.PhoneNumberDAO;
import com.sdc.webdev.dao.PhoneNumberDAOImpl;
import com.sdc.webdev.model.PhoneNumber;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/editNumber")
public class EditNumberServlet extends HttpServlet {
    private PhoneNumberDAO phoneNumberDAO;

    @Override
    public void init() {
        phoneNumberDAO = new PhoneNumberDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String number = request.getParameter("Number");
        String surname = request.getParameter("surname");  // This will be used only for update

        try {
            switch (action) {
                case "update":
                    updateNumber(number, surname, request, response);
                    break;
                case "delete":
                    deleteNumber(number, request, response);
                    break;
                default:
                    request.setAttribute("error", "Invalid action");
                    request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
                    return;
            }
        } catch (Exception e) {
            throw new ServletException("Error handling phone number operations", e);
        }
    }

    private void updateNumber(String number, String surname, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (phoneNumberExists(number)) {
            phoneNumberDAO.updatePhoneNumber(new PhoneNumber(number, surname));
            response.sendRedirect(request.getContextPath() + "/phonebook");
        } else {
            request.setAttribute("error", "There is no such number to update.");
            request.getRequestDispatcher("/WEB-INF/views/editNumber.jsp").forward(request, response);
        }
    }

    private void deleteNumber(String number, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (phoneNumberExists(number)) {
            phoneNumberDAO.deletePhoneNumber(number);
            response.sendRedirect(request.getContextPath() + "/phonebook");
        } else {
            request.setAttribute("error", "There is no such number to delete.");
            request.getRequestDispatcher("/WEB-INF/views/editNumber.jsp").forward(request, response);
        }
    }

    private boolean phoneNumberExists(String number) {
        List<PhoneNumber> phoneNumbers = phoneNumberDAO.getAllPhoneNumbers();
        for (PhoneNumber pn : phoneNumbers) {
            if (pn.getPhoneNumber().equals(number)) {
                return true;
            }
        }
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This method could set up the edit form, but for now, just forward to the form
        request.getRequestDispatcher("/WEB-INF/views/editNumber.jsp").forward(request, response);
    }
}
