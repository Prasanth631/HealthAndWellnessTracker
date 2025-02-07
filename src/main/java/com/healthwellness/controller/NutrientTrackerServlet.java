package com.healthwellness.controller;

import com.healthwellness.dao.NutrientIntakeDAO;
import com.healthwellness.model.NutrientIntake;
import com.healthwellness.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/nutrient-tracker")
public class NutrientTrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NutrientIntakeDAO nutrientIntakeDAO = new NutrientIntakeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<NutrientIntake> nutrientIntakes = nutrientIntakeDAO.getNutrientIntakeByUserId(user.getId());
            request.setAttribute("nutrientIntakes", nutrientIntakes);
            request.getRequestDispatcher("nutrient-tracker.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String foodItem = request.getParameter("foodItem");
        int calories = Integer.parseInt(request.getParameter("calories"));
        float protein = Float.parseFloat(request.getParameter("protein"));
        float carbs = Float.parseFloat(request.getParameter("carbs"));
        float fat = Float.parseFloat(request.getParameter("fat"));
        Date intakeDate = Date.valueOf(request.getParameter("intakeDate"));

        NutrientIntake nutrientIntake = new NutrientIntake(user.getId(), foodItem, calories, protein, carbs, fat, intakeDate);

        try {
            nutrientIntakeDAO.saveNutrientIntake(nutrientIntake);
            response.sendRedirect("nutrient-tracker");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}