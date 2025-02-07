<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>System Configuration</title>
    <link rel="stylesheet" href="css/system-config.css">
</head>
<body>
    <header>
        <h1>System Configuration</h1>
        <nav>
            <a href="admin-dashboard">Back to Dashboard</a>
        </nav>
    </header>

    <main>
        <section id="system-config">
            <h2>Current Configuration</h2>
            <table>
                <thead>
                    <tr>
                        <th>Key</th>
                        <th>Value</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${config}">
                        <tr>
                            <td>${entry.key}</td>
                            <td>${entry.value}</td>
                            <td>
                                <form action="admin-dashboard" method="post">
                                    <input type="hidden" name="action" value="updateConfig">
                                    <input type="hidden" name="key" value="${entry.key}">
                                    <input type="text" name="value" value="${entry.value}">
                                    <button type="submit">Update</button>
                                </form>
                                </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <section id="add-config">
            <h2>Add New Configuration</h2>
            <form action="admin-dashboard" method="post">
                <input type="hidden" name="action" value="updateConfig">
                <label for="newKey">Key:</label>
                <input type="text" id="newKey" name="key" required>
                <label for="newValue">Value:</label>
                <input type="text" id="newValue" name="value" required>
                <button type="submit">Add Configuration</button>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Health & Wellness Tracker. All rights reserved.</p>
    </footer>
</body>
</html>