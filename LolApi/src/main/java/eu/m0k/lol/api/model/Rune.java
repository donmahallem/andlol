package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 23.09.2014.
 */
public class Rune {
    @Expose
    @SerializedName("runeSlotId")
    private int mRuneSlotId;
    @Expose
    @SerializedName("runeId")
    private int mRuneId;

    public int getRuneSlotId() {
        return mRuneSlotId;
    }

    public int getRuneId() {
        return mRuneId;
    }
}
