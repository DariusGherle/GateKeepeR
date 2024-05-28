package com.example.gatekeepr.Database;

import java.sql.*;

public class AccessLogsDB {
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;database=database-IP;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    public static void selectAll() {
        String selectSql = "SELECT * FROM access_logs";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("portar"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(int tip, String data, int marcaAngajat, String portar, int inAfaraOrarurului) {
        String insertSql = "INSERT INTO access_logs (tip, data, marca_angajat, portar, in_afara_orarurului) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setInt(1, tip);
            preparedStatement.setString(2, data);
            preparedStatement.setInt(3, marcaAngajat);
            preparedStatement.setString(4, portar);
            preparedStatement.setInt(5, inAfaraOrarurului);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(int id, String portar) {
        String updateSql = "UPDATE access_logs SET portar = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setString(1, portar);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        String deleteSql = "DELETE FROM access_logs WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
