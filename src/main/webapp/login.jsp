<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Health and Wellness Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<div class="stars"></div>
    <div class="twinkling"></div>
    <div class="container">
        <main>
            <h2 class="title">Login</h2>
            <form action="login" method="post" class="login-form">
                <div class="input-group">
                    <input type="text" name="username" required>
                    <label>Username</label>
                </div>
                <div class="input-group">
                    <input type="password" name="password" required>
                    <label>Password</label>
                </div>
                <button type="submit" class="login-btn">Login</button>
            </form>
            <p class="register-link">Don't have an account? <a href="register.jsp">Register here</a></p>
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <p class="error"><%= error %></p>
            <%
                }
            %>
        </main>
    </div>
</body>
</html>