package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Don on 23.09.2014.
 */
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
}
