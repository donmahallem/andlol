/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import java.util.ArrayList;

public class SummonerIds extends ArrayList<Long> {

    public static SummonerIds create(long... ids) {
        SummonerIds summonerIds = new SummonerIds();
        for (long id : ids)
            summonerIds.add(id);
        return summonerIds;
    }

    @Override
    public boolean add(Long string) {
        if (!this.contains(string)) {
            if (this.size() >= 40) {
                throw new IllegalArgumentException("Not more than 40 items in list allowed");
            } else
                super.add(string);
        }
        return true;
    }

    @Override
    public String toString() {
        String ret = "";
        for (Long name : this) {
            if (ret.length() != 0)
                ret += ",";
            ret += name;
        }
        return ret;
    }
}
