/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.taktik.connector.business.recipeprojects.core.utils;

/**
 *
 * @author tvleminc
 */
public enum Qualities {
    DOCTOR("DOCTOR"),
    NURSE("NURSE"),
    MIDWIFE("MIDWIFE"),
    CITIZEN("CITIZEN"),
    DENTIST("DENTIST"),
    PHARMACY("PHARMACY"),
    HOSPITAL("HOSPITAL");
    private final String value;

    Qualities(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Qualities fromValue(String v) {
        for (Qualities c: Qualities.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
    
}
