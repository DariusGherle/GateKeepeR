package com.example.gatekeepr.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
    private final StringProperty adresaAdmin;
    private final StringProperty parola;

    public Admin(StringProperty adresaAdmin, StringProperty parola) {
        this.adresaAdmin = new SimpleStringProperty();
        this.parola = new SimpleStringProperty();
    }

    public StringProperty adresaAdminProperty() {return adresaAdmin;}

    public StringProperty parolaPRoperty() {
        return parola;
    }

}
