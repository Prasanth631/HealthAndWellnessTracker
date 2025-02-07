<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/admin-dashboard.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
        <nav>
            <a href="dashboard.jsp">User Dashboard</a>
            <a href="admin-dashboard?action=systemConfig">System Config</a>
            <a href="admin-dashboard?action=notifications">Notifications</a>
            <form action="${pageContext.request.contextPath}/logout" method="post" style="display: inline;">
                <button type="submit">Logout</button>
            </form>
        </nav>
    </header>

    <main>
        <section id="user-statistics">
            <h2>User Statistics</h2>
            <canvas id="userChart"></canvas>
        </section>

        <section id="nutrient-statistics">
            <h2>Average Nutrient Intake</h2>
            <canvas id="nutrientChart"></canvas>
        </section>

        <section id="user-management">
            <h2>User Management</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${allUsers}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>
                                <a href="admin-dashboard?action=userDetails&userId=${user.id}">View Details</a>
                                <form action="admin-dashboard" method="post" style="display: inline;">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="action" value="toggleAdmin">
                                    <button type="submit">${user.role == 'ADMIN' ? 'Remove Admin' : 'Make Admin'}</button>
                                </form>
                                <form action="admin-dashboard" method="post" style="display: inline;">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="action" value="deleteUser">
                                    <button type="submit" onclick="return confirm('Are you sure you want to delete this user?')">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>

    <footer>
        <p></p>
    </footer>

    <script>
        // User Chart
        var userCtx = document.getElementById('userChart').getContext('2d');
        var userChart = new Chart(userCtx, {
            type: 'pie',
            data: {
                labels: ['Admin Users', 'Regular Users'],
                datasets: [{
                    data: [${userStatistics.adminCount}, ${userStatistics.userCount}],
                    backgroundColor: ['#FF6384', '#36A2EB']
                }]
            }
        });

        // Nutrient Chart
        var nutrientCtx = document.getElementById('nutrientChart').getContext('2d');
        var nutrientChart = new Chart(nutrientCtx, {
            type: 'bar',
            data: {
                labels: ['Calories', 'Protein', 'Carbs', 'Fat'],
                datasets: [{
                    label: 'Average Intake',
                    data: [
                        ${nutrientStatistics.avgCalories},
                        ${nutrientStatistics.avgProtein},
                        ${nutrientStatistics.avgCarbs},
                        ${nutrientStatistics.avgFat}
                    ],
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0']
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>
