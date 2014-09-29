package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 29.09.2014.
 */
public class Item {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("count")
    private int mCount;

    public int getId() {
        return mId;
    }

    public int getCount() {
        return mCount;
    }
}
