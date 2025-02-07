package com.healthwellness.dao;

import com.healthwellness.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SystemConfigDAO {
    public Map<String, String> getAllConfigurations() throws SQLException {
        Map<String, String> config = new HashMap<>();
        String sql = "SELECT * FROM system_config";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                config.put(rs.getString("config_key"), rs.getString("config_value"));
            }
        }
        return config;
    }

    public void updateConfiguration(String key, String value) throws SQLException {
        String sql = "INSERT INTO system_config (config_key, config_value) VALUES (?, ?) " +
                     "ON DUPLICATE KEY UPDATE config_value = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.setString(2, value);
            pstmt.setString(3, value);
            pstmt.executeUpdate();
        }
    }
}