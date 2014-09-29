package eu.m0k.lol.api;

import android.support.annotation.NonNull;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

import eu.m0k.lol.api.model.Region;

/**
 * Created by Don on 24.09.2014.
 */
public class RequestClient2 {
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private final Region mRegion;
    private final String TOKEN;
    private OkHttpClient mOkHttpClient;

    public RequestClient2(@NonNull Region region, @NonNull String token) {
        this.mRegion = region;
        this.TOKEN = token;
        this.mOkHttpClient = new OkHttpClient();
    }

    public com.squareup.okhttp.Response request(@NonNull LolRequest request) throws IOException {
        request.addParameter("api_key", TOKEN);
        Request request1 = new Request.Builder()
                .url(request.getUrl())
                .build();
        return this.mOkHttpClient.newCall(request1).execute();
    }
}
