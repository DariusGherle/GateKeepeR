package com.example.gatekeepr.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Access {
    private final StringProperty nume;
    private final StringProperty data;
    private final StringProperty ora;
    private final StringProperty carePoarta;
    private final StringProperty nrMasina_ID;

    public Access(String nume, String data, String ora, String carePoarta, String nrMasina_ID) {
        this.nume = new SimpleStringProperty(this, "Nume", nume);
        this.data = new SimpleStringProperty(this, "Data", data);
        this.ora = new SimpleStringProperty(this, "Ora", ora);
        this.carePoarta = new SimpleStringProperty(this, "CarePoarta", carePoarta);
        this.nrMasina_ID = new SimpleStringProperty(this, "nrMasinaSauID", nrMasina_ID);
    }

    public StringProperty numeProperty() {return this.nume;}

    public StringProperty oraProperty() {return this.ora;}

    public StringProperty carePoartaProperty() {return this.carePoarta;}

    public StringProperty datePropery() {return this.data;}

    public StringProperty nrMasinaSauIdProperty() {return this.nrMasina_ID;}
}
