/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.request;

import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.LolRequest;

/**
 * Created by Don on 25.09.2014.
 */
public class ChampionRequest extends LolRequest {
    private final static String URL = "https://global.api.pvp.net/api/lol/static-data/{region}/v1.2/champion";

    public ChampionRequest(Region region) {
        super(region, URL, Champion.class);
    }
}
