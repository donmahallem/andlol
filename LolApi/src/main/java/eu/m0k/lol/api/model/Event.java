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
import java.util.Comparator;

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
    private long mTimestamp;
    public static Comparator<Event> SortDescending = new Comparator<Event>() {
        @Override
        public int compare(Event lhs, Event rhs) {
            if (lhs.getTimestamp() - rhs.getTimestamp() < 0)
                return -1;
            else if (lhs.getTimestamp() - rhs.getTimestamp() < 0)
                return 1;
            else
                return 0;
        }
    };
    @Expose
    @SerializedName("towerType")
    private TowerType mTowerType;
    @Expose
    @SerializedName("victimId")
    private int mVictimId;
    @Expose
    @SerializedName("wardType")
    private WardType mWardType;

    /**
     * The ascended type of the event. Only present if relevant. Note that CLEAR_ASCENDED refers to when a participants kills the ascended player.
     *
     * @return the type of ascension
     */
    public AscendedType getAscendedType() {
        return mAscendedType;
    }

    /**
     * The assisting participant IDs of the event. Only present if relevant.
     *
     * @return
     */
    public ArrayList<Integer> getAssistingParticipantIds() {
        return mAssistingParticipantIds;
    }

    /**
     * The building type of the event. Only present if relevant.
     *
     * @return
     */
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

    public long getTimestamp() {
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
