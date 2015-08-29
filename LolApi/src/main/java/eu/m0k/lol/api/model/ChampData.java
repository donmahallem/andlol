/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import java.util.HashMap;

public class ChampData {
    public final static ChampData ALL_DATA = new ChampData(true);
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
    private HashMap<String, Boolean> mChampData = new HashMap<String, Boolean>();

    public ChampData() {
        this(false);
    }

    public ChampData(boolean all) {
        this.setAll(all);
    }

    public void setAll(boolean all) {
        this.mChampData.clear();
        this.mChampData.put(ALL, all);
    }

    public void setAllyTips(boolean allyTips) {
        this.mChampData.put(ALLY_TIPS, allyTips);
    }

    public void setAltImages(boolean altImages) {
        this.mChampData.put(ALT_IMAGES, altImages);
    }

    public void setBlurb(boolean blurb) {
        this.mChampData.put(BLURB, blurb);
    }

    public void setEnemyTips(boolean enemyTips) {
        this.mChampData.put(ENEMY_TIPS, enemyTips);
    }

    public void setImage(boolean image) {
        this.mChampData.put(IMAGE, image);
    }

    public void setInfo(boolean info) {
        this.mChampData.put(INFO, info);
    }

    public void setLore(boolean lore) {
        this.mChampData.put(LORE, lore);
    }

    public void setParType(boolean parType) {
        this.mChampData.put(PAR_TYPE, parType);
    }

    public void setPassive(boolean passive) {
        this.mChampData.put(PASSIVE, passive);
    }

    public void setRecommended(boolean recommended) {
        this.mChampData.put(RECOMMENDED, recommended);
    }

    public void setSkins(boolean skins) {
        this.mChampData.put(SKINS, skins);
    }

    public void setSpells(boolean spells) {
        this.mChampData.put(SPELLS, spells);
    }

    public void setStats(boolean stats) {
        this.mChampData.put(STATS, stats);
    }

    public void setTags(boolean tags) {
        this.mChampData.put(TAGS, tags);
    }

    @Override
    public String toString() {
        return this.getChampData();
    }


    public String getChampData() {
        if (this.mChampData.get(ALL) == true)
            return ALL;
        StringBuilder ret = new StringBuilder();
        for (String key : this.mChampData.keySet()) {
            final boolean value = this.mChampData.get(key);
            if (!value)
                continue;
            if (ret.length() > 0)
                ret.append(",");
            ret.append(key);
        }
        return ret.toString();
    }
}
