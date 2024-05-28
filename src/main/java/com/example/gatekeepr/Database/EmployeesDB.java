package com.example.gatekeepr.Database;

import java.sql.*;

public class EmployeesDB {
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;database=database-IP;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    public static void selectAll() {
        String selectSql = "SELECT * FROM employees";
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("marca") + " " + resultSet.getString("nume"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(int marca, int divizie, String nume, String prenume, String cnp, String poza, String codSecuritateBluetooth, String codSecuritateDispozitiv, int intervaleOrareAccess, int accesPoarta, int accesBariera, String numarAuto, int esteSef) {
        String insertSql = "INSERT INTO employees (marca, divizie, nume, prenume, cnp, poza, cod_securitate_bluetooth, cod_securitate_dispozitiv, intervale_orare_access, acces_poarta, acces_bariera, numar_auto, este_sef) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setInt(9, intervaleOrareAccess);
            preparedStatement.setInt(10, accesPoarta);
            preparedStatement.setInt(11, accesBariera);
            preparedStatement.setString(12, numarAuto);
            preparedStatement.setInt(13, esteSef);

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
