package com.healthwellness.dao;

import com.healthwellness.model.NutrientIntake;
import com.healthwellness.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutrientIntakeDAO {
    public void saveNutrientIntake(NutrientIntake nutrientIntake) throws SQLException {
        String sql = "INSERT INTO nutrient_intake (user_id, food_item, calories, protein, carbs, fat, intake_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, nutrientIntake.getUserId());
            pstmt.setString(2, nutrientIntake.getFoodItem());
            pstmt.setInt(3, nutrientIntake.getCalories());
            pstmt.setFloat(4, nutrientIntake.getProtein());
            pstmt.setFloat(5, nutrientIntake.getCarbs());
            pstmt.setFloat(6, nutrientIntake.getFat());
            pstmt.setDate(7, nutrientIntake.getIntakeDate());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    nutrientIntake.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
    public Map<String, Double> getNutrientStatistics() throws SQLException {
        Map<String, Double> statistics = new HashMap<>();
        String sql = "SELECT AVG(calories) as avg_calories, " +
                     "AVG(protein) as avg_protein, " +
                     "AVG(carbs) as avg_carbs, " +
                     "AVG(fat) as avg_fat " +
                     "FROM nutrient_intake";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                statistics.put("avgCalories", rs.getDouble("avg_calories"));
                statistics.put("avgProtein", rs.getDouble("avg_protein"));
                statistics.put("avgCarbs", rs.getDouble("avg_carbs"));
                statistics.put("avgFat", rs.getDouble("avg_fat"));
            }
        }
        return statistics;
    }

    public List<NutrientIntake> getNutrientIntakeByUserId(int userId) throws SQLException {
        List<NutrientIntake> nutrientIntakes = new ArrayList<>();
        String sql = "SELECT * FROM nutrient_intake WHERE user_id = ? ORDER BY intake_date DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NutrientIntake nutrientIntake = new NutrientIntake();
                    nutrientIntake.setId(rs.getInt("id"));
                    nutrientIntake.setUserId(rs.getInt("user_id"));
                    nutrientIntake.setFoodItem(rs.getString("food_item"));
                    nutrientIntake.setCalories(rs.getInt("calories"));
                    nutrientIntake.setProtein(rs.getFloat("protein"));
                    nutrientIntake.setCarbs(rs.getFloat("carbs"));
                    nutrientIntake.setFat(rs.getFloat("fat"));
                    nutrientIntake.setIntakeDate(rs.getDate("intake_date"));
                    nutrientIntakes.add(nutrientIntake);
                }
            }
        }
        return nutrientIntakes;
    }

    public void updateNutrientIntake(NutrientIntake nutrientIntake) throws SQLException {
        String sql = "UPDATE nutrient_intake SET food_item = ?, calories = ?, protein = ?, carbs = ?, fat = ?, intake_date = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nutrientIntake.getFoodItem());
            pstmt.setInt(2, nutrientIntake.getCalories());
            pstmt.setFloat(3, nutrientIntake.getProtein());
            pstmt.setFloat(4, nutrientIntake.getCarbs());
            pstmt.setFloat(5, nutrientIntake.getFat());
            pstmt.setDate(6, nutrientIntake.getIntakeDate());
            pstmt.setInt(7, nutrientIntake.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteNutrientIntake(int id) throws SQLException {
        String sql = "DELETE FROM nutrient_intake WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}