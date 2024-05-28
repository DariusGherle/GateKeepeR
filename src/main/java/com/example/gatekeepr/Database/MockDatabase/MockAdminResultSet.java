package com.example.gatekeepr.Database.MockDatabase;

import com.example.gatekeepr.Models.Admin;

import java.sql.SQLException;

public class MockAdminResultSet extends ResultSetStub {
    private Admin admin;
    private boolean isFirst = true;

    public MockAdminResultSet(Admin admin) {
        this.admin = admin;
    }

    @Override
    public boolean next() throws SQLException {
        if (isFirst && admin != null) {
            isFirst = false;
            return true;
        }
        return false;
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        if (admin == null) {
            return null;
        }
        switch (columnLabel) {
            case "adresaAdmin":
                return admin.adresaAdminProperty().get();
            case "parola":
                return admin.parolaPRoperty().get();
            default:
                return null;
        }
    }

    // Implementați alte metode necesare
}
