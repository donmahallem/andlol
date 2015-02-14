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
import eu.m0k.lol.api.model.CurrentGameInfo;
import eu.m0k.lol.api.model.Item;
import eu.m0k.lol.api.model.ItemList;
import eu.m0k.lol.api.model.LeagueEntryMap;
import eu.m0k.lol.api.model.LeagueSummonerMap;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Masteries;
import eu.m0k.lol.api.model.MasteryMap;
import eu.m0k.lol.api.model.MasteryPagesResponse;
import eu.m0k.lol.api.model.MatchDetail;
import eu.m0k.lol.api.model.NameMap;
import eu.m0k.lol.api.model.Platform;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.RuneMap;
import eu.m0k.lol.api.model.SummonerIds;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.model.SummonerNameMap;
import eu.m0k.lol.api.model.SummonerNames;
import eu.m0k.lol.api.model.SummonerSpell;
import eu.m0k.lol.api.model.VersionList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

public class Lol {
    public static final String CUSTOM_CACHE = "Lol-Custom-Cache";
    private static final long
            MINUTES_10 = 10 * 60,
            MINUTES_30 = 30 * 60,
            HOURS_1 = 60 * 60,
            HOURS_6 = HOURS_1 * 6,
            HOURS_12 = HOURS_1 * 12,
            DAY_1 = HOURS_12 * 2,
            WEEK_1 = DAY_1 * 7,
            YEAR_1 = DAY_1 * 365;

    public static interface Static {
        final static String VERSION = "v1.2";

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion")
        public eu.m0k.lol.api.model.ChampionList getChampions(@Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion")
        public void getChampions(@Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.ChampionList> cb);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion?dataById=false")
        public void getChampions(@Query("locale") final Locale locale, @Query("version") final String version, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.ChampionList> cb);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion/{id}")
        public eu.m0k.lol.api.model.Champion getChampion(@Path("id") final int id, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion/{id}")
        public void getChampion(@Path("id") final int id, @Query("locale") final Locale locale, @Query("version") final String version, @Query("dataById") final boolean dataById, @Query("champData") ChampData champData, Callback<eu.m0k.lol.api.model.Champion> cb);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion/{id}?champData=all")
        public eu.m0k.lol.api.model.Champion getChampion(@Path("id") final int id, @Query("locale") final Locale locale);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/champion/{id}?champData=all")
        public void getChampion(@Path("id") final int id, @Query("locale") final Locale locale, final Callback<eu.m0k.lol.api.model.Champion> cb);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item")
        public ItemList getItems(@Query("locale") final Locale locale, @Query("version") final String version);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item")
        public void getItems(@Query("locale") final Locale locale, @Query("version") final String version, final Callback<ItemList> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item/{id}")
        public Item getItem(@Path("id") final int itemId, @Query("locale") final Locale locale, @Query("version") final String version);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item/{id}")
        public void getItem(@Path("id") final int itemId, @Query("locale") final Locale locale, @Query("version") final String version, final Callback<Item> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item/{id}")
        public Item getItem(@Path("id") final int itemId, @Query("locale") final Locale locale);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/item/{id}")
        public void getItem(@Path("id") final int itemId, @Query("locale") final Locale locale, final Callback<Item> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/versions")
        public List<VersionList> getVersions();

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/versions")
        public void getVersions(final Callback<VersionList> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/summoner-spell/{spellId}")
        public SummonerSpell getSummonerSpell(@Path("spellId") final int spellId);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/summoner-spell/{spellId}")
        public void getSummonerSpell(@Path("spellId") final int spellId, final Callback<SummonerSpell> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/mastery?masteryListData=all")
        public Masteries getMasteries(@Query("locale") final Locale locale);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/mastery?masteryListData=all")
        public void getMasteries(@Query("locale") final Locale locale, final Callback<Masteries> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/mastery/{masteryId}?masteryListData=all")
        public Masteries getMasteries(@Path("masteryId") final String masteryId, @Query("locale") final Locale locale);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/static-data/{region}/" + VERSION + "/mastery/{masteryId}?masteryListData=all")
        public void getMasteries(@Path("masteryId") final String masteryId, @Query("locale") final Locale locale, final Callback<Masteries> callback);

    }

    public static interface CurrentGame {

        @Headers({
                "Cache-Control: max-stale=" + MINUTES_30,
                CUSTOM_CACHE + ": " + MINUTES_10
        })
        @GET("/observer-mode/rest/consumer/getSpectatorGameInfo/{platformId}/{summonerId}")
        public CurrentGameInfo getMatch(@Path("platformId") final Platform platform, @Path("summonerId") final long summonerId);

        @Headers({
                "Cache-Control: max-stale=" + MINUTES_30,
                CUSTOM_CACHE + ": " + MINUTES_10
        })
        @GET("/observer-mode/rest/consumer/getSpectatorGameInfo/{platformId}/{summonerId}")
        public void getMatch(@Path("platformId") final Platform platform, @Path("summonerId") final long summonerId, final Callback<CurrentGameInfo> callback);
    }

    public static interface Match {
        final static String VERSION = "v2.2";

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + YEAR_1
        })
        @GET("/api/lol/{region}/v2.2/match/{matchId}")
        public MatchDetail getMatch(@Path("matchId") final long matchId, @Query("includeTimeline") final boolean includeTimeline);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + YEAR_1
        })
        @GET("/api/lol/{region}/v2.2/match/{matchId}")
        public void getMatch(@Path("matchId") final long matchId, @Query("includeTimeline") final boolean includeTimeline, final Callback<MatchDetail> callback);

    }

    public static interface Summoner {
        final static String VERSION = "v1.4";

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/by-name/{summonerNames}")
        public SummonerNameMap getSummonersByName(@Path("summonerNames") final SummonerNames summonerNames);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/by-name/{summonerNames}")
        public void getSummonersByName(@Path("summonerNames") final SummonerNames summonerNames, final Callback<SummonerNameMap> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}")
        public SummonerList getSummoner(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}")
        public void getSummoners(@Path("summonerIds") final SummonerIds summonerIds, final Callback<SummonerList> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/masteries")
        public MasteryMap getMasteries(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/masteries")
        public void getMasteries(@Path("summonerIds") final SummonerIds summonerIds, final Callback<MasteryMap> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerId}/masteries")
        public MasteryPagesResponse getMasteryPages(@Path("summonerId") final long summonerId);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerId}/masteries")
        public void getMasteryPages(@Path("summonerId") final long summonerId, final Callback<MasteryPagesResponse> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/runes")
        public RuneMap getRunes(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/runes")
        public void getRunes(@Path("summonerIds") final SummonerIds summonerIds, final Callback<RuneMap> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/name")
        public NameMap getName(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/summoner/{summonerIds}/name")
        public void getName(@Path("summonerIds") final SummonerIds summonerIds, final Callback<NameMap> callback);
    }

    public static interface League {
        final static String VERSION = "v2.5";

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}")
        public LeagueSummonerMap getLeague(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}")
        public void getLeague(@Path("summonerIds") final SummonerIds summonerIds, final Callback<LeagueSummonerMap> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}/entry")
        public void getLeagueForSummoner(@Path("summonerIds") final long... summonerIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}/entry")
        public LeagueEntryMap getLeagueEntryForSummoner(@Path("summonerIds") final SummonerIds summonerIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}/entry")
        public void getLeagueEntryForSummoner(@Path("summonerIds") final SummonerIds summonerIds, final Callback<LeagueEntryMap> callback);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}/entry")
        public void getLeagueForTeam(@Path("summonerIds") final long... teamIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}/entry")
        public void getLeagueEntriesForTeam(@Path("summonerIds") final long... teamIds);

        @Headers({
                "Cache-Control: max-stale=" + WEEK_1,
                CUSTOM_CACHE + ": " + DAY_1
        })
        @GET("/api/lol/{region}/" + VERSION + "/league/by-summoner/{summonerIds}/entry")
        public void getChallenger();
    }

    public static interface MatchHistory {
        final static String VERSION = "v2.2";

        @Headers({
                "Cache-Control: max-stale=" + DAY_1,
                CUSTOM_CACHE + ": " + MINUTES_30
        })
        @GET("/api/lol/{region}/" + VERSION + "/matchhistory/{summonerId}")
        public eu.m0k.lol.api.model.MatchHistory getMatchHistory(@Path("summonerId") final long summonerId, @Query("startIndex") int beginIndex, @Query("endIndex") int endIndex);

        @Headers({
                "Cache-Control: max-stale=" + DAY_1,
                CUSTOM_CACHE + ": " + MINUTES_30
        })
        @GET("/api/lol/{region}/" + VERSION + "/matchhistory/{summonerId}")
        public void getMatchHistory(@Path("summonerId") final long summonerId, @Query("startIndex") int beginIndex, @Query("endIndex") int endIndex, final Callback<eu.m0k.lol.api.model.MatchHistory> callback);
    }

    public static class Game {
        public static void getRecentGames(final LeagueClient leagueClient, final Region region, final long summonerId) {

        }
    }

    public static class Champion {
        public static void getChampion(final LeagueClient leagueClient, final Region region, final boolean freeToPlay) {

        }

        public static void getChampion(final LeagueClient leagueClient, final Region region, final long championId) {

        }
    }
}
