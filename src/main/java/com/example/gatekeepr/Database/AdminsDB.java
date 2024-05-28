package com.example.gatekeepr.Database;

import java.sql.*;

public class AdminsDB {
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;database=database-IP;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    public static void selectAll() {
        String selectSql = "SELECT * FROM admins";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString("adresa_utilizator") + " " + resultSet.getString("parola"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(String adresaUtilizator, String parola) {
        String insertSql = "INSERT INTO admins (adresa_utilizator, parola) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setString(1, adresaUtilizator);
            preparedStatement.setString(2, parola);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(String adresaUtilizator, String parola) {
        String updateSql = "UPDATE admins SET parola = ? WHERE adresa_utilizator = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setString(1, parola);
            preparedStatement.setString(2, adresaUtilizator);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(String adresaUtilizator) {
        String deleteSql = "DELETE FROM admins WHERE adresa_utilizator = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setString(1, adresaUtilizator);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
