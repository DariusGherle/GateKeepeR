package com.example.gatekeepr.Database.MockDatabase;

import com.example.gatekeepr.Models.Admin;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDataMock {
    private List<Admin> admins;

    public AdminDataMock() {
        admins = new ArrayList<>();
        // Adaugă date de test
        admins.add(new Admin(new SimpleStringProperty("Admin1"), new SimpleStringProperty("123456")));
        admins.add(new Admin(new SimpleStringProperty("Admin2"), new SimpleStringProperty("123456")));
        // Adaugă mai mulți administratori după necesitate
    }

    public ResultSet getAdminData(String adresaAdmin, String password) {
        for (Admin admin : admins) {
            if (admin.adresaAdminProperty().get().equals(adresaAdmin) && admin.parolaPRoperty().get().equals(password)) {
                return new MockAdminResultSet(admin);
            }
        }
        return new MockAdminResultSet(null);
    }
}
