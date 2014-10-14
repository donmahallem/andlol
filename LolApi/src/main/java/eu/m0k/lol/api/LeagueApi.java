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
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.DiskLruCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
import eu.m0k.lol.api.network.CachePolicy;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.m0k.lol.api.network.Parameters;
import eu.m0k.lol.api.network.PathSegments;
import eu.m0k.lol.api.response.RunePageResponse;

public class LeagueApi {
    private final static String TAG = "League-Api", HEADER_USER_AGENT = "User-Agent", HEADER_ACCEPT = "Accept", ENCODING_JSON = "application/json";
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
    private MessageDigest mMessageDigest;
    private CacheStatistics mCacheStatistics = new CacheStatistics();

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

    public static void log(final LogLevel system, final LogLevel level, final String tag, final String msg) {
        if (system.LEVEL >= level.LEVEL) {
            Log.d(tag, msg);
        }
    }

    public CacheStatistics getCacheStatistics() {
        return mCacheStatistics;
    }

    public void log(final LogLevel logLevel, final String msg) {
        log(this.mLogLevel, logLevel, TAG, msg);
    }

    private <T> LeagueResponse<T> getCache(String url, Class<T> clazz) throws IOException, NoSuchAlgorithmException {
        synchronized (this) {
            final String keyHash = hashUrl(url);
            this.mCacheStatistics.incrementCacheRequest();
            log(LogLevel.FULL, "CACHE --> " + url);
            DiskLruCache.Snapshot snapShot = this.mDiskLruCache.get(keyHash);
            if (snapShot == null) {
                this.mCacheStatistics.incrementCacheMiss();
                return null;
            }
            if (Long.parseLong(snapShot.getString(CACHE_INDEX_EXPIRES)) < System.currentTimeMillis()) {
                snapShot.close();
                this.mDiskLruCache.remove(keyHash);
                this.mCacheStatistics.incrementCacheMiss();
                log(LogLevel.FULL, "CACHE MISS <-- " + url);
                return null;
            }
            this.mCacheStatistics.incrementCacheHit();
            log(LogLevel.FULL, "CACHE HIT <-- " + url);
            log(LogLevel.FULL, "GSON --> Start Conversion");
            T obj = this.mGson.fromJson(new BufferedReader(new InputStreamReader(snapShot.getInputStream(CACHE_INDEX_BODY))), clazz);
            log(LogLevel.FULL, "GSON <-- End Conversion");
            snapShot.close();
            LeagueResponse<T> response = new LeagueResponse<T>(url, 200, "", Headers.of("Accept", "application/json"), obj, true);
            return response;
        }
    }

    private String hashUrl(String toHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return new String(UUID.nameUUIDFromBytes(toHash.getBytes("UTF-8")).toString());
    }

    private boolean putCache(String url, String body, long expires) throws IOException, NoSuchAlgorithmException {
        synchronized (this) {
            final String keyHash = hashUrl(url);
            log(LogLevel.FULL, "putCache " + url + "ID: \"" + keyHash + "\" for " + expires);
            DiskLruCache.Editor editor = this.mDiskLruCache.edit(keyHash);
            editor.set(CACHE_INDEX_BODY, body);
            editor.set(CACHE_INDEX_EXPIRES, "" + (System.currentTimeMillis() + expires));
            editor.commit();
            return true;
        }
    }

    private <T> LeagueResponse<T> query(final String url, final Region region,
                                        final PathSegments segments, final Parameters parameters,
                                        final CachePolicy cachePolicy, long cacheTime, TimeUnit unit,
                                        final Class<T> clazz) throws IOException {
        return query(url, region, segments, parameters, cachePolicy, unit.toMillis(cacheTime), clazz);
    }

    private <T> LeagueResponse<T> query(final String url, final Region region,
                                        final PathSegments segments, final Parameters parameters,
                                        final CachePolicy cachePolicy, long cacheTime,
                                        final Class<T> clazz) throws IOException {
        final long callTime = System.currentTimeMillis();
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
        log(LogLevel.BASIC, "Query: url( " + _url + " )");
        LeagueResponse<T> response = null;
        try {
            if (cachePolicy == CachePolicy.NORMAL) {
                response = getCache(_url, clazz);
                if (response == null) {
                    String body = queryNetwork(_url, region);
                    T object = convertToResponse(body, clazz);
                    if (object == null)
                        throw LeagueError.conversionError("Could not convert stream", _url, clazz);
                    putCache(_url, body, cacheTime);
                    response = new LeagueResponse<T>(_url, 200, "Success", Headers.of("Accept", "application/json"), object, false);
                }
            }
        } catch (IOException exception) {
            throw LeagueError.networkError(_url, exception);
        } catch (NoSuchAlgorithmException e) {
            throw LeagueError.unexpectedError("No MD5", e);
        }
        if (response == null)
            throw LeagueError.unexpectedError(_url, new RuntimeException("Response is null"));
        log(LogLevel.BASIC, "Query: url( " + _url + " ), cached: " + response.isCached() + ", time: " + (System.currentTimeMillis() - callTime) + "ms");
        return response;
    }

    private <T> T convertToResponse(InputStream stream, Class<T> clazz) {
        return this.mGson.fromJson(new BufferedReader(new InputStreamReader(stream)), clazz);
    }

    private <T> T convertToResponse(String body, Class<T> clazz) {
        return this.mGson.fromJson(body, clazz);
    }

    private String createKeyHash(String url) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(url.getBytes("UTF-8"));
        return new String(md.digest());
    }

    private String queryNetwork(final String url, final Region region) throws IOException {

        log(LogLevel.FULL, "HTTP --> " + url);
        /**
         * The Builder for the request to be send
         */
        final Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.addHeader(HEADER_USER_AGENT, mUserAgent);
        requestBuilder.addHeader(HEADER_ACCEPT, ENCODING_JSON);
        /**
         * The Request to be send
         */
        final Request request = requestBuilder.build();

        Response response = null;
        try {
            response = this.mOkHttpClient.newCall(request).execute();
            log(LogLevel.FULL, "HTTP <-- " + response.code() + " " + url);
        } catch (IOException e) {
            throw LeagueError.networkError(url, e);
        }
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw LeagueError.httpError(response.code(), url);
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
        return query(Endpoint.CHAMPION, region, mPathSegments, parameters, CachePolicy.NORMAL, 5, TimeUnit.MINUTES, Champion.class);
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
        return query(Endpoint.CHAMPION + matchId, region, mPathSegments, parameters, CachePolicy.NORMAL, 6, TimeUnit.HOURS, Champion.class);
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
        return query(Endpoint.CHAMPION_LIST, region, new PathSegments(), parameters, CachePolicy.NORMAL, 6, TimeUnit.HOURS, ChampionList.class);
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

        public LeagueApi build() {
            if (this.mApiKey == null) {
                throw new NullPointerException("You have to set an api token");
            }
            return new LeagueApi(this);
        }
    }

}
