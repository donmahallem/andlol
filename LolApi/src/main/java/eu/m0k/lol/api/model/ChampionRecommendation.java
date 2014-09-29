package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampionRecommendation {
    @Expose
    @SerializedName("champion")
    private String mChampion;
    @Expose
    @SerializedName("title")
    private String mTitle;
    @Expose
    @SerializedName("type")
    private String mType;
    @Expose
    @SerializedName("map")
    private String mMap;
    @Expose
    @SerializedName("mode")
    private String mMode;
    @Expose
    @SerializedName("priority")
    private boolean mPriority;
    @Expose
    @SerializedName("blocks")
    private List<Block> mBlocks;

    public String getChampion() {
        return mChampion;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
    }

    public String getMap() {
        return mMap;
    }

    public String getMode() {
        return mMode;
    }

    public boolean isPriority() {
        return mPriority;
    }

    public List<Block> getBlocks() {
        return mBlocks;
    }

    public final static class Block {
        @Expose
        @SerializedName("type")
        private String mType;
        @Expose
        @SerializedName("items")
        private Item[] mItems;

        public String getType() {
            return mType;
        }

        public Item[] getItems() {
            return mItems;
        }
    }
}
