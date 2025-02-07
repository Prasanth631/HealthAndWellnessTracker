<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Health and Wellness Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
    <div class="background">
        <span></span><span></span><span></span><span></span><span></span>
        <span></span><span></span><span></span><span></span><span></span>
    </div>
    <div class="container">
        <main>
            <h2 class="title">Register</h2>
            <form action="${pageContext.request.contextPath}/register" method="post" class="register-form">
                <div class="input-group">
                    <input type="text" name="username" required>
                    <label>Username</label>
                    <span class="highlight"></span>
                </div>
                <div class="input-group">
                    <input type="password" name="password" required>
                    <label>Password</label>
                    <span class="highlight"></span>
                </div>
                <div class="input-group">
                    <input type="email" name="email" required>
                    <label>Email</label>
                    <span class="highlight"></span>
                </div>
                <button type="submit" class="submit-btn">Register</button>
            </form>
            <p class="login-link">Already have an account? <a href="${pageContext.request.contextPath}/login.jsp">Login here</a></p>
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