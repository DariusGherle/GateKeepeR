package com.example.gatekeepr.Database.MockDatabase;

import com.example.gatekeepr.Models.UtilizatorAutorizat;

import java.sql.SQLException;

public class MockUserResultSet extends ResultSetStub {
    private UtilizatorAutorizat utilizatorAutorizat;
    private boolean isFirst = true;

    public MockUserResultSet(UtilizatorAutorizat utilizatorAutorizat) {
        this.utilizatorAutorizat = utilizatorAutorizat;
    }

    @Override
    public boolean next() throws SQLException {
        if (isFirst && utilizatorAutorizat != null) {
            isFirst = false;
            return true;
        }
        return false;
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        if (utilizatorAutorizat == null) {
            return null;
        }
        switch (columnLabel) {
            case "adresaUtilizator":
                return utilizatorAutorizat.adresaUtilizatorProperty().get();
            case "parola":
                return utilizatorAutorizat.parolaPRoperty().get();
            default:
                return null;
        }
    }

    // Implementați alte metode necesare
}
