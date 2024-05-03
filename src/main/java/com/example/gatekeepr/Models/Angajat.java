package com.example.gatekeepr.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Angajat {
    private final StringProperty id;
    private final StringProperty nume;
    private final StringProperty CNP;
    private final StringProperty poarta;
    private final StringProperty Departament;
    private final SimpleIntegerProperty intervalOrar;

    public Angajat(String id, String nume, String CNP, String poarta, String departament, int intervalOrar) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.nume = new SimpleStringProperty(this, "nume", nume);
        this.CNP = new SimpleStringProperty(this, "CNP", CNP);
        this.poarta = new SimpleStringProperty(this, "poarta", poarta);
        this.Departament = new SimpleStringProperty(this, "Departament", departament);
        this.intervalOrar = new SimpleIntegerProperty(this, "intervalOrar", intervalOrar);
    }

    // Getters for property values
    public String getId() {
        return id.get();
    }

    public String getNume() {
        return nume.get();
    }

    public String getCNP() {
        return CNP.get();
    }

    public String getPoarta() {
        return poarta.get();
    }

    public String getDepartament() {
        return Departament.get();
    }

    public int getIntervalOrar() {
        return intervalOrar.get();
    }

    // Property methods
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public StringProperty CNPProperty() {
        return CNP;
    }

    public StringProperty poartaProperty() {
        return poarta;
    }

    public StringProperty departamentProperty() {
        return Departament;
    }

    public SimpleIntegerProperty intervalOrarProperty() {
        return intervalOrar;
    }
}
