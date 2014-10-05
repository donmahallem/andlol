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

public class Mastery {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("rank")
    private int mRank;

    public int getId() {
        return mId;
    }

    public int getRank() {
        return mRank;
    }
}
