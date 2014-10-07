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
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.ApiToken;
import eu.m0k.lol.api.network.LeagueRequest;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.m0k.lol.api.network.Parameter;
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

    private <T> LeagueResponse<T> queryNetwork(String url, Parameters parameters, Class<T> clazz) throws IOException {
        final String _url = url + "?" + Util.parametersToString(parameters);
        Request request = new Request.Builder().url(_url).build();
        Response response = this.mOkHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            T obj = this.mGson.fromJson(new BufferedReader(new InputStreamReader(response.body().byteStream())), clazz);
            if (obj == null) {
                throw new RuntimeException("Can not convert response");
            }
            return new LeagueResponse<T>(_url, 20, "", response.headers(), obj);
        }
        throw new LeagueError("Network error", _url, true);
    }
    public LeagueResponse<Champion> getChampion(int champion, ChampData champData, Region region, Locale locale, boolean cache) throws IOException {
        if (champion < 0)
            throw new IllegalArgumentException("Champion ID must be greater then 0");
        if (region == null)
            throw new IllegalArgumentException("Region must not be null");
        LeagueRequest.Builder<Champion> leagueBuilder = new LeagueRequest.Builder<Champion>();
        leagueBuilder.setUrl(Endpoint.CHAMPION + champion);
        leagueBuilder.setApiToken(this.mApiToken);
        leagueBuilder.setRegion(region);
        leagueBuilder.setType(Champion.class);
        if (champData != null)
            leagueBuilder.addParameter(Parameter.from(champData));
        if (locale != null)
            leagueBuilder.addParameter(Parameter.from(locale));
        LeagueRequest<Champion> request = leagueBuilder.build();
        //Response response = this.mOkHttpClient.newCall().execute();
        Response response = this.mOkHttpClient.newCall(new Request.Builder().url(request.getUrl()).build()).execute();
        if (response.isSuccessful()) {
            LeagueResponse<Champion> res = new LeagueResponse<Champion>(request.getUrl(), response.code(), "", response.headers(), new Champion());
        }
        return null;
    }

    private <T> LeagueResponse<T> queryAndTransform(Request request, Class<T> clazz) throws IOException {
        Response response = this.mOkHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            T t = mGson.fromJson(response.message(), clazz);
            if (t != null) {
                return new LeagueResponse<T>(response.request().urlString(), response.code(), "", null, null);
            }
        }
        return null;
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
