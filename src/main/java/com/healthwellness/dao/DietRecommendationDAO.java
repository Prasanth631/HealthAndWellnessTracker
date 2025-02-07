package com.healthwellness.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.healthwellness.model.DietRecommendation;
import com.healthwellness.util.DatabaseUtil;

public class DietRecommendationDAO {
    private static final String SELECT_DIET_RECOMMENDATION = "SELECT * FROM diet_recommendations WHERE bmi_category = ?";

    public DietRecommendation getDietRecommendation(String bmiCategory) {
        DietRecommendation recommendation = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIET_RECOMMENDATION)) {
            preparedStatement.setString(1, bmiCategory);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    recommendation = new DietRecommendation();
                    recommendation.setBmiCategory(rs.getString("bmi_category"));
                    recommendation.setBreakfast(rs.getString("breakfast"));
                    recommendation.setLunch(rs.getString("lunch"));
                    recommendation.setDinner(rs.getString("dinner"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this error or throwing a custom exception
        }
        return recommendation;
    }
}