package com.sdc.webdev.controller;

import com.sdc.webdev.dao.PhoneNumberDAO;
import com.sdc.webdev.dao.PhoneNumberDAOImpl;
import com.sdc.webdev.model.PhoneNumber;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/phonebook")
public class PhoneBookServlet extends HttpServlet {

    private PhoneNumberDAO phoneNumberDAO = new PhoneNumberDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PhoneNumber> phoneNumbers = phoneNumberDAO.getAllPhoneNumbers();
        request.setAttribute("phoneNumbers", phoneNumbers);
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String surname = request.getParameter("surname");

        // Validation
        if (phoneNumber == null || surname == null || phoneNumber.isEmpty() || surname.isEmpty() ||
                !Pattern.matches("^[+]?[0-9]{10,13}$", phoneNumber) || !Pattern.matches("^[a-zA-Z\\s]+$", surname)) {
            request.setAttribute("error", "Invalid phone number or surname. Ensure correct format.");
            request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
            return;
        }

        phoneNumberDAO.addPhoneNumber(new PhoneNumber(phoneNumber, surname));
        request.setAttribute("success", "Phone number added successfully.");
        response.sendRedirect(request.getContextPath() + "/phonebook"); // Redirect to avoid re-posting form data
    }
}