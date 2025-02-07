package com.healthwellness.dao;

import com.healthwellness.model.User;
import com.healthwellness.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error registering user", e);
            throw e;
        }
        return false;
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    return user;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving user by username", e);
            throw e;
        }
        return null;
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    return user;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving user by ID", e);
            throw e;
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            throw e;
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
            throw e;
        }
    }

    public Map<String, Integer> getUserStatistics() throws SQLException {
        Map<String, Integer> statistics = new HashMap<>();
        String sql = "SELECT COUNT(*) as total_users, " +
                     "SUM(CASE WHEN role = 'ADMIN' THEN 1 ELSE 0 END) as admin_count, " +
                     "SUM(CASE WHEN role = 'USER' THEN 1 ELSE 0 END) as user_count " +
                     "FROM users";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                statistics.put("totalUsers", rs.getInt("total_users"));
                statistics.put("adminCount", rs.getInt("admin_count"));
                statistics.put("userCount", rs.getInt("user_count"));
            }
            logger.info("Retrieved user statistics: " + statistics);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving user statistics", e);
            throw e;
        }
        return statistics;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY created_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                users.add(user);
            }
            logger.info("Retrieved " + users.size() + " users from database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving users from database", e);
            throw e;
        }
        return users;
    }
}
