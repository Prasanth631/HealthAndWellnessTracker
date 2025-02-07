<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Health and Wellness Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
</head>
<body>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    <div class="container">
        <header>
            <h2 class="welcome">Welcome, <span class="username">${user.username}</span>!</h2>
        </header>
        <main>
            <nav class="dashboard-nav">
                <ul>
                    <li>
                        <a href="bmi-calculator.jsp" class="nav-item">
                            <i class="fas fa-calculator"></i>
                            <span>BMI Calculator</span>
                        </a>
                    </li>
                    <li>
                        <a href="diet-recommendation" class="nav-item">
                            <i class="fas fa-utensils"></i>
                            <span>Diet Recommendations</span>
                        </a>
                    </li>
                    <li>
                        <a href="activity-tracker" class="nav-item">
                            <i class="fas fa-running"></i>
                            <span>Activity Tracker</span>
                        </a>
                    </li>
                    <li>
                        <a href="nutrient-tracker" class="nav-item">
                            <i class="fas fa-apple-alt"></i>
                            <span>Nutrient Tracker</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="logout">
                <form action="logout" method="post">
                    <button type="submit" class="logout-btn">
                        <i class="fas fa-sign-out-alt"></i>
                        Logout
                    </button>
                </form>
            </div>
        </main>
    </div>
</body>
</html>