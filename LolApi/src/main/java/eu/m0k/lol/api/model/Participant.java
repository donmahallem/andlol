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

public class Participant {
    @Expose
    @SerializedName("participantId")
    private int mParticipantId;
    @Expose
    @SerializedName("teamId")
    private int mTeamId;
    @Expose
    @SerializedName("championId")
    private int mChampionId;
    @Expose
    @SerializedName("spell1Id")
    private int mSpell1Id;
    @Expose
    @SerializedName("spell2Id")
    private int mSpell2Id;
    @Expose
    @SerializedName("stats")
    private ParticipantStats mStats;
    @Expose
    @SerializedName("timeline")
    private ParticipantTimeLine mTimeLine;

    public int getParticipantId() {
        return mParticipantId;
    }

    public int getTeamId() {
        return mTeamId;
    }

    public int getChampionId() {
        return mChampionId;
    }

    public int getSpell1Id() {
        return mSpell1Id;
    }

    public int getSpell2Id() {
        return mSpell2Id;
    }

    public ParticipantStats getStats() {
        return mStats;
    }

    public ParticipantTimeLine getTimeLine() {
        return mTimeLine;
    }
}
