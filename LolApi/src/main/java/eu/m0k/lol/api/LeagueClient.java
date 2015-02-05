/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.ChampionSpell;
import eu.m0k.lol.api.model.ItemList;
import eu.m0k.lol.api.model.Ladder;
import eu.m0k.lol.api.model.LeagueEntryMap;
import eu.m0k.lol.api.model.MasteryMap;
import eu.m0k.lol.api.model.MatchHistory;
import eu.m0k.lol.api.model.NameList;
import eu.m0k.lol.api.model.NameMap;
import eu.m0k.lol.api.model.Queue;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.RuneMap;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.model.Tier;
import eu.m0k.lol.api.model.Version;
import eu.m0k.lol.api.network.ApiKey;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class LeagueClient {
    /**
     * 50MB
     */

    private final static long DEFAULT_CACHE_TIME = 5 * 60; //5 min
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Response originalResponse = chain.proceed(chain.request());
            final String cacheMod = chain.request().header("Cache-Control");
            Log.d("Interceptor", "Cache-Control: " + cacheMod);
            Log.d("Interceptor", "Cached: " + (originalResponse.cacheResponse() != null));
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheMod)

                    .build();
        }
    };
    private final static long CACHE_SIZE = 50 * 1024 * 1024;
    private final static String TAG = "League-Api", HEADER_USER_AGENT = "User-Agent",
            HEADER_ACCEPT = "Accept", ENCODING_JSON = "application/json",
            HEADER_CACHE_MOD = "x-cache-mod", BASE_AUTHORITY = ".api.pvp.net";
    private final String mUserAgent;
    /**
     * The Api token to be used
     */
    private final ApiKey mApiKey;
    /**
     * The OkHttp Client to be used
     */
    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private final Gson mGson;
    private final LogLevel mLogLevel;
    private Cache mCache;
    private HashMap<Boolean, HashMap<Region, RestAdapter>> mRestAdapters = new HashMap<Boolean, HashMap<Region, RestAdapter>>();


    private LeagueClient(Builder builder) {
        this.mOkHttpClient.interceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
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
        this.mGson = gsonBuilder.create();
        this.mApiKey = builder.getApiKey();
        this.mLogLevel = builder.getLogLevel();
        this.mUserAgent = builder.getUserAgent();

        if (builder.getCacheDir() != null) {
            try {
                mCache = new Cache(new File(builder.mCacheDir, "rcache"), CACHE_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private RestAdapter.Builder createDefaultRestAdapterBuilder(final Region region) {
        final RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setConverter(new GsonConverter(this.mGson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addQueryParam("api_key", mApiKey.getToken());
                        request.addPathParam("region", region.getRegion());
                    }
                }).setClient(new OkClient(this.mOkHttpClient));
        return restAdapterBuilder;
    }

    public Lol.Summoner getSummonerEndpoint(Region region) {
        return getRestAdapterForRegion(region).create(Lol.Summoner.class);
    }

    private RestAdapter getRestAdapterForRegion(Region region) {
        if (!mRestAdapters.containsKey(false)) {
            mRestAdapters.put(false, new HashMap<Region, RestAdapter>());
        }
        if (!mRestAdapters.get(false).containsKey(region)) {
            mRestAdapters.get(false).put(region, createDefaultRestAdapterBuilder(region)
                    .setEndpoint("https://" + region.getRegion() + ".api.pvp.net").build());
        }
        return mRestAdapters.get(false).get(region);
    }

    public Lol.Static getStaticEndpoint(Region region) {
        if (!mRestAdapters.containsKey(true)) {
            mRestAdapters.put(true, new HashMap<Region, RestAdapter>());
        }
        if (!mRestAdapters.get(true).containsKey(region)) {
            mRestAdapters.get(true).put(region, createDefaultRestAdapterBuilder(region)
                    .setEndpoint("https://global.api.pvp.net").build());

        }
        return mRestAdapters.get(true).get(region).create(Lol.Static.class);
    }

    public Lol.League getLeagueEndpoint(Region region) {
        return getRestAdapterForRegion(region).create(Lol.League.class);
    }

    public Lol.MatchHistory getMatchHistoryEndpoint(Region region) {
        return getRestAdapterForRegion(region).create(Lol.MatchHistory.class);
    }

    /**
     * Builder for the League Api
     */
    public static class Builder {
        private ApiKey mApiKey;
        private LogLevel mLogLevel = LogLevel.NONE;
        private String mUserAgent = "LeagueApi (github.com/donmahallem/andlol)";
        private File mCacheDir;

        public ApiKey getApiKey() {
            return mApiKey;
        }

        public Builder setApiKey(String apiKey) {
            return this.setApiKey(new ApiKey(apiKey));
        }

        public Builder setApiKey(ApiKey apiKey) {
            this.mApiKey = apiKey;
            return this;
        }

        public String getUserAgent() {
            return mUserAgent;
        }

        public Builder setUserAgent(String userAgent) {
            this.mUserAgent = userAgent;
            return this;
        }

        public File getCacheDir() {
            return mCacheDir;
        }

        public Builder setCacheDir(File file) {
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
            if (this.mApiKey == null) {
                throw new NullPointerException("You have to set an api token");
            }
            return new LeagueClient(this);
        }
    }

    private class LolRequestInterceptor implements RequestInterceptor {

        @Override
        public void intercept(RequestFacade request) {
            request.addQueryParam("api_key", LeagueClient.this.mApiKey.getToken());
            //request.addPathParam("region",LeagueClient.this.mRegion.getRegion());
        }
    }

}
