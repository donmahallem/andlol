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

public class ChampionStats {
    @Expose
    @SerializedName("armor")
    private double mArmor;
    @Expose
    @SerializedName("armorperlevel")
    private double mArmorPerLevel;
    @Expose
    @SerializedName("attackdamage")
    private double mAttackDamage;
    @Expose
    @SerializedName("attackdamageperlevel")
    private double mAttackDamagePerLevel;
    @Expose
    @SerializedName("attackrange")
    private double mAttackRange;
    @Expose
    @SerializedName("attackspeedoffset")
    private double mAttackSpeedOffset;
    @Expose
    @SerializedName("attackspeedperlevel")
    private double mAttackSpeedPerLevel;
    @Expose
    @SerializedName("crit")
    private double mCrit;
    @Expose
    @SerializedName("critperlevel")
    private double mCritPerLevel;
    @Expose
    @SerializedName("hp")
    private double mHp;
    @Expose
    @SerializedName("hpperlevel")
    private double mHpPerLevel;
    @Expose
    @SerializedName("hpregen")
    private double mHpRegen;
    @Expose
    @SerializedName("hpregenperlevel")
    private double mHpRegenPerLevel;
    @Expose
    @SerializedName("movespeed")
    private double mMoveSpeed;
    @Expose
    @SerializedName("mp")
    private double mMp;
    @Expose
    @SerializedName("mpperlevel")
    private double mMpPerLevel;
    @Expose
    @SerializedName("mpregen")
    private double mMpRegen;
    @Expose
    @SerializedName("mpregenperlevel")
    private double mMpRegenPerLevel;
    @Expose
    @SerializedName("spellblock")
    private double mSpellBlock;
    @Expose
    @SerializedName("spellblockperlevel")
    private double mSpellBlockPerLevel;

    public double getArmor() {
        return mArmor;
    }

    public double getArmorPerLevel() {
        return mArmorPerLevel;
    }

    public double getAttackDamage() {
        return mAttackDamage;
    }

    public double getAttackDamagePerLevel() {
        return mAttackDamagePerLevel;
    }

    public double getAttackRange() {
        return mAttackRange;
    }

    public double getAttackSpeedOffset() {
        return mAttackSpeedOffset;
    }

    public double getAttackSpeedPerLevel() {
        return mAttackSpeedPerLevel;
    }

    public double getCrit() {
        return mCrit;
    }

    public double getCritPerLevel() {
        return mCritPerLevel;
    }

    public double getHp() {
        return mHp;
    }

    public double getHpPerLevel() {
        return mHpPerLevel;
    }

    public double getHpRegen() {
        return mHpRegen;
    }

    public double getHpRegenPerLevel() {
        return mHpRegenPerLevel;
    }

    public double getMoveSpeed() {
        return mMoveSpeed;
    }

    public double getMp() {
        return mMp;
    }

    public double getMpPerLevel() {
        return mMpPerLevel;
    }

    public double getMpRegen() {
        return mMpRegen;
    }

    public double getMpRegenPerLevel() {
        return mMpRegenPerLevel;
    }

    public double getSpellBlock() {
        return mSpellBlock;
    }

    public double getSpellBlockPerLevel() {
        return mSpellBlockPerLevel;
    }
}
