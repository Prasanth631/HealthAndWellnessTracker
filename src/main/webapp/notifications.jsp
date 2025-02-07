<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notifications</title>
    <link rel="stylesheet" href="css/notifications.css">
</head>
<body>
    <header>
        <h1>Notifications</h1>
        <nav>
            <a href="admin-dashboard">Back to Dashboard</a>
        </nav>
    </header>

    <main>
        <section id="send-notification">
            <h2>Send New Notification</h2>
            <form action="admin-dashboard" method="post">
                <input type="hidden" name="action" value="sendNotification">
                <label for="message">Message:</label>
                <textarea id="message" name="message" required></textarea>
                <label for="recipientType">Recipient Type:</label>
                <select id="recipientType" name="recipientType">
                    <option value="ALL">All Users</option>
                    <option value="ADMIN">Admins Only</option>
                    <option value="USER">Regular Users Only</option>
                </select>
                <button type="submit">Send Notification</button>
            </form>
        </section>

        <section id="notification-history">
            <h2>Notification History</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Message</th>
                        <th>Recipient Type</th>
                        <th>Date Sent</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="notification" items="${notifications}">
                        <tr>
                            <td>${notification.id}</td>
                            <td>${notification.message}</td>
                            <td>${notification.recipientType}</td>
                            <td>${notification.dateSent}</td>
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