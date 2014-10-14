/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

public enum LogLevel {
    NONE(0), BASIC(1), FULL(3);
    public final int LEVEL;

    LogLevel(int level) {
        this.LEVEL = level;
    }
}
