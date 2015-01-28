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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import eu.m0k.lol.api.internal.MainThreadExecutor;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.ChampionSpell;
import eu.m0k.lol.api.model.MasteryMap;
import eu.m0k.lol.api.model.NameList;
import eu.m0k.lol.api.model.NameMap;
import eu.m0k.lol.api.model.RuneMap;
import eu.m0k.lol.api.model.SummonerList;
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
    private static final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
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
    private MessageDigest mMessageDigest;
    private Cache mCache;
    private CacheStatistics mCacheStatistics = new CacheStatistics();
    private RestAdapter mRestAdapter;


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
        this.mGson = gsonBuilder.create();
        this.mApiKey = builder.getApiKey();
        this.mLogLevel = builder.getLogLevel();
        this.mUserAgent = builder.getUserAgent();
        RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder();
        restAdapterBuilder.setEndpoint("https://global.api.pvp.net");
        restAdapterBuilder.setLogLevel(RestAdapter.LogLevel.BASIC);
        restAdapterBuilder.setConverter(new GsonConverter(this.mGson));
        restAdapterBuilder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("api_key", mApiKey.getToken());
            }
        });

        restAdapterBuilder.setClient(new OkClient(this.mOkHttpClient));
        this.mRestAdapter = restAdapterBuilder.build();

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

    public Lol.Static getEndpointStatic() {
        return this.mRestAdapter.create(Lol.Static.class);
    }

    public CacheStatistics getCacheStatistics() {
        return mCacheStatistics;
    }

    public void log(final LogLevel logLevel, final String msg) {
        log(this.mLogLevel, logLevel, TAG, msg);
    }

    private String hashUrl(String toHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return new String(UUID.nameUUIDFromBytes(toHash.getBytes("UTF-8")).toString());
    }


    /**
     * Class containing Cache Statistics
     */
    public static final class CacheStatistics {

        private long mCacheHits = 0;
        private long mCacheMiss = 0;
        private long mCacheRequests = 0;

        public void incrementCacheHit() {
            this.mCacheHits++;
        }

        public void incrementCacheMiss() {
            this.mCacheMiss++;
        }

        public void incrementCacheRequest() {
            this.mCacheRequests++;
        }

        @Override
        public String toString() {
            return "CacheStatistics{" +
                    "cacheHits=" + mCacheHits +
                    ", cacheMiss=" + mCacheMiss +
                    ", cacheRequests=" + mCacheRequests +
                    '}';
        }

        public long getCacheHits() {
            return mCacheHits;
        }

        public long getCacheMiss() {
            return mCacheMiss;
        }

        public long getCacheRequests() {
            return mCacheRequests;
        }
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

}
