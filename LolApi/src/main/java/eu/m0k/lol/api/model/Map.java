/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

public enum Map {
    /**
     * Summer Variant
     */
    SUMMONERS_RIFT_SUMMER_VARIANT(1),
    /**
     * Autuam Variant
     */
    SUMMONERS_RIFT_AUTUMN_VARIANT(2),
    /**
     * Tutorial Map
     */
    THE_PROVING_GROUNDS(3),
    /**
     * Original Version
     */
    TWISTED_TREELINE_ORIGINAL(4),
    /**
     * Dominion Map
     */
    THE_CRYSTAL_SCAR(8),
    /**
     * Current Version
     */
    TWISTED_TREELINE_NEW(10),
    /**
     * Aram Map
     */
    HOWLING_ABYSS(12);

    private final int mCode;

    Map(int code) {
        this.mCode = code;
    }

    public int getCode() {
        return mCode;
    }
}
