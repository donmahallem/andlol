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
