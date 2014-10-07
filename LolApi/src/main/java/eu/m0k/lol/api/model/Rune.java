/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rune {
    @Expose
    @SerializedName("runeSlotId")
    private int mRuneSlotId;
    @Expose
    @SerializedName("runeId")
    private int mRuneId;

    public int getRuneSlotId() {
        return mRuneSlotId;
    }

    public int getRuneId() {
        return mRuneId;
    }
}
