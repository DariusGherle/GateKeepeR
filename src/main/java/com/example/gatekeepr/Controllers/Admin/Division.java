package com.example.gatekeepr.Controllers.Admin;

public enum Division {
    URGENTE(1),
    GENERAL(2),
    ONCOLOGIE(3),
    ALTELE(4);

    private final int value;

    Division(int value) {
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (Division division : Division.values()) {
            if (division.value == value) {
                return division.name();
            }
        }
        return "UNKNOWN";
    }
}
