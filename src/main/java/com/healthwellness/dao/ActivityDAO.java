package com.healthwellness.dao;

import com.healthwellness.model.Activity;
import com.healthwellness.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {
    public void saveActivity(Activity activity) throws SQLException {
        String sql = "INSERT INTO activities (user_id, activity_type, duration, calories_burned, activity_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, activity.getUserId());
            pstmt.setString(2, activity.getActivityType());
            pstmt.setInt(3, activity.getDuration());
            pstmt.setInt(4, activity.getCaloriesBurned());
            pstmt.setDate(5, activity.getActivityDate());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    activity.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Activity> getActivitiesByUserId(int userId) throws SQLException {
        List<Activity> activities = new ArrayList<>();
        String sql = "SELECT * FROM activities WHERE user_id = ? ORDER BY activity_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Activity activity = new Activity();
                    activity.setId(rs.getInt("id"));
                    activity.setUserId(rs.getInt("user_id"));
                    activity.setActivityType(rs.getString("activity_type"));
                    activity.setDuration(rs.getInt("duration"));
                    activity.setCaloriesBurned(rs.getInt("calories_burned"));
                    activity.setActivityDate(rs.getDate("activity_date"));
                    activities.add(activity);
                }
            }
        }
        return activities;
    }
}
    
