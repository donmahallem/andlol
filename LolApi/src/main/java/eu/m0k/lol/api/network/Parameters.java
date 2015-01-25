/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import java.util.HashMap;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Locale;

public class Parameters extends HashMap<String, String> {
    public final static String CHAMP_DATA = "champData", LOCALE = "locale", API_KEY = "api_key",
            INCLUDE_TIMELINE = "includeTimeline", BEGIN_INDEX = "beginIndex",
            END_INDEX = "endIndex", RANKED_QUEUES = "rankedQueues", CHAMPION_IDS = "championIds",
            VERSION = "version";

    public void put(ChampData champData) {
        if (champData != null)
            this.put(CHAMP_DATA, champData.getChampData());
    }

    public void put(Locale locale) {
        if (locale != null)
            this.put(LOCALE, locale.getLocal());
    }

    public void put(ApiKey apiKey) {
        if (apiKey != null)
            this.put(API_KEY, apiKey.getToken());
    }

    public void put(String key, boolean bool) {
        if (key != null)
            this.put(key, "" + bool);
    }

    public String toParameterString() {
        String str = "";
        for (String key : this.keySet()) {
            if (str.length() != 0)
                str += "&";
            str += key + "=" + this.get(key);
        }
        return str;
    }

    public void put(String title, int value) {
        this.put(title, "" + value);
    }
}
