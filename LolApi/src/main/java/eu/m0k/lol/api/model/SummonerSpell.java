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

public class SummonerSpell {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("key")
    private String mKey;
    @Expose
    @SerializedName("summonerLevel")
    private int mSummonerLevel;

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public String getKey() {
        return mKey;
    }

    public int getSummonerLevel() {
        return mSummonerLevel;
    }

    public String getIconUri() {
        return Constants.PATH_IMG_SPELL + this.getKey();
    }
}
