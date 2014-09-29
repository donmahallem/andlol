package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 23.09.2014.
 */
public class TimeLine {
    @Expose
    @SerializedName("creepsPerMinDeltas")
    private TimeLineItem mCreepsPerMinDeltas;
    @Expose
    @SerializedName("xpPerMinDeltas")
    private TimeLineItem mXpPerMinDeltas;
    @Expose
    @SerializedName("goldPerMinDeltas")
    private TimeLineItem mGoldPerMinDeltas;
    @Expose
    @SerializedName("csDiffPerMinDeltas")
    private TimeLineItem mCsDiffPerMinDeltas;
    @Expose
    @SerializedName("xpDiffPerMinDeltas")
    private TimeLineItem mXpDiffPerMinDeltas;
    @Expose
    @SerializedName("damageTakenPerMinDeltas")
    private TimeLineItem mDamageTakenPerMinDeltas;
    @Expose
    @SerializedName("damageTakenDiffPerMinDeltas")
    private TimeLineItem mDamageTakenDiffPerMinDeltas;

    public TimeLineItem getCreepsPerMinDeltas() {
        return mCreepsPerMinDeltas;
    }

    public TimeLineItem getXpPerMinDeltas() {
        return mXpPerMinDeltas;
    }

    public TimeLineItem getGoldPerMinDeltas() {
        return mGoldPerMinDeltas;
    }

    public TimeLineItem getCsDiffPerMinDeltas() {
        return mCsDiffPerMinDeltas;
    }

    public TimeLineItem getXpDiffPerMinDeltas() {
        return mXpDiffPerMinDeltas;
    }

    public TimeLineItem getDamageTakenPerMinDeltas() {
        return mDamageTakenPerMinDeltas;
    }

    public TimeLineItem getDamageTakenDiffPerMinDeltas() {
        return mDamageTakenDiffPerMinDeltas;
    }

    public static class TimeLineItem {
        @Expose
        @SerializedName("zeroToTen")
        private float mZeroToTen;
        @Expose
        @SerializedName("tenToTwenty")
        private float mTenToTwenty;
        @Expose
        @SerializedName("twentyToThirty")
        private float mTwentyToThirty;
        @Expose
        @SerializedName("thirtyToEnd")
        private float mThirtyToEnd;

        public float getZeroToTen() {
            return mZeroToTen;
        }

        public float getTenToTwenty() {
            return mTenToTwenty;
        }

        public float getTwentyToThirty() {
            return mTwentyToThirty;
        }

        public float getThirtyToEnd() {
            return mThirtyToEnd;
        }
    }
}
