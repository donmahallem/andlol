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
