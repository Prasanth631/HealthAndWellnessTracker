<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nutrient Tracker - Health and Wellness Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nutrient-tracker.css">
</head>
<body>
    <div class="container">
        <main>
            <h2 class="title">Nutrient Tracker</h2>
            <form action="nutrient-tracker" method="post" class="nutrient-form">
                <div class="input-group">
                    <input type="text" name="foodItem" id="foodItem" placeholder=" " required>
                    <label for="foodItem">Food Item</label>
                </div>
                <div class="input-group">
                    <input type="number" name="calories" id="calories" placeholder=" " required>
                    <label for="calories">Calories</label>
                </div>
                <div class="input-group">
                    <input type="number" name="protein" id="protein" placeholder=" " required step="0.1">
                    <label for="protein">Protein (g)</label>
                </div>
                <div class="input-group">
                    <input type="number" name="carbs" id="carbs" placeholder=" " required step="0.1">
                    <label for="carbs">Carbs (g)</label>
                </div>
                <div class="input-group">
                    <input type="number" name="fat" id="fat" placeholder=" " required step="0.1">
                    <label for="fat">Fat (g)</label>
                </div>
                <div class="input-group">
                    <input type="date" name="intakeDate" id="intakeDate" required>
                    <label for="intakeDate">Date</label>
                </div>
                <button type="submit" class="submit-btn">Add Nutrient Intake</button>
            </form>
            <h3 class="subtitle">Your Nutrient Intake</h3>
            <div class="table-container">
                <table class="result">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Food Item</th>
                            <th>Calories</th>
                            <th>Protein</th>
                            <th>Carbs</th>
                            <th>Fat</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${nutrientIntakes}" var="intake">
                            <tr>
                                <td>${intake.intakeDate}</td>
                                <td>${intake.foodItem}</td>
                                <td>${intake.calories}</td>
                                <td>${intake.protein}g</td>
                                <td>${intake.carbs}g</td>
                                <td>${intake.fat}g</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
        </main>
    </div>
</body>
</html>