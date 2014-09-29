package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampionInfo {
    @Expose
    @SerializedName("attack")
    private int mAttack;
    @Expose
    @SerializedName("defense")
    private int mDefense;
    @Expose
    @SerializedName("magic")
    private int mMagic;
    @Expose
    @SerializedName("difficulty")
    private int mDifficulty;

    public int getAttack() {
        return mAttack;
    }

    public int getDefense() {
        return mDefense;
    }

    public int getMagic() {
        return mMagic;
    }

    public int getDifficulty() {
        return mDifficulty;
    }
}
