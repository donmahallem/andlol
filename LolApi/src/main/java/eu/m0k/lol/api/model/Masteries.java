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

public class Masteries {

    @Expose
    @SerializedName("pages")
    private List<MasteryPage> mPages;
    @Expose
    @SerializedName("summonerId")
    private long mSummonerId;

    public long getSummonerId() {
        return mSummonerId;
    }

    public List<MasteryPage> getPages() {
        return mPages;
    }

    @Override
    public String toString() {
        return "Masteries{" +
                "summonerId=" + mSummonerId +
                ", mPages=" + mPages.size() +
                '}';
    }
}
