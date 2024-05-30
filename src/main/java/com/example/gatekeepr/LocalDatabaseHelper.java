package com.example.gatekeepr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalDatabaseHelper {
    private static final String LOCAL_CONNECTION_URL = "jdbc:mysql://localhost:3306/gatekeeprdb";
    private static final String LOCAL_USER = "root";
    private static final String LOCAL_PASSWORD = "system";

    public static Connection getLocalConnection() throws SQLException {
        return DriverManager.getConnection(LOCAL_CONNECTION_URL, LOCAL_USER, LOCAL_PASSWORD);
    }

    public static int executeUpdate(String query, Object... params) throws SQLException {
        try (Connection connection = getLocalConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        }
    }
}
