/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

public enum Region {
    EUW("euw"), NA("na"), BR("br"), EUNE("eune"), KR("kr"),
    LAS("las"), LAN("lan"), OCE("oce"), TR("tr"), RU("ru"), STATIC("global");

    private final String mRegion;

    Region(String name) {
        this.mRegion = name;
    }

    public String getRegion() {
        return mRegion;
    }

    @Override
    public String toString() {
        return mRegion;
    }
}
