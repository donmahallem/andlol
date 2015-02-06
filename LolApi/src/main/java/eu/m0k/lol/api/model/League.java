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

import java.util.ArrayList;

public class League {
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("tier")
    private Tier mTier;
    @Expose
    @SerializedName("queue")
    private Queue mQueue;
    @Expose
    @SerializedName("entries")
    private ArrayList<LeagueEntry> mLeagueEntries;

    public String getName() {
        return mName;
    }

    public Tier getTier() {
        return mTier;
    }

    public Queue getQueue() {
        return mQueue;
    }

    public ArrayList<LeagueEntry> getLeagueEntries() {
        return mLeagueEntries;
    }
}
