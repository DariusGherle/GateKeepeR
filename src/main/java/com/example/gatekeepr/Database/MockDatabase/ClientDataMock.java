package com.example.gatekeepr.Database.MockDatabase;

import com.example.gatekeepr.Models.Portar;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDataMock {
    private List<Portar> portars;

    public ClientDataMock() {
        portars = new ArrayList<>();
        // Adaugă date de test
        portars.add(new Portar(new SimpleStringProperty("Portar1"), new SimpleStringProperty("123456")));
        portars.add(new Portar(new SimpleStringProperty("Portar2"), new SimpleStringProperty("123456")));
        // Adaugă mai mulți clienți după necesitate
    }

    public ResultSet getClientData(String pAddress, String password) {
        for (Portar client : portars) {
            if (client.adresaUtilizatorProperty().get().equals(pAddress) && client.parolaPRoperty().get().equals(password)) {
                return new MockPortarResultSet(client);
            }
        }
        return new MockPortarResultSet(null);
    }
}
