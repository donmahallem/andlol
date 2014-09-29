package eu.m0k.lol.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import eu.m0k.lol.api.model.MasteryList;
import eu.m0k.lol.api.model.NameList;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.response.RunePageResponse;
import eu.m0k.lol.api.services.MatchService;
import eu.m0k.lol.api.services.SummonerService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Don on 23.09.2014.
 */
public class RequestClient {
    private RestAdapter mRestAdapter;
    private OkHttpClient mOkHttpClient;
    private MatchService mMatchService;
    private Gson mGson;
    private SummonerService mSummonerApi;

    public RequestClient(Region region) {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.registerTypeAdapter(SummonerList.class, new SummonerList.Deserializer());
        builder.registerTypeAdapter(NameList.class, new NameList.Serializer());
        builder.registerTypeAdapter(MasteryList.class, new MasteryList.Deserializer());
        builder.registerTypeAdapter(RunePageResponse.class, new RunePageResponse.Deserializer());
        mGson = builder.create();
        mOkHttpClient = new OkHttpClient();
        mRestAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", "DD-Score-Bot");
                        request.addHeader("Accept", "application/json");
                        request.addQueryParam("api_key", "6a781363-6d22-4a4a-b31d-40e0de91ddfb");
                    }
                })
                .setClient(new OkClient(mOkHttpClient))
                .setConverter(new GsonConverter(mGson))
                .setEndpoint("https://" + region.toString() + ".api.pvp.net/api/lol/" + region.toString())
                .setLogLevel(RestAdapter.LogLevel.BASIC)
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

}
