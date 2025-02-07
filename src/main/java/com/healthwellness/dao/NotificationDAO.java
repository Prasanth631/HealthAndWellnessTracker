package com.healthwellness.dao;

import com.healthwellness.model.Notification;
import com.healthwellness.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    public void createNotification(String message, String recipientType) throws SQLException {
        String sql = "INSERT INTO notifications (message, recipient_type, date_sent) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, message);
            pstmt.setString(2, recipientType);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        }
    }

    public List<Notification> getAllNotifications() throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications ORDER BY date_sent DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt("id"));
                notification.setMessage(rs.getString("message"));
                notification.setRecipientType(rs.getString("recipient_type"));
                notification.setDateSent(rs.getTimestamp("date_sent"));
                notifications.add(notification);
            }
        }
        return notifications;
    }
}