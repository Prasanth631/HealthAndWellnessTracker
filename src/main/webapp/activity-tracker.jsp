<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activity Tracker - Health and Wellness Tracker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/activity-tracker.css">
</head>
<body>
    <div class="container">
        <main>
            <h2 class="title">Activity Tracker</h2>
            <form action="activity-tracker" method="post" class="nutrient-form">
                <div class="input-group">
                    <select name="activityType" id="activityType" required onchange="updateCalories()">
                        <option value="">Select Activity</option>
                        <option value="Walking">Walking</option>
                        <option value="Swimming">Swimming</option>
                        <option value="Running">Running</option>
                        <option value="Exercise">Exercise</option>
                        <option value="Other">Other</option>
                    </select>
                    <label for="activityType">Activity Type</label>
                </div>
                <div class="input-group" id="otherActivityGroup" style="display: none;">
                    <input type="text" name="otherActivity" id="otherActivity" placeholder="Enter activity">
                    <label for="otherActivity">Other Activity</label>
                </div>
                <div class="input-group">
                    <input type="number" name="duration" id="duration" placeholder="Enter duration" required onchange="updateCalories()">
                    <label for="duration">Duration (minutes)</label>
                </div>
                <div class="input-group">
                    <input type="number" name="caloriesBurned" id="caloriesBurned" placeholder="Calories burned" readonly>
                    <label for="caloriesBurned">Calories Burned</label>
                </div>
                <div class="input-group">
                    <input type="date" name="activityDate" id="activityDate" required>
                    <label for="activityDate">Activity Date</label>
                </div>
                <button class="submit-btn" type="submit">Add Activity</button>
            </form>
            
            <h3 class="subtitle">Your Activities</h3>
            <div class="table-container">
                <table class="result">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Activity</th>
                            <th>Duration</th>
                            <th>Calories Burned</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty activities}">
                                <tr>
                                    <td colspan="5" style="text-align: center;">No activities found. Add your first activity above!</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${activities}" var="activity">
                                    <tr>
                                        <td>${activity.activityDate}</td>
                                        <td>
                                            <span class="activity-type ${activity.activityType.toLowerCase()}">
                                                <i class="fas fa-${activityIcons[activity.activityType]}"></i>
                                                ${activity.activityType}
                                            </span>
                                        </td>
                                        <td>${activity.duration} minutes</td>
                                        <td>${activity.caloriesBurned}</td>
                                        <td>
                                            <a href="delete-activity?id=${activity.id}" 
                                               onclick="return confirm('Are you sure you want to delete this activity?');"
                                               class="delete-btn">
                                                <i class="fas fa-trash-alt"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
            <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
        </main>
    </div>

    <script>
    const caloriesPerMinute = {
        'Walking': 5,
        'Swimming': 7.43,
        'Running': 10,
        'Exercise': 5.56,
        'Other': 5 // Default value for Other
    };

    function updateCalories() {
        var activityType = document.getElementById('activityType');
        var otherActivityGroup = document.getElementById('otherActivityGroup');
        var duration = document.getElementById('duration');
        var caloriesBurned = document.getElementById('caloriesBurned');

        if (activityType.value === 'Other') {
            otherActivityGroup.style.display = 'block';
        } else {
            otherActivityGroup.style.display = 'none';
        }
        if (duration.value && activityType.value) {
            let calories = Math.round(caloriesPerMinute[activityType.value] * parseInt(duration.value));
            caloriesBurned.value = calories;
        } else {
            caloriesBurned.value = '';
        }
    }
    updateCalories();
    </script>
</body>
</html>