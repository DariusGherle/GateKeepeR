package com.example.gatekeepr.Database;
import com.example.gatekeepr.Models.Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RaportsGenerator {
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;database=database-IP;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    // Metodă pentru a obține rapoartele de prezență pe divizie
    /*
    *
    * */
    public static void getDivisionAttendanceReports(int divisionId) {
        String selectSql = "SELECT e.divizie, e.nume, e.prenume, e.marca, e.numar_auto, al.data, al.tip, al.in_afara_orarurului " +
                "FROM employees e " +
                "JOIN access_logs al ON e.marca = al.marca_angajat " +
                "WHERE e.divizie = " + divisionId +
                " ORDER BY e.nume, e.prenume, al.data";

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            System.out.println("Division Attendance Report for Division ID: " + divisionId);
            while (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String ID=resultSet.getString("marca");
                String data = resultSet.getString("data");
                String tip = resultSet.getString("tip").equals("1") ? "Entry" : "Exit";
                String inAfaraOrarului = resultSet.getString("in_afara_orarurului").equals("1") ? "Yes" : "No";
                //System.out.println(nume + " " + prenume + " | " + tip + " | Time: " + data + " | Porter: "  + " | Outside Working Hours: " + inAfaraOrarului);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Obtine rapoartele de intrare/iesire pt toti angajatii
    public static ResultSet getAllAccessLogs() {
        String selectSql = "SELECT e.nume, e.prenume, e.marca, e.numar_auto, al.data, al.tip, al.in_afara_orarurului " +
                "FROM employees e " +
                "JOIN access_logs al ON e.marca = al.marca_angajat " +
                "ORDER BY e.nume, e.prenume, al.data";

        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            resultSet = statement.executeQuery(selectSql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Metodă pentru a obține rapoartele de prezență pentru toate diviziile DAAAAARIUS
    public static List<Access> getAllDivisionsAttendanceReports() {
        String selectSql = "SELECT e.divizie, e.nume, e.prenume, e.numar_auto, al.data, al.tip, al.portar, al.in_afara_orarurului " +
                "FROM employees e " +
                "JOIN access_logs al ON e.marca = al.marca_angajat " +
                "ORDER BY e.divizie, e.nume, e.prenume, al.data";

        List<Access> accessList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                String carePoarta;
                int divizie = resultSet.getInt("divizie");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String numarMasina = resultSet.getString("numar_auto");
                LocalDate data = resultSet.getDate("data").toLocalDate();
                String ora = resultSet.getString("data");
                String tip = resultSet.getString("tip").equals("1") ? "Entry" : "Exit";
                String portar = resultSet.getString("portar");
                if (portar.equals("0"))
                    carePoarta = "Pietonal";
                else
                    carePoarta = "Auto";

                String inAfaraOrarului = resultSet.getString("in_afara_orarurului").equals("1") ? "Yes" : "No";
                Access access = new Access(nume + " " + prenume, data.toString(), ora, carePoarta, numarMasina);
                accessList.add(access);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessList;
    }
        public static void getAttendanceReportByMarca(int marca) {
            String selectSql = "SELECT e.marca, e.nume, e.prenume, al.data, al.tip, al.portar, al.in_afara_orarurului " +
                    "FROM employees e " +
                    "JOIN access_logs al ON e.marca = al.marca_angajat " +
                    "WHERE e.marca = " + marca +
                    " ORDER BY al.data";

            try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                System.out.println("Attendance Report for Employee Marca: " + marca);
                while (resultSet.next()) {
                    String nume = resultSet.getString("nume");
                    String prenume = resultSet.getString("prenume");
                    String data = resultSet.getString("data");
                    String tip = resultSet.getInt("tip") == 1 ? "Entry" : "Exit";
                    String portar = resultSet.getString("portar");
                    String inAfaraOrarului = resultSet.getInt("in_afara_orarurului") == 1 ? "Yes" : "No";
                    System.out.println(nume + " " + prenume + " | " + tip + " | Time: " + data + " | Porter: " + portar + " | Outside Working Hours: " + inAfaraOrarului);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    // Metodă pentru a obține toate înregistrările access_logs ca un array de string-uri
    public static String[] getAccessLogsAsStringArray() {
        String selectSql = "SELECT * FROM access_logs";
        ArrayList<String> logsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tip = resultSet.getInt("tip");
                String data = resultSet.getString("data");
                int marcaAngajat = resultSet.getInt("marca_angajat");
                String portar = resultSet.getString("portar");
                int inAfaraOrarului = resultSet.getInt("in_afara_orarurului");

                String log = "ID: " + id + ", Tip: " + (tip == 1 ? "Entry" : "Exit") + ", Data: " + data + ", Marca Angajat: " + marcaAngajat + ", Portar: " + portar + ", In Afara Orarului: " + (inAfaraOrarului == 1 ? "Yes" : "No");
                logsList.add(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return logsList.toArray(new String[0]);
    }

    //Metoda care extrage a
}