/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import java.util.HashMap;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;

/**
 * Created by Don on 07.10.2014.
 */
public class Parameters extends HashMap<String, String> {
    private final String CHAMP_DATA = "champData", REGION = "region", LOCALE = "locale";

    public void put(ChampData champData) {
        if (champData != null)
            this.put(CHAMP_DATA, champData.getChampData());
    }

    public void put(Region region) {
        if (region != null)
            this.put(REGION, region.getRegion());
    }

    public void put(Locale locale) {
        if (locale != null)
            this.put(LOCALE, locale.getLocal());
    }
}
