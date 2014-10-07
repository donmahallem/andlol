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
    private TimeLine mTimeLine;

}
