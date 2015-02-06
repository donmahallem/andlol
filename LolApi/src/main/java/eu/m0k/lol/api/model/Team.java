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

public class Team {
    @Expose
    @SerializedName("bans")
    private ArrayList<BannedChampion> mBans;
    @Expose
    @SerializedName("baronKills")
    private int mBaronKills;
    @Expose
    @SerializedName("dominionVictoryScore")
    private long mDominionVictoryScore;
    @Expose
    @SerializedName("dragonKills")
    private int mDragonKills;
    @Expose
    @SerializedName("firstBaron")
    private boolean mFirstBaron;
    @Expose
    @SerializedName("firstBlood")
    private boolean mFirstBlood;
    @Expose
    @SerializedName("firstDragon")
    private boolean mFirstDragon;
    @Expose
    @SerializedName("firstInhibitor")
    private boolean mFirstInhibitor;
    @Expose
    @SerializedName("firstTower")
    private boolean mFirstTower;
    @Expose
    @SerializedName("inhibitorKills")
    private int mInhibitorKills;
    @Expose
    @SerializedName("teamId")
    private int mTeamId;
    @Expose
    @SerializedName("towerKills")
    private int mTowerKills;
    @Expose
    @SerializedName("vilemawKills")
    private int mVilemawKills;
    @Expose
    @SerializedName("winner")
    private boolean mWinner;

    public ArrayList<BannedChampion> getBans() {
        return mBans;
    }

    public int getBaronKills() {
        return mBaronKills;
    }

    public long getDominionVictoryScore() {
        return mDominionVictoryScore;
    }

    public int getDragonKills() {
        return mDragonKills;
    }

    public boolean isFirstBaron() {
        return mFirstBaron;
    }

    public boolean isFirstBlood() {
        return mFirstBlood;
    }

    public boolean isFirstDragon() {
        return mFirstDragon;
    }

    public boolean isFirstInhibitor() {
        return mFirstInhibitor;
    }

    public boolean isFirstTower() {
        return mFirstTower;
    }

    public int getInhibitorKills() {
        return mInhibitorKills;
    }

    public int getTeamId() {
        return mTeamId;
    }

    public int getTowerKills() {
        return mTowerKills;
    }

    public int getVilemawKills() {
        return mVilemawKills;
    }

    public boolean isWinner() {
        return mWinner;
    }
}
