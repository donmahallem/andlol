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

public class CurrentGameInfo {
    @Expose
    @SerializedName("bannedChampions")
    private ArrayList<BannedChampion> mBannedChampions;
    @Expose
    @SerializedName("gameId")
    private long mGameId;
    @Expose
    @SerializedName("gameLength")
    private long mGameLength;
    @Expose
    @SerializedName("gameMode")
    private MatchMode mGameMode;
    @Expose
    @SerializedName("gameQueueConfigId")
    private long mGameQueueConfigId;
    @Expose
    @SerializedName("gameStartTime")
    private long mGameStartTime;
    @Expose
    @SerializedName("gameType")
    private GameType mGameType;
    @Expose
    @SerializedName("observers")
    private Observer mObservers;
    @Expose
    @SerializedName("participants")
    private ParticipantList mParticipants;
    @Expose
    @SerializedName("platformId")
    private Platform mPlatformId;

    public ArrayList<BannedChampion> getBannedChampions() {
        return mBannedChampions;
    }

    public long getGameId() {
        return mGameId;
    }

    public long getGameLength() {
        return mGameLength;
    }

    public MatchMode getGameMode() {
        return mGameMode;
    }

    public long getGameQueueConfigId() {
        return mGameQueueConfigId;
    }

    public long getGameStartTime() {
        return mGameStartTime;
    }

    public GameType getGameType() {
        return mGameType;
    }

    public Observer getObservers() {
        return mObservers;
    }

    public ParticipantList getParticipants() {
        return mParticipants;
    }

    public Platform getPlatformId() {
        return mPlatformId;
    }

    @Override
    public String toString() {
        return "CurrentGameInfo{" +
                "mGameId=" + mGameId +
                ", mParticipants=" + mParticipants.size() +
                ", mPlatformId=" + mPlatformId +
                '}';
    }
}
