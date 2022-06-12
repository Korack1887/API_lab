package com.nure.API_lab.entities;

import java.util.Arrays;

public enum Role {
    USER, ADMIN;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
}
