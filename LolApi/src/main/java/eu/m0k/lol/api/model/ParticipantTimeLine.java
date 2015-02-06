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

public class ParticipantTimeLine {
    @Expose
    @SerializedName("ancientGolemAssistsPerMinCounts")
    private ParticipantTimelineData mAncientGolemAssistsPerMinCounts;
    @Expose
    @SerializedName("ancientGolemKillsPerMinCounts")
    private ParticipantTimelineData mAncientGolemKillsPerMinCounts;
    @Expose
    @SerializedName("assistedLaneDeathsPerMinDeltas")
    private ParticipantTimelineData mAssistedLaneDeathsPerMinDeltas;
    @Expose
    @SerializedName("assistedLaneKillsPerMinDeltas")
    private ParticipantTimelineData mAssistedLaneKillsPerMinDeltas;
    @Expose
    @SerializedName("baronAssistsPerMinCounts")
    private ParticipantTimelineData mBaronAssistsPerMinCounts;
    @Expose
    @SerializedName("baronKillsPerMinCounts")
    private ParticipantTimelineData mBaronKillsPerMinCounts;
    @Expose
    @SerializedName("creepsPerMinDeltas")
    private ParticipantTimelineData mCreepsPerMinDeltas;
    @Expose
    @SerializedName("csDiffPerMinDeltas")
    private ParticipantTimelineData mCsDiffPerMinDeltas;
    @Expose
    @SerializedName("damageTakenDiffPerMinDeltas")
    private ParticipantTimelineData mDamageTakenDiffPerMinDeltas;
    @Expose
    @SerializedName("damageTakenPerMinDeltas")
    private ParticipantTimelineData mDamageTakenPerMinDeltas;
    @Expose
    @SerializedName("dragonAssistsPerMinCounts")
    private ParticipantTimelineData mDragonAssistsPerMinCounts;
    @Expose
    @SerializedName("dragonKillsPerMinCounts")
    private ParticipantTimelineData mDragonKillsPerMinCounts;
    @Expose
    @SerializedName("elderLizardAssistsPerMinCounts")
    private ParticipantTimelineData mElderLizardAssistsPerMinCounts;
    @Expose
    @SerializedName("elderLizardKillsPerMinCounts")
    private ParticipantTimelineData mElderLizardKillsPerMinCounts;
    @Expose
    @SerializedName("goldPerMinDeltas")
    private ParticipantTimelineData mGoldPerMinDeltas;
    @Expose
    @SerializedName("inhibitorAssistsPerMinCounts")
    private ParticipantTimelineData mInhibitorAssistsPerMinCounts;
    @Expose
    @SerializedName("inhibitorKillsPerMinCounts")
    private ParticipantTimelineData mInhibitorKillsPerMinCounts;
    @Expose
    @SerializedName("lane")
    private Lane mLane;
    @Expose
    @SerializedName("role")
    private Role mRole;
    @Expose
    @SerializedName("towerAssistsPerMinCounts")
    private ParticipantTimelineData mTowerAssistsPerMinCounts;
    @Expose
    @SerializedName("towerKillsPerMinCounts")
    private ParticipantTimelineData mTowerKillsPerMinCounts;
    @Expose
    @SerializedName("towerKillsPerMinDeltas")
    private ParticipantTimelineData mTowerKillsPerMinDeltas;
    @Expose
    @SerializedName("vilemawAssistsPerMinCounts")
    private ParticipantTimelineData mVilemawAssistsPerMinCounts;
    @Expose
    @SerializedName("vilemawKillsPerMinCounts")
    private ParticipantTimelineData mVilemawKillsPerMinCounts;
    @Expose
    @SerializedName("wardsPerMinDeltas")
    private ParticipantTimelineData mWardsPerMinDeltas;
    @Expose
    @SerializedName("xpDiffPerMinDeltas")
    private ParticipantTimelineData mXpDiffPerMinDeltas;
    @Expose
    @SerializedName("xpPerMinDeltas")
    private ParticipantTimelineData mXpPerMinDeltas;

    public ParticipantTimelineData getAncientGolemAssistsPerMinCounts() {
        return mAncientGolemAssistsPerMinCounts;
    }

    public ParticipantTimelineData getAncientGolemKillsPerMinCounts() {
        return mAncientGolemKillsPerMinCounts;
    }

    public ParticipantTimelineData getAssistedLaneDeathsPerMinDeltas() {
        return mAssistedLaneDeathsPerMinDeltas;
    }

    public ParticipantTimelineData getAssistedLaneKillsPerMinDeltas() {
        return mAssistedLaneKillsPerMinDeltas;
    }

    public ParticipantTimelineData getBaronAssistsPerMinCounts() {
        return mBaronAssistsPerMinCounts;
    }

    public ParticipantTimelineData getBaronKillsPerMinCounts() {
        return mBaronKillsPerMinCounts;
    }

    public ParticipantTimelineData getCreepsPerMinDeltas() {
        return mCreepsPerMinDeltas;
    }

    public ParticipantTimelineData getCsDiffPerMinDeltas() {
        return mCsDiffPerMinDeltas;
    }

    public ParticipantTimelineData getDamageTakenDiffPerMinDeltas() {
        return mDamageTakenDiffPerMinDeltas;
    }

    public ParticipantTimelineData getDamageTakenPerMinDeltas() {
        return mDamageTakenPerMinDeltas;
    }

    public ParticipantTimelineData getDragonAssistsPerMinCounts() {
        return mDragonAssistsPerMinCounts;
    }

    public ParticipantTimelineData getDragonKillsPerMinCounts() {
        return mDragonKillsPerMinCounts;
    }

    public ParticipantTimelineData getElderLizardAssistsPerMinCounts() {
        return mElderLizardAssistsPerMinCounts;
    }

    public ParticipantTimelineData getElderLizardKillsPerMinCounts() {
        return mElderLizardKillsPerMinCounts;
    }

    public ParticipantTimelineData getGoldPerMinDeltas() {
        return mGoldPerMinDeltas;
    }

    public ParticipantTimelineData getInhibitorAssistsPerMinCounts() {
        return mInhibitorAssistsPerMinCounts;
    }

    public ParticipantTimelineData getInhibitorKillsPerMinCounts() {
        return mInhibitorKillsPerMinCounts;
    }

    public Lane getLane() {
        return mLane;
    }

    public Role getRole() {
        return mRole;
    }

    public ParticipantTimelineData getTowerAssistsPerMinCounts() {
        return mTowerAssistsPerMinCounts;
    }

    public ParticipantTimelineData getTowerKillsPerMinCounts() {
        return mTowerKillsPerMinCounts;
    }

    public ParticipantTimelineData getTowerKillsPerMinDeltas() {
        return mTowerKillsPerMinDeltas;
    }

    public ParticipantTimelineData getVilemawAssistsPerMinCounts() {
        return mVilemawAssistsPerMinCounts;
    }

    public ParticipantTimelineData getVilemawKillsPerMinCounts() {
        return mVilemawKillsPerMinCounts;
    }

    public ParticipantTimelineData getWardsPerMinDeltas() {
        return mWardsPerMinDeltas;
    }

    public ParticipantTimelineData getXpDiffPerMinDeltas() {
        return mXpDiffPerMinDeltas;
    }

    public ParticipantTimelineData getXpPerMinDeltas() {
        return mXpPerMinDeltas;
    }
}
