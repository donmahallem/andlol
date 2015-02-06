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


}
