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
import java.util.Comparator;

public class MatchSummary {
    public static Comparator<MatchSummary> SORT_DESC = new Comparator<MatchSummary>() {
        @Override
        public int compare(MatchSummary lhs, MatchSummary rhs) {
            if (lhs.getMatchCreation() < rhs.getMatchCreation())
                return 1;
            else if (lhs.getMatchCreation() > rhs.getMatchCreation())
                return -1;
            else return 0;
        }
    };
    @Expose
    @SerializedName("mapId")
    private int mMapId;
    @Expose
    @SerializedName("matchCreation")
    private long mMatchCreation;
    @Expose
    @SerializedName("matchDuration")
    private long mMatchDuration;
    @Expose
    @SerializedName("matchId")
    private int mMatchId;
    @Expose
    @SerializedName("matchMode")
    private MatchMode mMatchMode;
    @Expose
    @SerializedName("matchType")
    private MatchType mMatchType;
    @Expose
    @SerializedName("matchVersion")
    private String mMatchVersion;
    @Expose
    @SerializedName("participantIdentities")
    private ArrayList<ParticipantIdentity> mParticipantIdentitites;
    @Expose
    @SerializedName("participants")
    private ArrayList<Participant> mParticipants;
    @Expose
    @SerializedName("platformId")
    private String mPlatformId;
    @Expose
    @SerializedName("queueType")
    private Queue mQueueType;
    @Expose
    @SerializedName("region")
    private Region mRegion;
    @Expose
    @SerializedName("season")
    private Season mSeason;

    public int getMapId() {
        return mMapId;
    }

    public long getMatchCreation() {
        return mMatchCreation;
    }

    public long getMatchDuration() {
        return mMatchDuration;
    }

    public int getMatchId() {
        return mMatchId;
    }

    public MatchMode getMatchMode() {
        return mMatchMode;
    }

    public MatchType getMatchType() {
        return mMatchType;
    }

    public String getMatchVersion() {
        return mMatchVersion;
    }

    public ArrayList<ParticipantIdentity> getParticipantIdentitites() {
        return mParticipantIdentitites;
    }

    public ArrayList<Participant> getParticipants() {
        return mParticipants;
    }

    public String getPlatformId() {
        return mPlatformId;
    }

    public Queue getQueueType() {
        return mQueueType;
    }

    public Region getRegion() {
        return mRegion;
    }

    public Season getSeason() {
        return mSeason;
    }
}
