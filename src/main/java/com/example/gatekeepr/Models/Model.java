package com.example.gatekeepr.Models;

import com.example.gatekeepr.Database.MockDatabase.AdminDataMock;
import com.example.gatekeepr.Database.MockDatabase.ClientDataMock;
import com.example.gatekeepr.Database.MockDatabase.UserDataMock;
import com.example.gatekeepr.Views.AccountType;
import com.example.gatekeepr.Views.ViewFactory;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private AccountType loginAccountType = AccountType.PORTAR;

    //Client/Portar Data Section
    private Portar portar;
    private boolean portarLoginSuccessFlag;

    //Admin DataSection
    private Admin admin;
    private boolean adminLoginSuccessFlag;

    //UserAutorizat data section
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
        ClientDataMock clientDataMock = new ClientDataMock();  // Creează o instanță a clasei ClientDataMock
        ResultSet resultSet = clientDataMock.getClientData(pAdresa, parola);  // Apelează metoda non-statică getClientData
        try {
            if (resultSet.next()) {
                System.out.println("Credentials are correct.");
                this.portar = new Portar(new SimpleStringProperty(resultSet.getString("adresaUtilizator")),
                        new SimpleStringProperty(resultSet.getString("parola")));
                this.portarLoginSuccessFlag = true;
            } else {
                System.out.println("Credentials are incorrect.");
                this.portarLoginSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.portarLoginSuccessFlag = false;
        }
    }
    /*
    * Admin Login Method Section
    *
    * */

    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean flag) {
        this.adminLoginSuccessFlag=flag;
    }
    public void evaluateAdminCred(String adresaAdmin, String parola) {
        System.out.println("Evaluating credentials for admin: " + adresaAdmin);
        AdminDataMock adminDataMock = new AdminDataMock();  // Creează o instanță a clasei AdminDataMock
        ResultSet resultSet = adminDataMock.getAdminData(adresaAdmin, parola);  // Apelează metoda non-statică getAdminData
        try {
            if (resultSet.next()) {
                System.out.println("Admin credentials are correct.");
                this.admin = new Admin(new SimpleStringProperty(resultSet.getString("adresaAdmin")),
                        new SimpleStringProperty(resultSet.getString("parola")));
                this.adminLoginSuccessFlag = true;
            } else {
                System.out.println("Admin credentials are incorrect.");
                this.adminLoginSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.adminLoginSuccessFlag = false;
        }}

        /*
         * UtilizatorAutorizat / Sef de departament
         * */

    public boolean getUserAutorizatLoginSuccessFlag() {
        return utilizatorAutorizatSuccessFlag;
    }

    public void setUtilizatorAutorizatSuccessFlag(boolean flag) {
        this.utilizatorAutorizatSuccessFlag=flag;
    }

    public void evaluateUtilCred(String adresaUtil, String parola) {
        System.out.println("Evaluating credentials for admin: " + adresaUtil);
        UserDataMock userDataMock = new UserDataMock();  // Creează o instanță a clasei AdminDataMock
        ResultSet resultSet = userDataMock.getUserData(adresaUtil, parola);  // Apelează metoda non-statică getAdminData
        try {
            if (resultSet.next()) {
                System.out.println("AuthorizedUser credentials are correct.");
                this.utilizatorAutorizat = new UtilizatorAutorizat(new SimpleStringProperty(resultSet.getString("adresaUtilizator")),
                        new SimpleStringProperty(resultSet.getString("parola")));
                this.utilizatorAutorizatSuccessFlag = true;
            } else {
                System.out.println("UtilizatorAutorizat credentials are incorrect.");
                this.utilizatorAutorizatSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.utilizatorAutorizatSuccessFlag = false;
        }}

    }

