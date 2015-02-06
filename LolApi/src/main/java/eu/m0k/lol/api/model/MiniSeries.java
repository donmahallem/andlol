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

public class MiniSeries {
    @Expose
    @SerializedName("target")
    private int mTarget;
    @Expose
    @SerializedName("wins")
    private int mWins;
    @Expose
    @SerializedName("losses")
    private int mLosses;
    @Expose
    @SerializedName("progress")
    private String mProgess;

    public int getTarget() {
        return mTarget;
    }

    public int getWins() {
        return mWins;
    }

    public int getLosses() {
        return mLosses;
    }

    public String getProgess() {
        return mProgess;
    }
}
