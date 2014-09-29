package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 23.09.2014.
 */
public class Summoner {
    @Expose
    @SerializedName("id")
    private long mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("profileIconId")
    private int mProfileIconId;
    @Expose
    @SerializedName("revisionDate")
    private long mRevisionDate;
    @Expose
    @SerializedName("summonerLevel")
    private int mSummonerLevel;

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getProfileIconId() {
        return mProfileIconId;
    }

    public long getRevisionDate() {
        return mRevisionDate;
    }

    public int getSummonerLevel() {
        return mSummonerLevel;
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "name='" + mName + '\'' +
                ", id=" + mId +
                '}';
    }
}
