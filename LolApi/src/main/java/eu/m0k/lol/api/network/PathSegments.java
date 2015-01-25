/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import java.util.HashMap;

import eu.m0k.lol.api.model.Region;


public class PathSegments extends HashMap<String, String> {
    public final static String REGION = "region", MATCH_ID = "matchId", CHAMP_ID = "champId", SUMMONER_ID = "summonerId", ID = "id";

    public void put(Region region) {
        this.put(REGION, region.getRegion());
    }

    public void putMatchId(long number) {
        this.put(MATCH_ID, number);
    }

    public void putChampId(int number) {
        this.put(CHAMP_ID, number);
    }

    public void put(String string, int number) {
        if (string != null)
            this.put(string, "" + number);
    }

    public void put(String string, long number) {
        if (string != null)
            this.put(string, "" + number);
    }

    public void putSummonerId(long summonerId) {
        this.put(SUMMONER_ID, summonerId);
    }
}
