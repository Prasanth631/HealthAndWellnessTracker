<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
    <link rel="stylesheet" href="css/admin-dashboard.css">
</head>
<body>
    <header>
        <h1>User Details</h1>
        <nav>
            <a href="admin-dashboard">Back to Dashboard</a>
        </nav>
    </header>

    <main>
        <section id="user-info">
            <h2>User Information</h2>
            <p>ID: ${user.id}</p>
            <p>Username: ${user.username}</p>
            <p>Email: ${user.email}</p>
            <p>Role: ${user.role}</p>
        </section>

        <section id="user-activities">
            <h2>User Activities</h2>
            <table>
                <thead>
                    <tr>
                        <th>Activity Type</th>
                        <th>Duration</th>
                        <th>Calories Burned</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="activity" items="${activities}">
                        <tr>
                            <td>${activity.activityType}</td>
                            <td>${activity.duration} minutes</td>
                            <td>${activity.caloriesBurned}</td>
                            <td>${activity.activityDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <section id="user-nutrient-intakes">
            <h2>User Nutrient Intakes</h2>
            <table>
                <thead>
                    <tr>
                        <th>Food Item</th>
                        <th>Calories</th>
                        <th>Protein</th>
                        <th>Carbs</th>
                        <th>Fat</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="intake" items="${nutrientIntakes}">
                        <tr>
                            <td>${intake.foodItem}</td>
                            <td>${intake.calories}</td>
                            <td>${intake.protein}g</td>
                            <td>${intake.carbs}g</td>
                            <td>${intake.fat}g</td>
                            <td>${intake.intakeDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Health & Wellness Tracker. All rights reserved.</p>
    </footer>
</body>
</html>