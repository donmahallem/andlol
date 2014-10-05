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

public class Image {

    @Expose
    @SerializedName("full")
    private String mFull;
    @Expose
    @SerializedName("sprite")
    private String mSprite;
    @Expose
    @SerializedName("group")
    private String mGroup;
    @Expose
    @SerializedName("x")
    private int mX;
    @Expose
    @SerializedName("y")
    private int mY;
    @Expose
    @SerializedName("w")
    private int mW;
    @Expose
    @SerializedName("h")
    private int mH;

    public String getFull() {
        return mFull;
    }

    public String getSprite() {
        return mSprite;
    }

    public String getGroup() {
        return mGroup;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getW() {
        return mW;
    }

    public int getH() {
        return mH;
    }
}
