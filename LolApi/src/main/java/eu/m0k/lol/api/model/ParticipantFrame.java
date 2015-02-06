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

public class ParticipantFrame {
    @Expose
    @SerializedName("currentGold")
    private int mCurrentGold;
    @Expose
    @SerializedName("jungleMinionsKilled")
    private int mJungleMinionsKilled;
    @Expose
    @SerializedName("level")
    private int mLevel;
    @Expose
    @SerializedName("minionsKilled")
    private int mMinionsKilled;
    @Expose
    @SerializedName("participantId")
    private int mParticipantId;
    @Expose
    @SerializedName("position")
    private Position mPosition;
    @Expose
    @SerializedName("totalGold")
    private int mTotalGold;
    @Expose
    @SerializedName("xp")
    private int mXp;

    public int getCurrentGold() {
        return mCurrentGold;
    }

    public int getJungleMinionsKilled() {
        return mJungleMinionsKilled;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getMinionsKilled() {
        return mMinionsKilled;
    }

    public int getParticipantId() {
        return mParticipantId;
    }

    public Position getPosition() {
        return mPosition;
    }

    public int getTotalGold() {
        return mTotalGold;
    }

    public int getXp() {
        return mXp;
    }
}
