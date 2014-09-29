package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 23.09.2014.
 */
public class ParticipantStats {
    @Expose
    @SerializedName("winner")
    private boolean mWinner;
    @Expose
    @SerializedName("champLevel")
    private int mChampLevel;
    @Expose
    @SerializedName("teamId")
    private int mTeamId;
    @Expose
    @SerializedName("item1")
    private int mItem1;
    @Expose
    @SerializedName("item2")
    private int mItem2;
    @Expose
    @SerializedName("item3")
    private int mItem3;
    @Expose
    @SerializedName("item4")
    private int mItem4;
    @Expose
    @SerializedName("item5")
    private int mItem5;
    @Expose
    @SerializedName("item6")
    private int mItem6;
    @Expose
    @SerializedName("kills")
    private int mKills;
    @Expose
    @SerializedName("doubleKills")
    private int mDoubleKills;
    @Expose
    @SerializedName("tripleKills")
    private int mTripleKills;
    @Expose
    @SerializedName("quadraKills")
    private int mQuadraKills;
    @Expose
    @SerializedName("pentaKills")
    private int mPentaKills;
    @Expose
    @SerializedName("unrealKills")
    private int mUnrealKills;
    @Expose
    @SerializedName("largestKillingSpree")
    private int mLargestKillingSpree;
    @Expose
    @SerializedName("deaths")
    private int mDeaths;
    @Expose
    @SerializedName("assists")
    private int mAssists;
    @Expose
    @SerializedName("totalDamageDealt")
    private int mTotalDamageDealt;
    @Expose
    @SerializedName("totalDamageDealtToChampions")
    private int mTotalDamageDealtToChampions;
    @Expose
    @SerializedName("totalDamageTaken")
    private int mTotalDamageTaken;
    @Expose
    @SerializedName("largestCriticalStrike")
    private int mLargestCriticalStrike;
    @Expose
    @SerializedName("totalHeal")
    private int mTotalHeal;
    @Expose
    @SerializedName("minionsKilled")
    private int mMinionsKilled;
    @Expose
    @SerializedName("neutralMinionsKilled")
    private int mNeutralMinionsKilled;
    @Expose
    @SerializedName("neutralMinionsKilledTeamJungle")
    private int mNeutralMinionsKilledTeamJungle;
    @Expose
    @SerializedName("neutralMinionsKilledEnemyJungle")
    private int mNeutralMinionsKilledEnemyJungle;
    @Expose
    @SerializedName("goldEarned")
    private int mGoldEarned;
    @Expose
    @SerializedName("goldSpent")
    private int mGoldSpent;
    @Expose
    @SerializedName("combatPlayerScore")
    private int mCombatPlayerScore;
    @Expose
    @SerializedName("objectivePlayerScore")
    private int mObjectivePlayerScore;
    @Expose
    @SerializedName("totalPlayerScore")
    private int mTotalPlayerScore;
    @Expose
    @SerializedName("totalScoreRank")
    private int mTotalScoreRank;
    @Expose
    @SerializedName("magicDamageDealtToChampions")
    private int mMagicDamageDealtToChampions;
    @Expose
    @SerializedName("physicalDamageDealtToChampions")
    private int mPhysicalDamageDealtToChampions;
    @Expose
    @SerializedName("trueDamageDealtToChampions")
    private int mTrueDamageDealtToChampions;
    @Expose
    @SerializedName("visionWardsBoughtInGame")
    private int mVisionWardsBoughtInGame;
    @Expose
    @SerializedName("sightWardsBoughtInGame")
    private int mSightWardsBoughtInGame;
    @Expose
    @SerializedName("magicDamageDealt")
    private int mMagicDamageDealt;
    @Expose
    @SerializedName("physicalDamageDealt")
    private int mPhysicalDamageDealt;
    @Expose
    @SerializedName("trueDamageDealt")
    private int mTrueDamageDealt;
    @Expose
    @SerializedName("magicDamageTaken")
    private int mMagicDamageTaken;
    @Expose
    @SerializedName("physicalDamageTaken")
    private int mPhysicalDamageTaken;
    @Expose
    @SerializedName("trueDamageTaken")
    private int mTrueDamageTaken;
    @Expose
    @SerializedName("firstBloodKill")
    private boolean mFirstBloodKill;
    @Expose
    @SerializedName("firstBloodAssist")
    private boolean mFirstBloodAssist;
    @Expose
    @SerializedName("firstTowerKill")
    private boolean mFirstTowerKill;
    @Expose
    @SerializedName("firstTowerAssist")
    private boolean mFirstTowerAssist;
    @Expose
    @SerializedName("firstInhibitorKill")
    private boolean mFirstInhibitorKill;
    @Expose
    @SerializedName("firstInhibitorAssist")
    private boolean mFirstInhibitorAssist;
    @Expose
    @SerializedName("inhibitorKills")
    private int mInhibitorKills;
    @Expose
    @SerializedName("towerKills")
    private int mTowerKills;
    @Expose
    @SerializedName("wardsPlaced")
    private int mWardsPlaced;
    @Expose
    @SerializedName("wardsKilled")
    private int mWardsKilled;
    @Expose
    @SerializedName("largestMultiKill")
    private int mLargestMultiKill;
    @Expose
    @SerializedName("killingSprees")
    private int mKillingSprees;
    @Expose
    @SerializedName("totalUnitsHealed")
    private int mTotalUnitsHealed;
    @Expose
    @SerializedName("totalTimeCrowdControlDealt")
    private int mTotalTimeCrowdControlDealt;

    public boolean isWinner() {
        return mWinner;
    }

    public int getChampLevel() {
        return mChampLevel;
    }

    public int getTeamId() {
        return mTeamId;
    }

    public int getItem1() {
        return mItem1;
    }

    public int getItem2() {
        return mItem2;
    }

    public int getItem3() {
        return mItem3;
    }

    public int getItem4() {
        return mItem4;
    }

    public int getItem5() {
        return mItem5;
    }

    public int getItem6() {
        return mItem6;
    }

    public int getKills() {
        return mKills;
    }

    public int getDoubleKills() {
        return mDoubleKills;
    }

    public int getTripleKills() {
        return mTripleKills;
    }

    public int getQuadraKills() {
        return mQuadraKills;
    }

    public int getPentaKills() {
        return mPentaKills;
    }

    public int getUnrealKills() {
        return mUnrealKills;
    }

    public int getLargestKillingSpree() {
        return mLargestKillingSpree;
    }

    public int getDeaths() {
        return mDeaths;
    }

    public int getAssists() {
        return mAssists;
    }

    public int getTotalDamageDealt() {
        return mTotalDamageDealt;
    }

    public int getTotalDamageDealtToChampions() {
        return mTotalDamageDealtToChampions;
    }

    public int getTotalDamageTaken() {
        return mTotalDamageTaken;
    }

    public int getLargestCriticalStrike() {
        return mLargestCriticalStrike;
    }

    public int getTotalHeal() {
        return mTotalHeal;
    }

    public int getMinionsKilled() {
        return mMinionsKilled;
    }

    public int getNeutralMinionsKilled() {
        return mNeutralMinionsKilled;
    }

    public int getNeutralMinionsKilledTeamJungle() {
        return mNeutralMinionsKilledTeamJungle;
    }

    public int getNeutralMinionsKilledEnemyJungle() {
        return mNeutralMinionsKilledEnemyJungle;
    }

    public int getGoldEarned() {
        return mGoldEarned;
    }

    public int getGoldSpent() {
        return mGoldSpent;
    }

    public int getCombatPlayerScore() {
        return mCombatPlayerScore;
    }

    public int getObjectivePlayerScore() {
        return mObjectivePlayerScore;
    }

    public int getTotalPlayerScore() {
        return mTotalPlayerScore;
    }

    public int getTotalScoreRank() {
        return mTotalScoreRank;
    }

    public int getMagicDamageDealtToChampions() {
        return mMagicDamageDealtToChampions;
    }

    public int getPhysicalDamageDealtToChampions() {
        return mPhysicalDamageDealtToChampions;
    }

    public int getTrueDamageDealtToChampions() {
        return mTrueDamageDealtToChampions;
    }

    public int getVisionWardsBoughtInGame() {
        return mVisionWardsBoughtInGame;
    }

    public int getSightWardsBoughtInGame() {
        return mSightWardsBoughtInGame;
    }

    public int getMagicDamageDealt() {
        return mMagicDamageDealt;
    }

    public int getPhysicalDamageDealt() {
        return mPhysicalDamageDealt;
    }

    public int getTrueDamageDealt() {
        return mTrueDamageDealt;
    }

    public int getMagicDamageTaken() {
        return mMagicDamageTaken;
    }

    public int getPhysicalDamageTaken() {
        return mPhysicalDamageTaken;
    }

    public int getTrueDamageTaken() {
        return mTrueDamageTaken;
    }

    public boolean isFirstBloodKill() {
        return mFirstBloodKill;
    }

    public boolean isFirstBloodAssist() {
        return mFirstBloodAssist;
    }

    public boolean isFirstTowerKill() {
        return mFirstTowerKill;
    }

    public boolean isFirstTowerAssist() {
        return mFirstTowerAssist;
    }

    public boolean isFirstInhibitorKill() {
        return mFirstInhibitorKill;
    }

    public boolean isFirstInhibitorAssist() {
        return mFirstInhibitorAssist;
    }

    public int getInhibitorKills() {
        return mInhibitorKills;
    }

    public int getTowerKills() {
        return mTowerKills;
    }

    public int getWardsPlaced() {
        return mWardsPlaced;
    }

    public int getWardsKilled() {
        return mWardsKilled;
    }

    public int getLargestMultiKill() {
        return mLargestMultiKill;
    }

    public int getKillingSprees() {
        return mKillingSprees;
    }

    public int getTotalUnitsHealed() {
        return mTotalUnitsHealed;
    }

    public int getTotalTimeCrowdControlDealt() {
        return mTotalTimeCrowdControlDealt;
    }
}
