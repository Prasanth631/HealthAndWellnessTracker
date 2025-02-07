package com.healthwellness.controller;

import com.healthwellness.dao.UserDAO;
import com.healthwellness.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User newUser = new User(username, password, email, "USER");

        try {
            boolean isRegistered = userDAO.registerUser(newUser);
            if (isRegistered) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed. Please try again. " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
