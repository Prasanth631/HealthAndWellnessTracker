<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BMI Calculator</title>
    <link rel="stylesheet" href="css/bmi-calculator.css">
</head>
<body>
    <div class="stars"></div>
    <div class="twinkling"></div>
    <div class="container">
        <h1>BMI Calculator</h1>
        <h2>Calculate Your Body Mass Index</h2>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color: red;">${error}</p>
        <% } %>
        <div class="bmi-form">
            <form action="${pageContext.request.contextPath}/bmi-calculator" method="post">
                <input type="hidden" name="userId" value="${sessionScope.userId}">
                <div class="form-group">
                    <label for="weight">Weight (kg):</label>
                    <input type="number" id="weight" name="weight" required step="0.1">
                </div>
                <div class="form-group">
                    <label for="height">Height (cm):</label>
                    <input type="number" id="height" name="height" required step="0.01">
                </div>
                <button type="submit" class="button">Calculate BMI</button>
            </form>
        </div>
    </div>
    <footer>
        <p></p>
    </footer>
</body>
</html>