/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

/**
 * Created by Don on 23.09.2014.
 */
public enum Region {
    EUW("euw"), NA("na"), BR("br"), EUNE("eune"), KR("kr"), LAS("las"), LAN("lan"), OCE("oce"), TR("tr"), RU("ru");

    private final String mRegion;

    Region(String name) {
        this.mRegion = name;
    }

    public String getRegion() {
        return mRegion;
    }

    @Override
    public String toString() {
        return "Region{" +
                "region='" + mRegion + '\'' +
                '}';
    }
}
