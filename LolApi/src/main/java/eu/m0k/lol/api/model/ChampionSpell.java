package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampionSpell {
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("sanitizedDescription")
    private String mSanitizedDescription;
    @Expose
    @SerializedName("tooltip")
    private String mTooltip;
    @Expose
    @SerializedName("sanitizedTooltip")
    private String mSanitizedTooltip;
    //TODO: LevelTip
    @Expose
    @SerializedName("image")
    private Image mImage;
    @Expose
    @SerializedName("resource")
    private String mResource;
    @Expose
    @SerializedName("maxrank")
    private int mMaxRank;
    @Expose
    @SerializedName("cost")
    private int[] mCost;
    @Expose
    @SerializedName("costType")
    private String mCostType;
    @Expose
    @SerializedName("costBurn")
    private String mCostBurn;
    @Expose
    @SerializedName("cooldown")
    private float[] mCooldown;
    @Expose
    @SerializedName("cooldownBurn")
    private String mCooldownBurn;
    @Expose
    @SerializedName("effect")
    private double[][] mEffect;
    @Expose
    @SerializedName("effectBurn")
    private String[] mEffectBurn;
    @Expose
    @SerializedName("range")
    private int[] mRange;
    @Expose
    @SerializedName("rangeBurn")
    private String mRangeBurn;
    @Expose
    @SerializedName("key")
    private String mKey;

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getSanitizedDescription() {
        return mSanitizedDescription;
    }

    public String getTooltip() {
        return mTooltip;
    }

    public String getSanitizedTooltip() {
        return mSanitizedTooltip;
    }

    public Image getImage() {
        return mImage;
    }

    public String getResource() {
        return mResource;
    }

    public int getMaxRank() {
        return mMaxRank;
    }

    public int[] getCost() {
        return mCost;
    }

    public String getCostType() {
        return mCostType;
    }

    public String getCostBurn() {
        return mCostBurn;
    }

    public float[] getCooldown() {
        return mCooldown;
    }

    public String getCooldownBurn() {
        return mCooldownBurn;
    }

    public double[][] getEffect() {
        return mEffect;
    }

    public String[] getEffectBurn() {
        return mEffectBurn;
    }

    public int[] getRange() {
        return mRange;
    }

    public String getRangeBurn() {
        return mRangeBurn;
    }

    public String getKey() {
        return mKey;
    }
}
