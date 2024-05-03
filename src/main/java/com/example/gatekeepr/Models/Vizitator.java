package com.example.gatekeepr.Models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Vizitator {
    private final StringProperty nume;
    private final ObjectProperty<LocalDate> data;
    private final StringProperty ora;
    private final StringProperty carePoarta;
    private final StringProperty nrMasina;

    public Vizitator(String nume, LocalDate data, String ora, String carePoarta, String nrMasina) {
        this.nume = new SimpleStringProperty(this, "nume", nume);
        this.data = new SimpleObjectProperty<>(this, "data", data);
        this.ora = new SimpleStringProperty(this, "ora", ora);
        this.carePoarta = new SimpleStringProperty(this, "carePoarta", carePoarta);
        this.nrMasina = new SimpleStringProperty(this, "nrMasina", nrMasina);
    }

    // Getter methods
    public String getNume() {
        return nume.get();
    }

    public LocalDate getData() {
        return data.get();
    }

    public String getOra() {
        return ora.get();
    }

    public String getCarePoarta() {
        return carePoarta.get();
    }

    public String getNrMasina() {
        return nrMasina.get();
    }

    // Property methods
    public StringProperty numeProperty() {
        return nume;
    }

    public ObjectProperty<LocalDate> dataProperty() {
        return data;
    }

    public StringProperty oraProperty() {
        return ora;
    }

    public StringProperty carePoartaProperty() {
        return carePoarta;
    }

    public StringProperty nrMasinaProperty() {
        return nrMasina;
    }
}
