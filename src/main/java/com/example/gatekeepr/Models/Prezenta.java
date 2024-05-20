package com.example.gatekeepr.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prezenta {
    private final StringProperty nume;
    private final StringProperty prenume;
    private final StringProperty CNP;
    private final StringProperty numar;
    private final ObjectProperty<LocalDate> data;
    private final ObjectProperty<LocalDateTime> ora;

    public Prezenta(StringProperty nume, StringProperty prenume, StringProperty CNP, StringProperty numar, ObjectProperty<LocalDate> data, ObjectProperty<LocalDateTime> ora) {
        this.nume = new SimpleStringProperty();
        this.prenume = new SimpleStringProperty();
        this.CNP = new SimpleStringProperty();
        this.numar = new SimpleStringProperty();
        this.data = new SimpleObjectProperty<>();
        this.ora = new SimpleObjectProperty<>();
    }

    public StringProperty numeProperty() {return this.nume;}

    public StringProperty prenumeProperty() {return this.prenume;}

    public StringProperty cnpProperty() {return this.CNP;}

    public StringProperty numarProperty() {return this.numar;}

    public ObjectProperty<LocalDate> dataPropery() {return this.data;}

    public ObjectProperty<LocalDateTime> oraPropery() {return this.ora;}
}
