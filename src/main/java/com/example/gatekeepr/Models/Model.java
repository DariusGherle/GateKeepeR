package com.example.gatekeepr.Models;

import com.example.gatekeepr.Database.MockDatabase.ClientDataMock;
import com.example.gatekeepr.Views.AccountType;
import com.example.gatekeepr.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.Statement;

public class Model {
    private static  Model model;
    private final ViewFactory viewFactory;
    private AccountType loginAccountType=AccountType.CLIENT;

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
        this.viewFactory=new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if(model==null) {
            model=new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType=loginAccountType;
    }

    /*
    * Client/Portar MEthod section
    * */
    public boolean getPortarLoginSuccessFlag() {return this.portarLoginSuccessFlag;}
    public void setPortarLoginSuccessFlag(boolean flag) {this.portarLoginSuccessFlag=flag;}
    public Portar getPortar() {
        return portar;
    }

    public void evaluatePortarCred(String pAdresa, String parola) {
        ClientDataMock clientDataMock = new ClientDataMock();  // Creează o instanță a clasei ClientDataMock
        ResultSet resultSet = clientDataMock.getClientData(pAdresa, parola);  // Apelează metoda non-statică getClientData
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                this.portar.adresaUtilizatorProperty().set(resultSet.getString("adresaUtilizator"));
                this.portar.parolaPRoperty().set(resultSet.getString("parola"));
                this.portarLoginSuccessFlag=(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
