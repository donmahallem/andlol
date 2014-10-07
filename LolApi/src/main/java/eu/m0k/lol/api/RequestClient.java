/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.ChampionSpell;
import eu.m0k.lol.api.model.MasteryList;
import eu.m0k.lol.api.model.NameList;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.response.RunePageResponse;
import eu.m0k.lol.api.services.MatchService;
import eu.m0k.lol.api.services.StaticDataService;
import eu.m0k.lol.api.services.SummonerService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RequestClient {
    private final String mToken;
    private RestAdapter mRestAdapter, mStaticDataRestAdapter;
    private OkHttpClient mOkHttpClient;
    private MatchService mMatchService;
    private Gson mGson;
    private SummonerService mSummonerApi;
    private StaticDataService mStaticDataApi;

    public RequestClient(@NonNull Region region, @NonNull String token, @NonNull RestAdapter.LogLevel logLevel) {
        this.mToken = token;
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.registerTypeAdapter(SummonerList.class, new SummonerList.Deserializer());
        builder.registerTypeAdapter(NameList.class, new NameList.Serializer());
        builder.registerTypeAdapter(MasteryList.class, new MasteryList.Deserializer());
        builder.registerTypeAdapter(RunePageResponse.class, new RunePageResponse.Deserializer());
        builder.registerTypeAdapter(ChampionList.class, new ChampionList.Serializer());
        builder.registerTypeAdapter(ChampionSpell.SpellRange.class, new ChampionSpell.SpellRange.Serializer());
        mGson = builder.create();
        mOkHttpClient = new OkHttpClient();
        final GsonConverter converter = new GsonConverter(this.mGson);
        mRestAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", "DD-Score-Bot");
                        request.addHeader("Accept", "application/json");
                        request.addQueryParam("api_key", mToken);
                    }
                })
                .setClient(new OkClient(mOkHttpClient))
                .setConverter(converter)
                .setEndpoint(new LeagueEndpoint(region, false))
                .setLogLevel(logLevel)
                .build();

        mStaticDataRestAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", "DD-Score-Bot");
                        request.addHeader("Accept", "application/json");
                        request.addQueryParam("api_key", mToken);
                    }
                })
                .setClient(new OkClient(mOkHttpClient))
                .setConverter(converter)
                .setEndpoint(new LeagueEndpoint(region, true))
                .setLogLevel(logLevel)
                .build();

    }

    public Gson getGson() {
        return mGson;
    }

    public MatchService getMatchApi() {
        if (mMatchService == null)
            mMatchService = mRestAdapter.create(MatchService.class);
        return mMatchService;
    }

    public SummonerService getSummonerApi() {
        if (mSummonerApi == null)
            mSummonerApi = mRestAdapter.create(SummonerService.class);
        return mSummonerApi;
    }

    public StaticDataService getStaticDataApi() {
        if (mStaticDataApi == null)
            mStaticDataApi = mRestAdapter.create(StaticDataService.class);
        return mStaticDataApi;
    }

}
