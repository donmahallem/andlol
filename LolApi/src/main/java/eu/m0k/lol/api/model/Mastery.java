package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 23.09.2014.
 */
public class Mastery {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("rank")
    private int mRank;

    public int getId() {
        return mId;
    }

    public int getRank() {
        return mRank;
    }
}
