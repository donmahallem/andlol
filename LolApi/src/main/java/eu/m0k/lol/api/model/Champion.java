package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 25.09.2014.
 */
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
}
