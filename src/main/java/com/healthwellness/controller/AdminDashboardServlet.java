package com.healthwellness.controller;

import com.healthwellness.dao.*;
import com.healthwellness.model.*;
import com.healthwellness.util.ChartGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();
    private ActivityDAO activityDAO = new ActivityDAO();
    private NutrientIntakeDAO nutrientIntakeDAO = new NutrientIntakeDAO();
    private SystemConfigDAO systemConfigDAO = new SystemConfigDAO();
    private NotificationDAO notificationDAO = new NotificationDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.isAdmin()) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "dashboard";
        }

        try {
            switch (action) {
                case "dashboard":
                    showDashboard(request, response);
                    break;
                case "userDetails":
                    showUserDetails(request, response);
                    break;
                case "systemConfig":
                    showSystemConfig(request, response);
                    break;
                case "notifications":
                    showNotifications(request, response);
                    break;
                default:
                    showDashboard(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "deleteUser":
                    deleteUser(request, response);
                    break;
                case "toggleAdmin":
                    toggleAdminStatus(request, response);
                    break;
                case "updateConfig":
                    updateSystemConfig(request, response);
                    break;
                case "sendNotification":
                    sendNotification(request, response);
                    break;
                default:
                    response.sendRedirect("admin-dashboard");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<User> allUsers = userDAO.getAllUsers();
        Map<String, Integer> userStatistics = userDAO.getUserStatistics();
        Map<String, Double> nutrientStatistics = nutrientIntakeDAO.getNutrientStatistics();

        String userChartUrl = ChartGenerator.generateUserChart(userStatistics);
        String nutrientChartUrl = ChartGenerator.generateNutrientChart(nutrientStatistics);

        request.setAttribute("allUsers", allUsers);
        request.setAttribute("userStatistics", userStatistics);
        request.setAttribute("nutrientStatistics", nutrientStatistics);
        request.setAttribute("userChartUrl", userChartUrl);
        request.setAttribute("nutrientChartUrl", nutrientChartUrl);

        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }

    private void showUserDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userDAO.getUserById(userId);
        List<Activity> activities = activityDAO.getActivitiesByUserId(userId);
        List<NutrientIntake> nutrientIntakes = nutrientIntakeDAO.getNutrientIntakeByUserId(userId);

        request.setAttribute("user", user);
        request.setAttribute("activities", activities);
        request.setAttribute("nutrientIntakes", nutrientIntakes);

        request.getRequestDispatcher("user-details.jsp").forward(request, response);
    }

    private void showSystemConfig(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Map<String, String> config = systemConfigDAO.getAllConfigurations();
        request.setAttribute("config", config);
        request.getRequestDispatcher("system-config.jsp").forward(request, response);
    }

    private void showNotifications(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Notification> notifications = notificationDAO.getAllNotifications();
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("notifications.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        userDAO.deleteUser(userId);
        response.sendRedirect("admin-dashboard");
    }

    private void toggleAdminStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userDAO.getUserById(userId);
        user.setRole(user.getRole().equals("ADMIN") ? "USER" : "ADMIN");
        userDAO.updateUser(user);
        response.sendRedirect("admin-dashboard");
    }

    private void updateSystemConfig(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        systemConfigDAO.updateConfiguration(key, value);
        response.sendRedirect("admin-dashboard?action=systemConfig");
    }

    private void sendNotification(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String message = request.getParameter("message");
        String recipientType = request.getParameter("recipientType");
        notificationDAO.createNotification(message, recipientType);
        response.sendRedirect("admin-dashboard?action=notifications");
    }
}
