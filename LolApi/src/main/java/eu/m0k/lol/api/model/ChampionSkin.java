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

import eu.m0k.lol.api.picasso.Constants;

public class ChampionSkin {
    private final static String BASE_URL = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("num")
    private int mNum;
    /**
     * Champion key. Required to create URI
     */
    private String mKey;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getNum() {
        return mNum;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    public String getImageUri() {
        return Constants.PATH_IMG_SPELL + "://" + this.getKey() + "_" + this.getNum();
    }

    @Override
    public String toString() {
        return "ChampionSkin{" +
                "id=" + mId +
                ", name='" + mName + '\'' +
                ", num=" + mNum +
                '}';
    }
}
