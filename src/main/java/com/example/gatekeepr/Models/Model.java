package com.example.gatekeepr.Models;

import com.example.gatekeepr.Views.AccountType;
import com.example.gatekeepr.Views.ViewFactory;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private AccountType loginAccountType = AccountType.PORTAR;

    // Database connection details
    private static final String CONNECTION_URL = "jdbc:sqlserver://database-IP.database.windows.net:1433;"
            + "database=database-IP;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";
    private static final String USER = "database-IP@database-IP";
    private static final String PASSWORD = "Aloha123";

    // Client/Portar Data Section
    private Portar portar;
    private boolean portarLoginSuccessFlag;

    // Admin DataSection
    private Admin admin;
    private boolean adminLoginSuccessFlag;

    // UserAutorizat data section
    private UtilizatorAutorizat utilizatorAutorizat;
    private boolean utilizatorAutorizatSuccessFlag;

    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
     * Client/Portar Method section
     */
    public boolean getPortarLoginSuccessFlag() {
        return this.portarLoginSuccessFlag;
    }

    public void setPortarLoginSuccessFlag(boolean flag) {
        this.portarLoginSuccessFlag = flag;
    }

    public Portar getPortar() {
        return portar;
    }

    public void evaluatePortarCred(String pAdresa, String parola) {
        System.out.println("Evaluating credentials for: " + pAdresa);
        String query = "SELECT adresa_utilizator, parola FROM gatekeepers WHERE adresa_utilizator = ? AND parola = ?";

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, pAdresa);
            preparedStatement.setString(2, parola);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Credentials are correct.");
                    this.portar = new Portar(
                            new SimpleStringProperty(resultSet.getString("adresa_utilizator")),
                            new SimpleStringProperty(resultSet.getString("parola"))
                    );
                    this.portarLoginSuccessFlag = true;
                } else {
                    System.out.println("Credentials are incorrect.");
                    this.portarLoginSuccessFlag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.portarLoginSuccessFlag = false;
        }
    }

    /*
     * Admin Login Method Section
     */
    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean flag) {
        this.adminLoginSuccessFlag = flag;
    }

    public void evaluateAdminCred(String adresaAdmin, String parola) {
        System.out.println("Evaluating credentials for admin: " + adresaAdmin);
        String query = "SELECT adresa_utilizator, parola FROM admins WHERE adresa_utilizator = ? AND parola = ?";

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, adresaAdmin);
            preparedStatement.setString(2, parola);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Admin credentials are correct.");
                    this.admin = new Admin(
                            new SimpleStringProperty(resultSet.getString("adresa_utilizator")),
                            new SimpleStringProperty(resultSet.getString("parola"))
                    );
                    this.adminLoginSuccessFlag = true;
                } else {
                    System.out.println("Admin credentials are incorrect.");
                    this.adminLoginSuccessFlag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.adminLoginSuccessFlag = false;
        }
    }

    /*
     * UtilizatorAutorizat / Sef de departament
     */
    public boolean getUserAutorizatLoginSuccessFlag() {
        return utilizatorAutorizatSuccessFlag;
    }

    public void setUtilizatorAutorizatSuccessFlag(boolean flag) {
        this.utilizatorAutorizatSuccessFlag = flag;
    }

    // verifica doar daca sef utilizator e pe 1
    public void evaluateUtilCred(String adresaUtil, String parola) {
        System.out.println("Evaluating credentials for authorized user: " + adresaUtil);

        // Check if the password is "12345"
        if (!"12345".equals(parola)) {
            System.out.println("Authorized user credentials are incorrect.");
            this.utilizatorAutorizatSuccessFlag = false;
            return;
        }

        // Query to check if the adresa_utilizator exists in the employees table
        String query = "SELECT marca FROM employees WHERE marca = ? AND este_sef = 1";

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, adresaUtil);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Authorized user credentials are correct.");
                    this.utilizatorAutorizat = new UtilizatorAutorizat(
                            new SimpleStringProperty(adresaUtil),
                            new SimpleStringProperty(parola)
                    );
                    this.utilizatorAutorizatSuccessFlag = true;
                } else {
                    System.out.println("Authorized user credentials are incorrect.");
                    this.utilizatorAutorizatSuccessFlag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.utilizatorAutorizatSuccessFlag = false;
        }
    }


    // Define Portar, Admin, and UtilizatorAutorizat classes (simplified example)
    public class Portar {
        private SimpleStringProperty adresaUtilizator;
        private SimpleStringProperty parola;

        public Portar(SimpleStringProperty adresaUtilizator, SimpleStringProperty parola) {
            this.adresaUtilizator = adresaUtilizator;
            this.parola = parola;
        }

        // Getter and setter methods
    }

    public class Admin {
        private SimpleStringProperty adresaUtilizator;
        private SimpleStringProperty parola;

        public Admin(SimpleStringProperty adresaUtilizator, SimpleStringProperty parola) {
            this.adresaUtilizator = adresaUtilizator;
            this.parola = parola;
        }

        // Getter and setter methods
    }

    public class UtilizatorAutorizat {
        private SimpleStringProperty adresaUtilizator;
        private SimpleStringProperty parola;

        public UtilizatorAutorizat(SimpleStringProperty adresaUtilizator, SimpleStringProperty parola) {
            this.adresaUtilizator = adresaUtilizator;
            this.parola = parola;
        }

        // Getter and setter methods
    }
}
