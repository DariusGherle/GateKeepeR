package com.example.gatekeepr.Classes;

import java.util.Objects;

public class Admin {
    private String adresa_utilizator;
    private String parola;

    public Admin(){}
    public Admin(String adresa_utilizator, String parola) {
        this.adresa_utilizator = adresa_utilizator;
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adresa_utilizator='" + adresa_utilizator + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

    public String getAdresa_utilizator() {
        return adresa_utilizator;
    }

    public void setAdresa_utilizator(String adresa_utilizator) {
        this.adresa_utilizator = adresa_utilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
