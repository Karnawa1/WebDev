package com.sdc.webdev.controller;

import com.sdc.webdev.dao.UserDAO;
import com.sdc.webdev.dao.UserDAOImpl;
import com.sdc.webdev.model.User;
import com.sdc.webdev.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Basic input validation
        if (username == null || username.isEmpty() || username.length() < 5 || username.length() > 20) {
            request.setAttribute("error", "Username must be between 5 and 20 characters long.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        if (password == null || password.isEmpty() || password.length() < 8) {
            request.setAttribute("error", "Password must be at least 8 characters long.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        try {
            String hashedPassword = PasswordUtil.hashPassword(password);
            User newUser = new User(username, hashedPassword);
            userDAO.registerUser(newUser);
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("error", "Error hashing password: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }
}
