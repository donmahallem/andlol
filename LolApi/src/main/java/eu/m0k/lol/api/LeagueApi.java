/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.DiskLruCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import eu.m0k.lol.api.internal.LeagueError;
import eu.m0k.lol.api.internal.MainThreadExecutor;
import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.ChampionSpell;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.MasteryList;
import eu.m0k.lol.api.model.NameList;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.network.ApiKey;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.m0k.lol.api.network.Parameters;
import eu.m0k.lol.api.network.PathSegments;
import eu.m0k.lol.api.response.RunePageResponse;

public class LeagueApi {
    private final static String HEADER_USER_AGENT = "User-Agent", HEADER_ACCEPT = "Accept", ENCODING_JSON = "application/json";
    private final static int CACHE_INDEX_EXPIRES = 1, CACHE_INDEX_BODY = 0;
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
    private DiskLruCache mDiskLruCache;

    private LeagueApi(Builder builder) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.registerTypeAdapter(SummonerList.class, new SummonerList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(NameList.class, new NameList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(MasteryList.class, new MasteryList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(RunePageResponse.class, new RunePageResponse.TypeAdapter());
        gsonBuilder.registerTypeAdapter(ChampionList.class, new ChampionList.TypeAdapter());
        gsonBuilder.registerTypeAdapter(ChampionSpell.SpellRange.class, new ChampionSpell.SpellRange.TypeAdapter());
        this.mGson = gsonBuilder.create();
        this.mApiKey = builder.getApiKey();
        this.mLogLevel = builder.getLogLevel();
        this.mUserAgent = builder.getUserAgent();
        if (builder.getCacheDir() == null)
            this.mDiskLruCache = null;
        else {
            try {
                this.mDiskLruCache = DiskLruCache.open(new File(builder.mCacheDir, "rcache"), 0, 2, 100000);
            } catch (IOException e) {
                this.mDiskLruCache = null;
            }
        }
    }

    private String getCache(String key) throws IOException {
        synchronized (this) {
            DiskLruCache.Snapshot snapShot = this.mDiskLruCache.get(key);
            if (Long.parseLong(snapShot.getString(CACHE_INDEX_EXPIRES)) < System.currentTimeMillis()) {
                snapShot.close();
                this.mDiskLruCache.remove(key);
                return null;
            }
            return snapShot.getString(CACHE_INDEX_BODY);
        }
    }

    private void putCache(String key, String body, long expires) throws IOException {
        synchronized (this) {
            DiskLruCache.Editor editor = this.mDiskLruCache.edit(key);
            editor.set(CACHE_INDEX_BODY, body);
            editor.set(CACHE_INDEX_EXPIRES, "" + expires);
            editor.commit();
        }
    }

    private <T> LeagueResponse<T> queryNetwork(String url, Region region, PathSegments segments, Parameters parameters, Class<T> clazz) {
        /**
         * Check if PathSegements is null otherwise sets one
         */
        segments.put(region);
        // Adding the Api Token
        parameters.put(this.mApiKey);
        /**
         * The url to be called
         */
        String _url = url + "?" + parameters.toParameterString();
        for (String segment : segments.keySet()) {
            _url = _url.replace("{" + segment + "}", segments.get(segment));
        }
        if (LogLevel.BASIC == this.mLogLevel) {
            Log.d("LeagueApi", "HTTP --> " + _url);
        }
        /**
         * The Builder for the request to be send
         */
        final Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(_url);
        requestBuilder.addHeader(HEADER_USER_AGENT, mUserAgent);
        requestBuilder.addHeader(HEADER_ACCEPT, ENCODING_JSON);
        /**
         * The Request to be send
         */
        final Request request = requestBuilder.build();

        Response response = null;
        try {
            response = this.mOkHttpClient.newCall(request).execute();
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi", "HTTP <-- " + response.code() + " " + _url);
            }
        } catch (IOException e) {
            throw LeagueError.networkError(_url, e);
        }
        if (response.isSuccessful()) {
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi", "GSON --> Start Conversion");
            }
            T obj = this.mGson.fromJson(new BufferedReader(new InputStreamReader(response.body().byteStream())), clazz);
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi", "GSON <-- End Conversion");
            }
            if (obj == null) {
                throw LeagueError.conversionError("Could not convert", _url, clazz);
            }
            return new LeagueResponse<T>(_url, response.code(), "", response.headers(), obj);
        } else {
            throw LeagueError.httpError(response.code(), _url);
        }
    }

    @SuppressWarnings("unused")
    public LeagueResponse<Champion> getChampion(int champion, Region region) throws IOException {
        return this.getChampion(champion, region, null, null);
    }

    @SuppressWarnings("unused")
    public LeagueResponse<Champion> getChampion(int champion, Region region, Locale locale) throws IOException {
        return this.getChampion(champion, region, null, locale);
    }

    @SuppressWarnings("unused")
    public LeagueResponse<Champion> getChampion(int champion, Region region, ChampData champData) throws IOException {
        return this.getChampion(champion, region, champData, null);
    }

    @SuppressWarnings("unused")
    public LeagueResponse<Champion> getChampion(int champion, Region region, ChampData champData, Locale locale) throws IOException {
        return this.getChampion(champion, region, champData, locale, true);
    }

    @SuppressWarnings("unused")
    public LeagueResponse<Champion> getChampion(int champion, Region region, ChampData champData, Locale locale, boolean cache) throws IOException {
        if (champion < 0)
            throw new IllegalArgumentException("Champion ID must be greater then 0");
        if (region == null)
            throw new IllegalArgumentException("Region must not be null");
        Parameters parameters = new Parameters();
        parameters.put(champData);
        parameters.put(locale);
        PathSegments mPathSegments = new PathSegments();
        mPathSegments.putChampId(champion);
        return queryNetwork(Endpoint.CHAMPION, region, mPathSegments, parameters, Champion.class);
    }

    @SuppressWarnings("unused")
    public LeagueResponse<Champion> getMatch(long matchId, boolean includeTimeline, Region region, boolean cache) throws IOException {
        if (matchId < 0)
            throw new IllegalArgumentException("Champion ID must be greater then 0");
        if (region == null)
            throw new IllegalArgumentException("Region must not be null");
        Parameters parameters = new Parameters();
        parameters.put(Parameters.INCLUDE_TIMELINE, includeTimeline);
        PathSegments mPathSegments = new PathSegments();
        mPathSegments.putMatchId(matchId);
        return queryNetwork(Endpoint.CHAMPION + matchId, region, mPathSegments, parameters, Champion.class);
    }
    /**
     * Retrieves the Championlist
     *
     * @param region    region to query
     * @param champData ChampData to query
     * @param locale    Gets the ChampData in this Language
     * @param cache     Gets this Data from Cache or Network
     * @return the LeagueResponse or null if it fails
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public LeagueResponse<ChampionList> getChampionList(Region region, ChampData champData, Locale locale, boolean cache) throws IOException {
        if (region == null)
            throw new IllegalArgumentException("Region must not be null");
        Parameters parameters = new Parameters();
        parameters.put(champData);
        parameters.put(locale);
        return queryNetwork(Endpoint.CHAMPION, region, new PathSegments(), parameters, ChampionList.class);
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

        public LeagueApi build() {
            if (this.mApiKey == null) {
                throw new NullPointerException("You have to set an api token");
            }
            return new LeagueApi(this);
        }
    }

}
