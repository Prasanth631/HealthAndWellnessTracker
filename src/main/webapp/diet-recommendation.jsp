<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Diet Recommendations</title>
    <link rel="stylesheet" href="css/diet-recommendation.css">
    <link rel="stylesheet" href="css/rainbow-button.css">
</head>
<body>
    <div class="stars"></div>
    <div class="twinkling"></div>
    <div class="container">
        <h1>Diet Recommendations for ${recommendation.bmiCategory}</h1>
        <div class="recommendation">
            <h2>Breakfast</h2>
            <p>${recommendation.breakfast}</p>
        </div>
        <div class="recommendation">
            <h2>Lunch</h2>
            <p>${recommendation.lunch}</p>
        </div>
        <div class="recommendation">
            <h2>Dinner</h2>
            <p>${recommendation.dinner}</p>
        </div>
        <a href="dashboard.jsp" class="rainbow-button">Back to Dashboard</a>
    </div>
</body>
</html>