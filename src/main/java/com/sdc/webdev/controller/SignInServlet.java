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

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && PasswordUtil.hashPassword(password).equals(user.getPassword())) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ServletException("Error hashing password", e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(request, response);
    }
}
