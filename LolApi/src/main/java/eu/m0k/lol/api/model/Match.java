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

import java.util.List;

public class Match {

    @Expose
    @SerializedName("matchId")
    private long mMatchId;
    @Expose
    @SerializedName("region")
    private String mRegion;
    @Expose
    @SerializedName("matchMode")
    private String mMatchMode;
    @Expose
    @SerializedName("matchType")
    private String mMatchType;
    @Expose
    @SerializedName("matchCreation")
    private long mMatchCreation;
    @Expose
    @SerializedName("matchDuration")
    private int mMatchDuration;
    @Expose
    @SerializedName("queueType")
    private String mQueueType;
    @Expose
    @SerializedName("mapId")
    private int mMapId;
    @Expose
    @SerializedName("season")
    private String mSeason;
    @Expose
    @SerializedName("matchVersion")
    private String mMatchVersion;
    @Expose
    @SerializedName("participants")
    private List<Participant> mParticipants;

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + mMatchId +
                ", region='" + mRegion +
                ", matchMode='" + mMatchMode +
                ", matchType='" + mMatchType +
                '}';
    }

    public long getMatchId() {
        return mMatchId;
    }

    public String getRegion() {
        return mRegion;
    }

    public String getMatchMode() {
        return mMatchMode;
    }

    public String getMatchType() {
        return mMatchType;
    }

    public long getMatchCreation() {
        return mMatchCreation;
    }

    public int getMatchDuration() {
        return mMatchDuration;
    }

    public String getQueueType() {
        return mQueueType;
    }

    public int getMapId() {
        return mMapId;
    }

    public String getSeason() {
        return mSeason;
    }

    public String getMatchVersion() {
        return mMatchVersion;
    }

    public List<Participant> getParticipants() {
        return mParticipants;
    }
}
