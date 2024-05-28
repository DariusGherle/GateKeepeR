package com.example.gatekeepr.Database.MockDatabase;

import com.example.gatekeepr.Models.UtilizatorAutorizat;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDataMock {
    private List<UtilizatorAutorizat> utilizatoriAutorizati;

    public UserDataMock() {
        utilizatoriAutorizati = new ArrayList<>();
        // Adaugă date de test
        utilizatoriAutorizati.add(new UtilizatorAutorizat(new SimpleStringProperty("User1"), new SimpleStringProperty("123456")));
        utilizatoriAutorizati.add(new UtilizatorAutorizat(new SimpleStringProperty("User2"), new SimpleStringProperty("123456")));
        // Adaugă mai mulți utilizatori după necesitate
    }

    public ResultSet getUserData(String adresaUtilizator, String password) {
        for (UtilizatorAutorizat user : utilizatoriAutorizati) {
            if (user.adresaUtilizatorProperty().get().equals(adresaUtilizator) && user.parolaPRoperty().get().equals(password)) {
                return new MockUserResultSet(user);
            }
        }
        return new MockUserResultSet(null);
    }
}
