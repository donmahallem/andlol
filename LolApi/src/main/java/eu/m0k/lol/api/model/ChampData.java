package eu.m0k.lol.api.model;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampData {
    private final static String ALL = "all", ALLY_TIPS = "allytips", ALT_IMAGES = "altimages", BLURB = "blurb",
            ENEMY_TIPS = "enemytips",
            IMAGE = "image",
            INFO = "info",
            LORE = "lore",
            PAR_TYPE = "partype",
            PASSIVE = "passive",
            RECOMMENDED = "recommended",
            SKINS = "skins",
            SPELLS = "spells",
            STATS = "stats",
            TAGS = "tags";

    private boolean mAll, mAllyTips, mAltImages, mBlurb, mEnemyTips, mImage, mInfo, mLore, mParType, mPassive, mRecommended, mSkins, mSpells, mStats, mTags;

    public ChampData() {

    }

    public void setAll(boolean all) {
        mAll = all;
    }

    public void setAllyTips(boolean allyTips) {
        mAllyTips = allyTips;
    }

    public void setAltImages(boolean altImages) {
        mAltImages = altImages;
    }

    public void setBlurb(boolean blurb) {
        mBlurb = blurb;
    }

    public void setEnemyTips(boolean enemyTips) {
        mEnemyTips = enemyTips;
    }

    public void setImage(boolean image) {
        mImage = image;
    }

    public void setInfo(boolean info) {
        mInfo = info;
    }

    public void setLore(boolean lore) {
        mLore = lore;
    }

    public void setParType(boolean parType) {
        mParType = parType;
    }

    public void setPassive(boolean passive) {
        mPassive = passive;
    }

    public void setRecommended(boolean recommended) {
        mRecommended = recommended;
    }

    public void setSkins(boolean skins) {
        mSkins = skins;
    }

    public void setSpells(boolean spells) {
        mSpells = spells;
    }

    public void setStats(boolean stats) {
        mStats = stats;
    }

    public void setTags(boolean tags) {
        mTags = tags;
    }

    @Override
    public String toString() {
        if (this.mAll) {
            return ALL;
        }
        String ret = "";
        if (this.mAllyTips) {
            ret += ALLY_TIPS + ",";
        }
        if (this.mAltImages) {
            ret += ALT_IMAGES + ",";
        }
        if (this.mBlurb) {
            ret += BLURB + ",";
        }
        if (this.mEnemyTips) {
            ret += ENEMY_TIPS + ",";
        }
        if (this.mImage) {
            ret += IMAGE + ",";
        }
        if (this.mInfo) {
            ret += INFO + ",";
        }
        if (this.mLore) {
            ret += LORE + ",";
        }
        if (this.mParType) {
            ret += PAR_TYPE + ",";
        }
        if (this.mPassive) {
            ret += PASSIVE + ",";
        }
        if (this.mRecommended) {
            ret += RECOMMENDED + ",";
        }
        if (this.mSkins) {
            ret += SKINS + ",";
        }
        if (this.mSpells) {
            ret += SPELLS + ",";
        }
        if (this.mStats) {
            ret += STATS + ",";
        }
        if (this.mTags) {
            ret += TAGS + ",";
        }
        return ret;
    }
}
