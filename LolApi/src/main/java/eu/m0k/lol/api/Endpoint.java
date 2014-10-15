/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

public class Endpoint {
    public final static String CHAMPION = "https://global.api.pvp.net/api/lol/static-data/{region}/v1.2/champion/{champId}";
    public final static String CHAMPION_LIST = "https://global.api.pvp.net/api/lol/static-data/{region}/v1.2/champion";
    public final static String MATCH = "https://global.api.pvp.net/api/lol/{region}/v2.2/match/{matchId}";
    public final static String MATCH_HISTORY = "https://{region}.api.pvp.net/api/lol/{region}/v2.2/matchhistory/{summonerId}";
}
