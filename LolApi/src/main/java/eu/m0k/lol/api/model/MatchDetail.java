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
import java.util.List;

public class MatchDetail {

    @Expose
    @SerializedName("matchId")
    private long mMatchId;
    @Expose
    @SerializedName("region")
    private Region mRegion;
    @Expose
    @SerializedName("matchMode")
    private MatchMode mMatchMode;
    @Expose
    @SerializedName("matchType")
    private MatchType mMatchType;
    @Expose
    @SerializedName("matchCreation")
    private long mMatchCreation;
    @Expose
    @SerializedName("matchDuration")
    private long mMatchDuration;
    @Expose
    @SerializedName("queueType")
    private Queue mQueueType;
    @Expose
    @SerializedName("mapId")
    private long mMapId;
    @Expose
    @SerializedName("season")
    private Season mSeason;
    @Expose
    @SerializedName("matchVersion")
    private String mMatchVersion;
    @Expose
    @SerializedName("participants")
    private ParticipantList mParticipants;
    @Expose
    @SerializedName("participantIdentities")
    private List<ParticipantIdentity> mParticipantIdentities;
    @Expose
    @SerializedName("teams")
    private ArrayList<Team> mTeams;
    @Expose
    @SerializedName("timeline")
    private TimeLine mTimeLine;

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

    public Region getRegion() {
        return mRegion;
    }

    public MatchMode getMatchMode() {
        return mMatchMode;
    }

    public MatchType getMatchType() {
        return mMatchType;
    }

    public long getMatchCreation() {
        return mMatchCreation;
    }

    public long getMatchDuration() {
        return mMatchDuration;
    }

    public Queue getQueueType() {
        return mQueueType;
    }

    public long getMapId() {
        return mMapId;
    }

    public Season getSeason() {
        return mSeason;
    }

    public String getMatchVersion() {
        return mMatchVersion;
    }

    public ParticipantList getParticipants() {
        return mParticipants;
    }

    public TimeLine getTimeLine() {
        return mTimeLine;
    }

    public List<ParticipantIdentity> getParticipantIdentities() {
        return mParticipantIdentities;
    }

    public ArrayList<Team> getTeams() {
        return mTeams;
    }
}
