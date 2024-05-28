package com.example.gatekeepr.Database.MockDatabase;

import com.example.gatekeepr.Models.Portar;

import java.sql.SQLException;

public class MockPortarResultSet extends ResultSetStub {
    private Portar portar;
    private boolean isFirst = true;

    public MockPortarResultSet(Portar portar) {
        this.portar = portar;
    }

    @Override
    public boolean next() throws SQLException {
        if (isFirst && portar != null) {
            isFirst = false;
            return true;
        }
        return false;
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        if (portar == null) {
            return null;
        }
        switch (columnLabel) {
            case "adresaUtilizator":
                return portar.adresaUtilizatorProperty().get();
            case "parola":
                return portar.parolaPRoperty().get();
            default:
                return null;
        }
    }

    // Implementați alte metode necesare
}
