/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import eu.m0k.lol.api.model.Region;

public class LeagueEndpoint implements retrofit.Endpoint {
    private final static String NAME_STATIC = "StaticData", NAME_STATS = "StatsData";
    private final Region mRegion;
    private final String mStaticUrl, mStatsUrl;
    private boolean mStaticData;

    public LeagueEndpoint(Region region, boolean staticData) {
        this.mRegion = region;
        this.mStaticData = staticData;
        this.mStaticUrl = "https://" + this.mRegion.toString() + ".api.pvp.net/api/lol/" + this.mRegion.toString();
        this.mStatsUrl = "https://global.api.pvp.net/api/lol/static-data/" + this.mRegion.toString();
    }

    public Region getRegion() {
        return mRegion;
    }

    @Override
    public String getUrl() {
        if (this.mStaticData)
            return this.mStaticUrl;
        else
            return this.mStatsUrl;
    }

    @Override
    public String getName() {
        if (this.mStaticData) {
            return NAME_STATIC;
        } else
            return NAME_STATS;
    }
}
