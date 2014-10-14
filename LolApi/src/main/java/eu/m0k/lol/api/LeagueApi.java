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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    private <T> LeagueResponse<T> getCache(String url, Class<T> clazz) throws IOException {
        synchronized (this) {
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi-Query", "CACHE --> " + url);
            }
            DiskLruCache.Snapshot snapShot = this.mDiskLruCache.get(url);
            if (Long.parseLong(snapShot.getString(CACHE_INDEX_EXPIRES)) < System.currentTimeMillis()) {
                snapShot.close();
                this.mDiskLruCache.remove(url);
                Log.d("LeagueApi-Query", "CACHE MISS <-- " + url);
                return null;
            }
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi-Query", "GSON --> Start Conversion");
            }
            T obj = this.mGson.fromJson(new BufferedReader(new InputStreamReader(snapShot.getInputStream(CACHE_INDEX_BODY))), clazz);
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi-Query", "GSON <-- End Conversion");
            }
            snapShot.close();
            LeagueResponse<T> response = new LeagueResponse<T>(url, 200, "", null, obj, true);
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi-Query", "CACHE HIT <-- " + url);
            }
            return response;
        }
    }

    private boolean putCache(String key, InputStream inputStream, long expires) throws IOException {
        synchronized (this) {
            DiskLruCache.Editor editor = this.mDiskLruCache.edit(key);
            OutputStream outputStream = editor.newOutputStream(CACHE_INDEX_BODY);
            byte[] buffer = new byte[1024];
            int len = inputStream.read(buffer);
            while (len != -1) {
                outputStream.write(buffer, 0, len);
                len = inputStream.read(buffer);
            }
            outputStream.close();
            editor.set(CACHE_INDEX_EXPIRES, "" + (System.currentTimeMillis() + expires));
            editor.commit();
            return true;
        }
    }

    private <T> LeagueResponse<T> query(final String url, final Region region, final PathSegments segments, final Parameters parameters, final CachePolicy cachePolicy, final Class<T> clazz) throws IOException {
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

        if (cachePolicy == CachePolicy.NORMAL) {
            LeagueResponse<T> response = getCache(_url, clazz);
            if (response != null) {
                /**
                 * Response was cached and will be delivered
                 */
                return response;
            }
            queryNetwork(_url, region, clazz);

        }
        return null;
    }

    private String createKeyHash(String url) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(url.getBytes("UTF-8"));
        return new String(md.digest());
    }

    private <T> LeagueResponse<T> queryNetwork(final String url, final Region region, final Class<T> clazz) {

        if (LogLevel.BASIC == this.mLogLevel) {
            Log.d("LeagueApi-Query", "HTTP --> " + url);
        }
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
            if (LogLevel.BASIC == this.mLogLevel) {
                Log.d("LeagueApi-Query", "HTTP <-- " + response.code() + " " + url);
            }
        } catch (IOException e) {
            throw LeagueError.networkError(url, e);
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
                throw LeagueError.conversionError("Could not convert", url, clazz);
            }
            return new LeagueResponse<T>(url, response.code(), "", response.headers(), obj, true);
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
        return query(Endpoint.CHAMPION, region, mPathSegments, parameters, CachePolicy.NORMAL, Champion.class);
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
        return query(Endpoint.CHAMPION + matchId, region, mPathSegments, parameters, CachePolicy.NORMAL, Champion.class);
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
        return query(Endpoint.CHAMPION, region, new PathSegments(), parameters, CachePolicy.NORMAL, ChampionList.class);
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
