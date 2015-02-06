/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannedChampion {
    @Expose
    @SerializedName("championId")
    private int mChampionId;
    @Expose
    @SerializedName("pickTurn")
    private int mPickTurn;

    public int getChampionId() {
        return mChampionId;
    }

    public int getPickTurn() {
        return mPickTurn;
    }
}
