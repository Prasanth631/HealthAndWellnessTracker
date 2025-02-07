<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BMI Result</title>
    <link rel="stylesheet" href="css/bmi-result.css">
</head>
<body>
    <div class="stars"></div>
    <div class="twinkling"></div>
    <div class="container">
        <h1>Your BMI Result</h1>
        <p class="result">Your BMI is: <span class="value">${bmi.bmiValue}</span></p>
        <p class="category">BMI Category: <span class="value">${bmi.bmiCategory}</span></p>
        <a href="${pageContext.request.contextPath}/diet-recommendation?bmiCategory=${bmi.bmiCategory}" class="button">Get Diet Recommendations</a>
    </div>
</body>
</html>