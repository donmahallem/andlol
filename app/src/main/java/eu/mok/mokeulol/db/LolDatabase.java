/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.db;

import android.content.Context;

import eu.m0k.lol.api.model.Champion;

public class LolDatabase {
    private LolDatabaseHelper mLolDatabaseHelper;

    public LolDatabase(Context context) {
        this.mLolDatabaseHelper = new LolDatabaseHelper(context);
    }

    public void favoriteChamp(Champion champion) {
        if (champion != null)
            favoriteChamp(champion.getId());
    }

    public void favoriteChamp(int championId) {

    }

    public void unfavoriteChamp(Champion champion) {
        if (champion != null)
            unfavoriteChamp(champion.getId());
    }

    public void unfavoriteChamp(int championId) {

    }
}

