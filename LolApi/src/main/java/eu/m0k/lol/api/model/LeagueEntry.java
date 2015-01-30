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

public class LeagueEntry {
    @Expose
    @SerializedName("playerOrTeamId")
    private String mPlayerOrTeamId;
    @Expose
    @SerializedName("playerOrTeamName")
    private String mPlayerOrTeamName;
    @Expose
    @SerializedName("division")
    private String mDivision;
    @Expose
    @SerializedName("leaguePoints")
    private int mLeaguePoints;
    @Expose
    @SerializedName("wins")
    private int mWins;
    @Expose
    @SerializedName("losses")
    private int mLosses;
    @Expose
    @SerializedName("isHotStreak")
    private boolean mIsHotStreak;
    @Expose
    @SerializedName("isVeteran")
    private boolean mIsVeteran;
    @Expose
    @SerializedName("isFreshBlood")
    private boolean mIsFreshBlood;
    @Expose
    @SerializedName("isInactive")
    private boolean mIsInactive;
    @Expose
    @SerializedName("miniSeries")
    private MiniSeries mMiniSeries;

    public String getPlayerOrTeamId() {
        return mPlayerOrTeamId;
    }

    public String getPlayerOrTeamName() {
        return mPlayerOrTeamName;
    }

    public String getDivision() {
        return mDivision;
    }

    public int getLeaguePoints() {
        return mLeaguePoints;
    }

    public int getWins() {
        return mWins;
    }

    public int getLosses() {
        return mLosses;
    }

    public boolean isHotStreak() {
        return mIsHotStreak;
    }

    public boolean isVeteran() {
        return mIsVeteran;
    }

    public boolean isFreshBlood() {
        return mIsFreshBlood;
    }

    public boolean isInactive() {
        return mIsInactive;
    }

    public MiniSeries getMiniSeries() {
        return mMiniSeries;
    }
}
