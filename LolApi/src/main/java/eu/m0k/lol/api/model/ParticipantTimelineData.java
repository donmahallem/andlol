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

public class ParticipantTimelineData {
    @Expose
    @SerializedName("tenToTwenty")
    private double mTenToTwenty;
    @Expose
    @SerializedName("thirtyToEnd")
    private double mThirtyToEnd;
    @Expose
    @SerializedName("twentyToThirty")
    private double mTwentyToThirty;
    @Expose
    @SerializedName("zeroToTen")
    private double mZeroToTen;

    public double getTenToTwenty() {
        return mTenToTwenty;
    }

    public double getThirtyToEnd() {
        return mThirtyToEnd;
    }

    public double getTwentyToThirty() {
        return mTwentyToThirty;
    }

    public double getZeroToTen() {
        return mZeroToTen;
    }
}
