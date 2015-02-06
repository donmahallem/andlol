/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import java.util.ArrayList;

public class SummonerNames extends ArrayList<String> {

    public static SummonerNames create(String... searchQuery) {
        SummonerNames summonerNames = new SummonerNames();
        for (String name : searchQuery) {
            summonerNames.add(name);
        }
        return summonerNames;
    }

    @Override
    public boolean add(String string) {
        if (!this.contains(string))
            if (this.size() >= 40) {
                throw new IllegalArgumentException("Not more than 40 items in list allowed");
            } else
                super.add(string);
        return true;
    }

    @Override
    public String toString() {
        String ret = "";
        for (String name : this) {
            if (ret.length() != 0)
                ret += ",";
            ret += name;
        }
        return ret;
    }
}
