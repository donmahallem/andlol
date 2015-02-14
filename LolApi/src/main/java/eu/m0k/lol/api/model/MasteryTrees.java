/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasteryTrees {
    @Expose
    @SerializedName("Offense")
    private MasteryTree mOffense;
    @Expose
    @SerializedName("Defense")
    private MasteryTree mDefense;
    @Expose
    @SerializedName("Utility")
    private MasteryTree mUtility;

    public MasteryTree getOffense() {
        return mOffense;
    }

    public MasteryTree getDefense() {
        return mDefense;
    }

    public MasteryTree getUtility() {
        return mUtility;
    }

}
