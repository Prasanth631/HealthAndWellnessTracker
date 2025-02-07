<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Health and Wellness Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
    <div class="container">
        <header>
            <nav class="top-nav">
                <div class="auth-buttons">
                    <a href="login.jsp" class="button login-btn">Login</a>
                    <a href="register.jsp" class="button register-btn">Register</a>
                </div>
            </nav>
        </header>
        <main>
            <h1 class="title">Welcome to Health and Wellness Tracker</h1>
            <p class="subtitle">Track your health,x fitness, and nutrition goals with our easy-to-use platform.</p>
            <div class="animated-icons">
                <div class="icon heart"></div>
                <div class="icon apple"></div>
                <div class="icon dumbbell"></div>
            </div>
        </main>
    </div>
</body>
</html>