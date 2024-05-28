package com.example.gatekeepr.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Angajat {
    private final StringProperty marca;
    private final StringProperty nume;
    private final StringProperty prenume;
    private final StringProperty CNP;
    private final StringProperty poarta;
    private final String poza;
    private final String cod_securitate_bluetooth;
    private final String cod_securitate_dispozitiv;
    private final boolean acces_poarta;
    private final boolean acces_bariera;
    private final StringProperty numar_auto;
    private final boolean este_sef;
    private final int Departament;
    private final int intervale_orare_acces;
    /*
    * private final int marca;
    private final int divizie;
    private final StringProperty nume;
    private final StringProperty prenume;
    private final StringProperty cnp;
    private final StringProperty poza;
    private final StringProperty cod_securitate_bluetooth;
    private final StringProperty cod_securitate_dispozitiv;
    private final int intervale_orare_acces;
    private final boolean acces_poarta;
    private final boolean acces_bariera;
    private final StringProperty numar_auto;
    private final boolean este_sef;
    * */

    public Angajat(StringProperty marca, StringProperty nume, StringProperty prenume, StringProperty CNP, StringProperty poarta, String poza, String cod_securitate_bluetooth, String cod_securitate_dispozitiv, boolean acces_poarta, boolean acces_bariera, StringProperty numar_auto, boolean este_sef, int departament, int intervale_orare_acces) {
        this.marca = marca;
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.poarta = poarta;
        this.poza = poza;
        this.cod_securitate_bluetooth = cod_securitate_bluetooth;
        this.cod_securitate_dispozitiv = cod_securitate_dispozitiv;
        this.acces_poarta = acces_poarta;
        this.acces_bariera = acces_bariera;
        this.numar_auto = numar_auto;
        this.este_sef = este_sef;
        Departament = departament;
        this.intervale_orare_acces = intervale_orare_acces;
    }

    public String getMarca() {
        return marca.get();
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getNume() {
        return nume.get();
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public String getPrenume() {
        return prenume.get();
    }

    public StringProperty prenumeProperty() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume.set(prenume);
    }

    public String getCNP() {
        return CNP.get();
    }

    public StringProperty CNPProperty() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP.set(CNP);
    }

    public String getPoarta() {
        return poarta.get();
    }

    public StringProperty poartaProperty() {
        return poarta;
    }

    public void setPoarta(String poarta) {
        this.poarta.set(poarta);
    }

    public String getPoza() {
        return poza;
    }

    public String getCod_securitate_bluetooth() {
        return cod_securitate_bluetooth;
    }

    public String getCod_securitate_dispozitiv() {
        return cod_securitate_dispozitiv;
    }

    public boolean isAcces_poarta() {
        return acces_poarta;
    }

    public boolean isAcces_bariera() {
        return acces_bariera;
    }

    public String getNumar_auto() {
        return numar_auto.get();
    }

    public StringProperty numar_autoProperty() {
        return numar_auto;
    }

    public void setNumar_auto(String numar_auto) {
        this.numar_auto.set(numar_auto);
    }

    public boolean isEste_sef() {
        return este_sef;
    }

    public int getDepartament() {
        return Departament;
    }

    public int getIntervale_orare_acces() {
        return intervale_orare_acces;
    }
}
