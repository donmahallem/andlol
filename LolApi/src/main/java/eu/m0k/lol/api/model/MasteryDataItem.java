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

import java.util.ArrayList;

public class MasteryDataItem {

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("description")
    private ArrayList<String> mDescription;
    @Expose
    @SerializedName("image")
    private Image mImage;
    @Expose
    @SerializedName("masteryTree")
    private MasteryTreeType mMasteryTree;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("prereq")
    private String mPrerequirement;
    @Expose
    @SerializedName("ranks")
    private int mRanks;
    @Expose
    @SerializedName("sanitizedDescription")
    private ArrayList<String> mSanitizedDescription;

    public int getId() {
        return mId;
    }

    public ArrayList<String> getDescription() {
        return mDescription;
    }

    public Image getImage() {
        return mImage;
    }

    public MasteryTreeType getMasteryTree() {
        return mMasteryTree;
    }

    public String getName() {
        return mName;
    }

    public String getPrerequirement() {
        return mPrerequirement;
    }

    public int getRanks() {
        return mRanks;
    }

    public ArrayList<String> getSanitizedDescription() {
        return mSanitizedDescription;
    }
}
