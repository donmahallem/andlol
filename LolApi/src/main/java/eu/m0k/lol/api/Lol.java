/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import java.util.List;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Item;
import eu.m0k.lol.api.model.ItemList;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.MasteryMap;
import eu.m0k.lol.api.model.NameMap;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.RuneMap;
import eu.m0k.lol.api.model.SummonerIds;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.model.SummonerNames;
import eu.m0k.lol.api.model.VersionList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

public class Lol {
    public static final String HEADER_CACHE_MOD = "x-cache-mod", CACHE_CONTROL = "Cache-Control";
    private static final long HOURS_1 = 60 * 60, HOURS_6 = HOURS_1 * 6, HOURS_12 = HOURS_1 * 12;

    public static interface Static {
        final static String VERSION = "v1.2";

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion")
        public eu.m0k.lol.api.model.ChampionList getChampions(@Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion")
        public void getChampions(@Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.ChampionList> cb);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion?dataById=false")
        public void getChampions(@Query("locale") final Locale locale, @Query("version") final String version, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.ChampionList> cb);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion/{id}")
        public eu.m0k.lol.api.model.Champion getChampion(@Path("id") final int id, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion/{id}")
        public void getChampion(@Path("id") final int id, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.Champion> cb);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item")
        public ItemList getItems(@Query("locale") final Locale locale, @Query("version") final String version);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item")
        public void getItems(@Query("locale") final Locale locale, @Query("version") final String version, final Callback<ItemList> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item/{id}")
        public Item getItem(@Path("id") final int itemId, @Query("locale") final Locale locale, @Query("version") final String version);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item/{id}")
        public void getItem(@Path("id") final int itemId, @Query("locale") final Locale locale, @Query("version") final String version, final Callback<Item> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/versions")
        public List<VersionList> getVersions();

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/" + VERSION + "/versions")
        public void getVersions(final Callback<VersionList> callback);

    }

    public static interface Summoner {
        final static String VERSION = "v1.4";

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/by-name/{summonerNames}")
        public SummonerList getSummonersByName(@Path("summonerNames") final SummonerNames summonerNames);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/by-name/{summonerNames}")
        public void getSummonersByName(@Path("summonerNames") final SummonerNames summonerNames, final Callback<SummonerList> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}")
        public SummonerList getSummoner(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}")
        public void getSummoners(@Path("summonerIds") final SummonerIds summonerIds, final Callback<SummonerList> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/masteries")
        public MasteryMap getMasteries(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/masteries")
        public void getMasteries(@Path("summonerIds") final SummonerIds summonerIds, final Callback<MasteryMap> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/runes")
        public RuneMap getRunes(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/runes")
        public void getRunes(@Path("summonerIds") final SummonerIds summonerIds, final Callback<RuneMap> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/name")
        public NameMap getName(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_1)
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/name")
        public void getName(@Path("summonerIds") final SummonerIds summonerIds, final Callback<NameMap> callback);
    }

    public static class Game {
        public static void getRecentGames(final LeagueClient leagueClient, final Region region, final long summonerId) {

        }
    }

    public static class League {

        public static void getLeagueForSummoner(final LeagueClient leagueClient, final Region region, final long... summonerIds) {

        }

        public static void getLeagueEntryForSummoner(final LeagueClient leagueClient, final Region region, final long summonerIds) {

        }

        public static void getLeagueForTeam(final LeagueClient leagueClient, final Region region, final long... teamIds) {

        }

        public static void getLeagueEntriesForTeam(final LeagueClient leagueClient, final Region region, final long... teamIds) {

        }

        public static void getChallenger(final LeagueClient leagueClient, final Region region) {

        }
    }

    public static class Champion {
        public static void getChampion(final LeagueClient leagueClient, final Region region, final boolean freeToPlay) {

        }

        public static void getChampion(final LeagueClient leagueClient, final Region region, final long championId) {

        }
    }
}