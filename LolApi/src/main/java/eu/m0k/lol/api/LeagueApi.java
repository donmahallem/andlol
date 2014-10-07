/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import eu.m0k.lol.api.internal.LeagueError;
import eu.m0k.lol.api.internal.MainThreadExecutor;
import eu.m0k.lol.api.internal.Util;
import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.ApiToken;
import eu.m0k.lol.api.network.LeagueRequest;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.m0k.lol.api.network.Parameters;

/**
 * Created by Don on 03.10.2014.
 */
public class LeagueApi {
    private static final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
    /**
     * The Api token to be used
     */
    private final ApiToken mApiToken;
    /**
     * The OkHttp Client to be used
     */
    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private final Gson mGson;

    private LeagueApi(Builder builder) {
        this.mGson = new Gson();
        this.mApiToken = builder.getApiToken();
    }

    public LeagueResponse query(LeagueRequest request) throws IOException {
        return null;
    }

    private <T> LeagueResponse<T> queryNetwork(String url, Parameters parameters, Class<T> clazz) {
        final String _url = url + "?" + Util.parametersToString(parameters);
        Request request = new Request.Builder().url(_url).build();
        Response response = null;
        try {
            response = this.mOkHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw LeagueError.networkError(_url, e);
        }
        if (response.isSuccessful()) {
            T obj = this.mGson.fromJson(new BufferedReader(new InputStreamReader(response.body().byteStream())), clazz);
            if (obj == null) {
                throw LeagueError.conversionError("Could not convert", _url, clazz);
            }
            return new LeagueResponse<T>(_url, 20, "", response.headers(), obj);
        } else {
            throw LeagueError.httpError(response.code(), _url);
        }
    }

    public LeagueResponse<Champion> getChampion(int champion, Region region) throws IOException {
        return this.getChampion(champion, region, null, null);
    }

    public LeagueResponse<Champion> getChampion(int champion, Region region, Locale locale) throws IOException {
        return this.getChampion(champion, region, null, locale);
    }

    public LeagueResponse<Champion> getChampion(int champion, Region region, ChampData champData) throws IOException {
        return this.getChampion(champion, region, champData, null);
    }

    public LeagueResponse<Champion> getChampion(int champion, Region region, ChampData champData, Locale locale) throws IOException {
        return this.getChampion(champion, region, champData, locale, true);
    }

    public LeagueResponse<Champion> getChampion(int champion, Region region, ChampData champData, Locale locale, boolean cache) throws IOException {
        if (champion < 0)
            throw new IllegalArgumentException("Champion ID must be greater then 0");
        if (region == null)
            throw new IllegalArgumentException("Region must not be null");
        Parameters parameters = new Parameters();
        parameters.put(champData);
        parameters.put(region);
        parameters.put(locale);
        return queryNetwork(Endpoint.CHAMPION + champion, parameters, Champion.class);
    }

    /**
     * Retrieves the Championlist
     * @param region region to query
     * @param champData ChampData to query
     * @param locale Gets the ChampData in this Language
     * @param cache Gets this Data from Cache or Network
     * @return the LeagueResponse or null if it fails
     * @throws IOException
     */
    public LeagueResponse<ChampionList> getChampionList(Region region, ChampData champData, Locale locale, boolean cache) throws IOException {
        if (region == null)
            throw new IllegalArgumentException("Region must not be null");
        Parameters parameters = new Parameters();
        parameters.put(champData);
        parameters.put(region);
        parameters.put(locale);
        return queryNetwork(Endpoint.CHAMPION, parameters, ChampionList.class);
    }

    /**
     * Builder for the League Api
     */
    public static class Builder {
        private ApiToken mApiToken;

        public ApiToken getApiToken() {
            return mApiToken;
        }

        public Builder setApiToken(ApiToken apiToken) {
            this.mApiToken = apiToken;
            return this;
        }

        public LeagueApi build() {
            if (this.mApiToken == null) {
                throw new NullPointerException("You have to set an api token");
            }
            return new LeagueApi(this);
        }
    }

}
