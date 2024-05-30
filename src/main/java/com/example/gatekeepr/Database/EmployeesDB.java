package com.example.gatekeepr.Database;

import java.sql.*;

public class EmployeesDB {
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;database=database-IP;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    public static ResultSet selectAll() throws SQLException {
        String selectSql = "SELECT marca, nume, prenume, cnp, divizie, poza, cod_securitate_bluetooth, cod_securitate_dispozitiv, acces_poarta, acces_bariera, numar_auto, este_sef, intervale_orare_access FROM employees";
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        return statement.executeQuery(selectSql);
    }

    public static void insert(int marca, int divizie, String nume, String prenume, String cnp, String poza, String codSecuritateBluetooth, String codSecuritateDispozitiv, int intervaleOrareAccess, int accesPoarta, int accesBariera, String numarAuto, int esteSef) {
        String insertSql = "INSERT INTO employees (marca, divizie, nume, prenume, cnp, poza, cod_securitate_bluetooth, cod_securitate_dispozitiv, acces_poarta, acces_bariera, numar_auto, este_sef, intervale_orare_access) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setInt(1, marca);
            preparedStatement.setInt(2, divizie);
            preparedStatement.setString(3, nume);
            preparedStatement.setString(4, prenume);
            preparedStatement.setString(5, cnp);
            preparedStatement.setString(6, poza);
            preparedStatement.setString(7, codSecuritateBluetooth);
            preparedStatement.setString(8, codSecuritateDispozitiv);
            preparedStatement.setInt(9, accesPoarta);
            preparedStatement.setInt(10, accesBariera);
            preparedStatement.setString(11, numarAuto);
            preparedStatement.setInt(12, esteSef);
            preparedStatement.setInt(13, intervaleOrareAccess);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(int marca, String nume) {
        String updateSql = "UPDATE employees SET nume = ? WHERE marca = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setString(1, nume);
            preparedStatement.setInt(2, marca);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int marca) {
        String deleteSql = "DELETE FROM employees WHERE marca = ?";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setInt(1, marca);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}