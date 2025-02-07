package com.healthwellness.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtil {
    private static final Logger logger = Logger.getLogger(DatabaseUtil.class.getName());
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException | IOException e) {
            logger.log(Level.SEVERE, "Error loading database properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
            );
            logger.info("Database connection established");
            return conn;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error establishing database connection", e);
            throw e;
        }
    }
}
