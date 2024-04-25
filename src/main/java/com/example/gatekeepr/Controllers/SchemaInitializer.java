package com.example.gatekeepr.Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {
    public static void initializeDatabase() {
        String[] sqlStatements = new String[]{
                "CREATE TABLE IF NOT EXISTS admins (\n" +
                        "    user_address TEXT PRIMARY KEY,\n" +
                        "    password TEXT NOT NULL,\n" +
                        "    CHECK (LENGTH(user_address) <= 25),\n" +
                        "    CHECK (LENGTH(password) <= 25)\n" +
                        ");\n",  // Use the SQL provided above
                "CREATE TABLE IF NOT EXISTS gatekeepers (\n" +
                        "    user_address TEXT PRIMARY KEY,\n" +
                        "    password TEXT NOT NULL,\n" +
                        "    CHECK (LENGTH(user_address) <= 25),\n" +
                        "    CHECK (LENGTH(password) <= 25)\n" +
                        ");\n",
                "CREATE TABLE IF NOT EXISTS employees (\n" +
                        "    badge_number INTEGER PRIMARY KEY,\n" +
                        "    division INTEGER NOT NULL,\n" +
                        "    first_name TEXT NOT NULL,\n" +
                        "    last_name TEXT NOT NULL,\n" +
                        "    cnp TEXT NOT NULL,\n" +
                        "    photo_url TEXT NOT NULL,\n" +
                        "    bluetooth_security_code TEXT NOT NULL,\n" +
                        "    device_security_code TEXT NOT NULL,\n" +
                        "    access_time_slots INTEGER NOT NULL,\n" +
                        "    gate_access BOOLEAN NOT NULL,\n" +
                        "    barrier_access BOOLEAN NOT NULL,\n" +
                        "    car_number TEXT NOT NULL,\n" +
                        "    is_manager BOOLEAN NOT NULL,\n" +
                        "    CHECK (LENGTH(first_name) <= 25),\n" +
                        "    CHECK (LENGTH(last_name) <= 25),\n" +
                        "    CHECK (LENGTH(cnp) = 13),\n" +
                        "    CHECK (LENGTH(photo_url) <= 45),\n" +
                        "    CHECK (LENGTH(bluetooth_security_code) = 18),\n" +
                        "    CHECK (LENGTH(device_security_code) = 12),\n" +
                        "    CHECK (LENGTH(car_number) = 7)\n" +
                        ");\n",
                "CREATE TABLE IF NOT EXISTS guests (\n" +
                        "    visit_id INTEGER PRIMARY KEY,\n" +
                        "    first_name TEXT NOT NULL,\n" +
                        "    last_name TEXT NOT NULL,\n" +
                        "    cnp TEXT NOT NULL,\n" +
                        "    visit_purpose TEXT NOT NULL,\n" +
                        "    employee_badge INTEGER NOT NULL,\n" +
                        "    gatekeeper TEXT NOT NULL,\n" +
                        "    entry_datetime DATETIME NOT NULL,\n" +
                        "    exit_datetime DATETIME,\n" +
                        "    FOREIGN KEY (employee_badge) REFERENCES employees(badge_number),\n" +
                        "    FOREIGN KEY (gatekeeper) REFERENCES gatekeepers(user_address),\n" +
                        "    CHECK (LENGTH(first_name) <= 25),\n" +
                        "    CHECK (LENGTH(last_name) <= 25),\n" +
                        "    CHECK (LENGTH(cnp) = 13),\n" +
                        "    CHECK (LENGTH(visit_purpose) <= 256)\n" +
                        ");\n",
                "CREATE TABLE IF NOT EXISTS access_logs (\n" +
                        "    log_id INTEGER PRIMARY KEY,\n" +
                        "    entry_exit BOOLEAN NOT NULL,\n" +
                        "    datetime DATETIME NOT NULL,\n" +
                        "    employee_badge INTEGER NOT NULL,\n" +
                        "    gatekeeper TEXT NOT NULL,\n" +
                        "    outside_hours BOOLEAN NOT NULL,\n" +
                        "    FOREIGN KEY (employee_badge) REFERENCES employees(badge_number),\n" +
                        "    FOREIGN KEY (gatekeeper) REFERENCES gatekeepers(user_address),\n" +
                        "    CHECK (LENGTH(gatekeeper) <= 25)\n" +
                        ");\n",
        };

        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement()) {
            for (String sql : sqlStatements) {
                stmt.execute(sql);
            }
            System.out.println("Database tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating database: " + e.getMessage());
        }
    }
}
