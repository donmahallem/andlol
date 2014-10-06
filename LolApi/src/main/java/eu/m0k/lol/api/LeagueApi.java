/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import eu.m0k.lol.api.internal.MainThreadExecutor;
import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.ApiToken;
import eu.m0k.lol.api.network.LeagueRequest;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.m0k.lol.api.network.Parameter;

/**
 * Created by Don on 03.10.2014.
 */
public class LeagueApi {
    private static final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
    private final static Gson mGson;

    static {
        GsonBuilder builder = new GsonBuilder();

        mGson = builder.create();
    }

    /**
     * The Api token to be used
     */
    private final ApiToken mApiToken;
    /**
     * The OkHttp Client to be used
     */
    private final OkHttpClient mOkHttpClient = new OkHttpClient();

    private LeagueApi(Builder builder) {
        this.mApiToken = builder.getApiToken();
    }

    public LeagueResponse query(LeagueRequest request) throws IOException {
        return null;
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
