package com.healthwellness.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.healthwellness.model.BMI;
import com.healthwellness.util.DatabaseUtil;

public class BMIDAO {
    private static final String INSERT_BMI_SQL = "INSERT INTO bmi (user_id, weight, height, bmi_value, bmi_category) VALUES (?, ?, ?, ?, ?)";

    public void saveBMI(BMI bmi) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BMI_SQL)) {
            preparedStatement.setInt(1, bmi.getUserId());
            preparedStatement.setFloat(2, bmi.getWeight());
            preparedStatement.setFloat(3, bmi.getHeight());
            preparedStatement.setFloat(4, bmi.getBmiValue());
            preparedStatement.setString(5, bmi.getBmiCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this error or throwing a custom exception
        }
    }
}