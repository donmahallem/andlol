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

import java.util.List;

public class MasteryPage {
    @Expose
    @SerializedName("id")
    private long mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("current")
    private boolean mCurrent;
    @Expose
    @SerializedName("masteries")
    private List<Mastery> mMasteries;

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isCurrent() {
        return mCurrent;
    }

    public List<Mastery> getMasteries() {
        return mMasteries;
    }
}
