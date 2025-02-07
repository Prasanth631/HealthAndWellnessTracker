package com.healthwellness.controller;

import com.healthwellness.dao.ActivityDAO;
import com.healthwellness.model.Activity;
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

@WebServlet("/activity-tracker")
public class ActivityTrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ActivityDAO activityDAO = new ActivityDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Activity> activities = activityDAO.getActivitiesByUserId(user.getId());
            request.setAttribute("activities", activities);
            request.getRequestDispatcher("activity-tracker.jsp").forward(request, response);
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

        String activityType = request.getParameter("activityType");
        int duration = Integer.parseInt(request.getParameter("duration"));
        int caloriesBurned = Integer.parseInt(request.getParameter("caloriesBurned"));
        Date activityDate = Date.valueOf(request.getParameter("activityDate"));

        Activity activity = new Activity(user.getId(), activityType, duration, caloriesBurned, activityDate);

        try {
            activityDAO.saveActivity(activity);
            response.sendRedirect("activity-tracker");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
