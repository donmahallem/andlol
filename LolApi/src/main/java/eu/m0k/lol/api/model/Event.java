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

public class Event {

    @Expose
    @SerializedName("ascendedType")
    private AscendedType mAscendedType;
    @Expose
    @SerializedName("assistingParticipantIds")
    private ArrayList<Integer> mAssistingParticipantIds;
    @Expose
    @SerializedName("buildingType")
    private BuildingType mBuildingType;
    @Expose
    @SerializedName("creatorId")
    private int mCreatorId;
    @Expose
    @SerializedName("eventType")
    private EventType mEventType;
    @Expose
    @SerializedName("itemAfter")
    private int mItemAfter;
    @Expose
    @SerializedName("itemBefore")
    private int mItemBefore;
    @Expose
    @SerializedName("itemId")
    private int mItemId;
    @Expose
    @SerializedName("killerId")
    private int mKillerId;
    @Expose
    @SerializedName("laneType")
    private LaneType mLaneType;
    @Expose
    @SerializedName("levelUpType")
    private LevelUpType mLevelUpType;
    @Expose
    @SerializedName("monsterType")
    private MonsterType mMonsterType;
    @Expose
    @SerializedName("participantId")
    private int mParticipantId;
    @Expose
    @SerializedName("pointCaptured")
    private Point mPointCaptured;
    @Expose
    @SerializedName("position")
    private Position mPosition;
    @Expose
    @SerializedName("skillSlot")
    private int mSkillSlot;
    @Expose
    @SerializedName("teamId")
    private int mTeamId;
    @Expose
    @SerializedName("timestamp")
    private int mTimestamp;
    @Expose
    @SerializedName("towerType")
    private TowerType mTowerType;
    @Expose
    @SerializedName("victimId")
    private int mVictimId;
    @Expose
    @SerializedName("wardType")
    private WardType mWardType;

    public AscendedType getAscendedType() {
        return mAscendedType;
    }

    public ArrayList<Integer> getAssistingParticipantIds() {
        return mAssistingParticipantIds;
    }

    public BuildingType getBuildingType() {
        return mBuildingType;
    }

    public int getCreatorId() {
        return mCreatorId;
    }

    public EventType getEventType() {
        return mEventType;
    }

    public int getItemAfter() {
        return mItemAfter;
    }

    public int getItemBefore() {
        return mItemBefore;
    }

    public int getItemId() {
        return mItemId;
    }

    public int getKillerId() {
        return mKillerId;
    }

    public LaneType getLaneType() {
        return mLaneType;
    }

    public LevelUpType getLevelUpType() {
        return mLevelUpType;
    }

    public MonsterType getMonsterType() {
        return mMonsterType;
    }

    public int getParticipantId() {
        return mParticipantId;
    }

    public Point getPointCaptured() {
        return mPointCaptured;
    }

    public Position getPosition() {
        return mPosition;
    }

    public int getSkillSlot() {
        return mSkillSlot;
    }

    public int getTeamId() {
        return mTeamId;
    }

    public int getTimestamp() {
        return mTimestamp;
    }

    public TowerType getTowerType() {
        return mTowerType;
    }

    public int getVictimId() {
        return mVictimId;
    }

    public WardType getWardType() {
        return mWardType;
    }
}
