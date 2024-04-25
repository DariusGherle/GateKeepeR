package com.example.gatekeepr.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:./src/main/resources/LocalDB.db"; // Adjust the path to where you want your database file stored

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
