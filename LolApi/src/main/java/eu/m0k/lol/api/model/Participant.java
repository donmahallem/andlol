package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 23.09.2014.
 */
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
