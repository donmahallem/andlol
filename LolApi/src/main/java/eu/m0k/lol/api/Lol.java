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
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
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
        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/champion")
        public eu.m0k.lol.api.model.ChampionList getChampions(@Path("region") final Region region, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/champion")
        public void getChampions(@Path("region") final Region region, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.ChampionList> cb);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/champion?dataById=false")
        public void getChampions(@Path("region") final Region region, @Query("locale") final Locale locale, @Query("version") final String version, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.ChampionList> cb);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/champion/{id}")
        public eu.m0k.lol.api.model.Champion getChampion(@Path("region") final Region region, @Path("id") final int id, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/champion/{id}")
        public void getChampion(@Path("region") final Region region, @Path("id") final int id, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.Champion> cb);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/item")
        public Item getItems(@Path("region") final Region region, @Query("locale") final Locale locale, @Query("locale") final String version);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/item")
        public void getItems(@Path("region") final Region region, @Query("locale") final Locale locale, @Query("locale") final String version, final Callback<Item> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/item/{id}")
        public Item getItem(@Path("region") final Region region, @Path("id") final int itemId, @Query("locale") final Locale locale, @Query("locale") final String version);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/item/{id}")
        public void getItem(@Path("region") final Region region, @Path("id") final int itemId, @Query("locale") final Locale locale, @Query("locale") final String version, final Callback<Item> callback);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/versions")
        public List<VersionList> getVersions(@Path("region") final Region region);

        @Headers(CACHE_CONTROL + ": public, max-age=" + HOURS_12)
        @GET("/api/lol/static-data/{region}/v1.2/versions")
        public void getVersions(@Path("region") final Region region, final Callback<VersionList> callback);

    }

    public static class Summoner {

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
