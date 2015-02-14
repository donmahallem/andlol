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

public class MasteryTreeItem {
    int x;
    int y;
    @Expose
    @SerializedName("prereq")
    private String mPrerequirement;
    @Expose
    @SerializedName("masteryId")
    private int mMasteryId;

    public String getPrerequirement() {
        return mPrerequirement;
    }

    public int getMasteryId() {
        return mMasteryId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "MasteryTreeItem{" +
                "mPrerequirement='" + mPrerequirement + '\'' +
                ", mMasteryId=" + mMasteryId +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
