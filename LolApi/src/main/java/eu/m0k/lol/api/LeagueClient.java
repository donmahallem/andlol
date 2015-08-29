/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.ChampionSpell;
import eu.m0k.lol.api.model.ItemList;
import eu.m0k.lol.api.model.Ladder;
import eu.m0k.lol.api.model.LeagueEntryMap;
import eu.m0k.lol.api.model.MasteryMap;
import eu.m0k.lol.api.model.MatchHistory;
import eu.m0k.lol.api.model.NameList;
import eu.m0k.lol.api.model.NameMap;
import eu.m0k.lol.api.model.Platform;
import eu.m0k.lol.api.model.Queue;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.RuneMap;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.model.Tier;
import eu.m0k.lol.api.model.Version;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class LeagueClient {
    /**
     * 50MB
     */

    private final static long DEFAULT_CACHE_TIME = 5 * 60; //5 min
    private final static long CACHE_SIZE = 50 * 1024 * 1024;
    private final static String TAG = "League-Api", HEADER_USER_AGENT = "User-Agent",
            HEADER_ACCEPT = "Accept", ENCODING_JSON = "application/json",
            HEADER_CACHE_MOD = "x-cache-mod", BASE_AUTHORITY = ".api.pvp.net";
    private final String mUserAgent;
    /**
     * The OkHttp Client to be used
     */
    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private final Gson mGson;
    private final LogLevel mLogLevel;
    private Cache mCache;
    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Response originalResponse = chain.proceed(chain.request());
            final String cacheMod = chain.request().header(Lol.CUSTOM_CACHE);
            Log.d("Interceptor", "Cache-Stats: " + mCache.getRequestCount() + " network: " + mCache.getNetworkCount() + " cache: " + mCache.getHitCount());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + cacheMod)
                    .build();
        }
    };
    private RestAdapter mRestAdapter;

    private LeagueClient(Builder builder) {
        this.mOkHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.registerTypeAdapter(SummonerList.class, new SummonerList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(Version.class, new Version.TypeAdapter());
        gsonBuilder.registerTypeAdapter(NameList.class, new NameList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(ChampionList.class, new ChampionList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(ChampionSpell.SpellRange.class, new ChampionSpell.SpellRange.TypeAdapter());
        gsonBuilder.registerTypeAdapter(NameMap.class, new NameMap.TypeAdapter());
        gsonBuilder.registerTypeAdapter(MasteryMap.class, new MasteryMap.TypeAdapter());
        gsonBuilder.registerTypeAdapter(RuneMap.class, new RuneMap.TypeAdapter());
        gsonBuilder.registerTypeAdapter(ItemList.class, new ItemList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(Tier.class, new Tier.TypeAdapter());
        gsonBuilder.registerTypeAdapter(LeagueEntryMap.class, new LeagueEntryMap.TypeAdapter());
        gsonBuilder.registerTypeAdapter(Ladder.class, new Ladder.TypeAdapter());
        gsonBuilder.registerTypeAdapter(Queue.class, new Queue.TypeAdapter());
        gsonBuilder.registerTypeAdapter(MatchHistory.class, new MatchHistory.TypeAdapter());
        gsonBuilder.registerTypeAdapter(Region.class, new Region.TypeAdapter());
        gsonBuilder.registerTypeAdapter(Platform.class, new Platform.TypeAdapter());
        this.mGson = gsonBuilder.create();
        this.mLogLevel = builder.getLogLevel();
        this.mUserAgent = builder.getUserAgent();

        if (builder.mCacheDir != null) {
            mCache = new Cache(new File(builder.mCacheDir, "rcache"), CACHE_SIZE);
        }
        this.mOkHttpClient.setCache(this.mCache);
    }

    public static void log(final LogLevel system, final LogLevel level, final String tag, final String msg) {
        if (system.LEVEL >= level.LEVEL) {
            Log.d(tag, msg);
        }
    }

    public Cache getCache() {
        return this.mCache;
    }

    private RestAdapter.Builder createDefaultRestAdapterBuilder() {
        final RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setConverter(new GsonConverter(this.mGson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                    }
                }).setClient(new OkClient(this.mOkHttpClient))
                .setEndpoint("https://lolapi.xants.de");
        return restAdapterBuilder;
    }

    public Lol.Summoner getSummonerEndpoint() {
        return getRestAdapter().create(Lol.Summoner.class);
    }

    private RestAdapter getRestAdapter() {
        if (this.mRestAdapter == null) {
            this.mRestAdapter = this.createDefaultRestAdapterBuilder().build();
        }
        return this.mRestAdapter;
    }

    public Lol.Static getStaticEndpoint() {
        return this.getRestAdapter().create(Lol.Static.class);
    }

    public Lol.League getLeagueEndpoint() {
        return getRestAdapter().create(Lol.League.class);
    }

    public Lol.MatchHistory getMatchHistoryEndpoint() {
        return getRestAdapter().create(Lol.MatchHistory.class);
    }

    public Lol.Match getMatchEndpoint() {
        return getRestAdapter().create(Lol.Match.class);
    }

    public Lol.CurrentGame getCurrentGameEndpoint() {
        return getRestAdapter().create(Lol.CurrentGame.class);
    }

    public <T> eu.m0k.lol.api.Response<T> query(HttpUrl.Builder builder, Class<ChampionList> clazz) {
        return null;
    }
    private final String BASE_URL="";

    HttpUrl.Builder createDefaultUrlBuilder(@NonNull Region region){
        HttpUrl.Builder builder=HttpUrl.parse(BASE_URL).newBuilder();
        builder.addPathSegment("test");
        return builder;
    }

    /**
     * Builder for the League Api
     */
    public static class Builder {
        private LogLevel mLogLevel = LogLevel.NONE;
        private String mUserAgent = "LeagueApi (github.com/donmahallem/andlol)";
        private File mCacheDir;
        private OkHttpClient mOkHttpClient;

        public String getUserAgent() {
            return mUserAgent;
        }

        public Builder setUserAgent(String userAgent) {
            this.mUserAgent = userAgent;
            return this;
        }


        public Builder cacheDir(File file) {
            this.mCacheDir = file;
            return this;
        }

        public LogLevel getLogLevel() {
            return this.mLogLevel;
        }

        public Builder setLogLevel(LogLevel logLevel) {
            this.mLogLevel = logLevel;
            return this;
        }

        public LeagueClient build() {
            return new LeagueClient(this);
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            this.mOkHttpClient = okHttpClient;
            return this;
        }
    }

    private class LolRequestInterceptor implements RequestInterceptor {

        @Override
        public void intercept(RequestFacade request) {
            //request.addPathParam("region",LeagueClient.this.mRegion.getRegion());
        }
    }

}
