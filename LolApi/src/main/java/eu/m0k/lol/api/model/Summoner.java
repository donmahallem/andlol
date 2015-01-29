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

import eu.m0k.lol.api.picasso.Constants;

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

    public String getProfileIcon() {
        return Constants.PATH_IMG_PROFILE_ICON + this.getProfileIconId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Summoner summoner = (Summoner) o;

        if (mId != summoner.mId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "name='" + mName + '\'' +
                ", id=" + mId +
                '}';
    }
}
