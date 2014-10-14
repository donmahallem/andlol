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

import java.util.List;

import eu.m0k.lol.api.picasso.Constants;

public class Champion {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("key")
    private String mKey;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("image")
    private Image mImage;
    @Expose
    @SerializedName("title")
    private String mTitle;
    @Expose
    @SerializedName("lore")
    private String mLore;
    @Expose
    @SerializedName("blurb")
    private String mBlurb;
    @Expose
    @SerializedName("allytips")
    private String[] mAllyTips;
    @Expose
    @SerializedName("enemytips")
    private String[] mEnemyTips;
    @Expose
    @SerializedName("tags")
    private String[] mTags;
    @Expose
    @SerializedName("partype")
    private String mParType;
    @Expose
    @SerializedName("skins")
    private List<ChampionSkin> mSkins;
    @Expose
    @SerializedName("info")
    private ChampionInfo mInfo;
    @Expose
    @SerializedName("stats")
    private ChampionStats mStats;
    @Expose
    @SerializedName("spells")
    private List<ChampionSpell> mSpells;
    @Expose
    @SerializedName("passive")
    private ChampionPassive mPassive;
    @Expose
    @SerializedName("recommended")
    private List<ChampionRecommendation> mRecommended;

    /**
     * Gets the Champion Lore
     *
     * @return the Champion Lore if queried or null
     */
    public String getLore() {
        return mLore;
    }

    public Image getImage() {
        return mImage;
    }

    public String getBlurb() {
        return mBlurb;
    }

    public String[] getAllyTips() {
        return mAllyTips;
    }

    public String[] getEnemyTips() {
        return mEnemyTips;
    }

    public String[] getTags() {
        return mTags;
    }

    public String getParType() {
        return mParType;
    }

    public List<ChampionSkin> getSkins() {
        //TODO: FIX THIS SOMEWHERE IN THE FUTURE OR WON'T FIX!
        if (this.mSkins != null && this.mSkins.size() > 0 && this.mSkins.get(0).getKey() == null) {
            for (ChampionSkin skin : this.mSkins) {
                skin.setKey(this.mKey);
            }
        }
        return mSkins;
    }

    public ChampionInfo getInfo() {
        return mInfo;
    }

    public ChampionStats getStats() {
        return mStats;
    }

    public List<ChampionSpell> getSpells() {
        return mSpells;
    }

    public ChampionPassive getPassive() {
        return mPassive;
    }

    public List<ChampionRecommendation> getRecommended() {
        return mRecommended;
    }

    public int getId() {
        return mId;
    }

    public String getKey() {
        return mKey;
    }

    public String getName() {
        return mName;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUri() {
        return Constants.PATH_IMG_CHAMPION_ICON + this.getKey();
    }

    @Override
    public String toString() {
        return "Champion{" +
                "id=" + mId +
                ", key='" + mKey + '\'' +
                ", name='" + mName + '\'' +
                '}';
    }
}
