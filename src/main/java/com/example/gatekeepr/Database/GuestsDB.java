package com.example.gatekeepr.Database;

import java.sql.*;

public class GuestsDB {
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;database=database-IP;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    public static void selectAll() {
        String selectSql = "SELECT * FROM guests";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_vizite") + " " + resultSet.getString("nume"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(int idVizite, String nume, String prenume, String cnp, String scopVizita, int marca, String portar, String dataOraIntrare, String dataOraIesire) {
        String insertSql = "INSERT INTO guests (id_vizite, nume, prenume, cnp, scop_vizita, marca, portar, data_ora_intrare, data_ora_iesire) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setInt(1, idVizite);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.setString(4, cnp);
            preparedStatement.setString(5, scopVizita);
            preparedStatement.setInt(6, marca);
            preparedStatement.setString(7, portar);
            preparedStatement.setString(8, dataOraIntrare);
            preparedStatement.setString(9, dataOraIesire);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(int idVizite, String nume) {
        String updateSql = "UPDATE guests SET nume = ? WHERE id_vizite = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setString(1, nume);
            preparedStatement.setInt(2, idVizite);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int idVizite) {
        String deleteSql = "DELETE FROM guests WHERE id_vizite = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setInt(1, idVizite);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
