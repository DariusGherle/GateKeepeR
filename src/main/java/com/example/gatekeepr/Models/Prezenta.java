package com.example.gatekeepr.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prezenta {
    private final StringProperty nume;
    private final StringProperty prenume;
    private final StringProperty marca;
    private final StringProperty departament;
    private final StringProperty procentajPrezenta;

    public Prezenta(StringProperty nume, StringProperty prenume, StringProperty marca, StringProperty departament, StringProperty procentajPrezenta) {
        this.nume = nume;
        this.prenume = prenume;
        this.marca = marca;
        this.departament = departament;
        this.procentajPrezenta = procentajPrezenta;
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public StringProperty prenumeProperty() {
        return prenume;
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public StringProperty departamentProperty() {
        return departament;
    }

    public StringProperty procentajPrezentaProperty() {
        return procentajPrezenta;
    }
}