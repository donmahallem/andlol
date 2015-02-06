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

public class Player {
    @Expose
    @SerializedName("matchHistoryUri")
    private String mMatchHistoryUri;
    @Expose
    @SerializedName("profileIcon")
    private int mProfileIcon;
    @Expose
    @SerializedName("summonerId")
    private long mSummonerId;
    @Expose
    @SerializedName("summonerName")
    private String mSummonerName;

    public String getSummonerName() {
        return mSummonerName;
    }

    public String getMatchHistoryUri() {
        return mMatchHistoryUri;
    }

    public int getProfileIcon() {
        return mProfileIcon;
    }

    public long getSummonerId() {
        return mSummonerId;
    }
}
