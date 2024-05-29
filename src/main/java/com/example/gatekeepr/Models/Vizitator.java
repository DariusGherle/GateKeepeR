package com.example.gatekeepr.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;
import java.time.LocalDateTime;

public class Vizitator {
    private final StringProperty nume;
    private final StringProperty prenume;
    private final StringProperty cnp;
    private final StringProperty scopVizita;
    private final StringProperty portar;
    private final ObjectProperty<Date> dataOraIntrare;
    private final ObjectProperty<Date> dataOraIesire;

    public Vizitator(String nume, String prenume, String cnp, String scopVizita, String portar, Date dataOraIntrare, Date dataOraIesire) {
        this.nume = new SimpleStringProperty(nume);
        this.prenume = new SimpleStringProperty(prenume);
        this.cnp = new SimpleStringProperty(cnp);
        this.scopVizita = new SimpleStringProperty(scopVizita);
        this.portar = new SimpleStringProperty(portar);
        this.dataOraIntrare = new SimpleObjectProperty<>(dataOraIntrare);
        this.dataOraIesire = new SimpleObjectProperty<>(dataOraIesire);
    }

    public StringProperty numeProperty() { return nume; }
    public StringProperty prenumeProperty() { return prenume; }
    public StringProperty cnpProperty() { return cnp; }
    public StringProperty scopVizitaProperty() { return scopVizita; }
    public StringProperty portarProperty() { return portar; }
    public ObjectProperty<Date> dataOraIntrareProperty() { return dataOraIntrare; }
    public ObjectProperty<Date> dataOraIesireProperty() { return dataOraIesire; }
}