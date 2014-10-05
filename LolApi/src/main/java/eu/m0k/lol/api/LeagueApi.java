/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import eu.m0k.lol.api.internal.MainThreadExecutor;
import eu.m0k.lol.api.network.LeagueRequest;
import eu.m0k.lol.api.network.LeagueResponse;

/**
 * Created by Don on 03.10.2014.
 */
public class LeagueApi {
    private static final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
    /**
     * The Api token to be used
     */
    private final String mApiToken;
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

    /**
     * Builder for the League Api
     */
    public static class Builder {
        private String mApiToken;

        public String getApiToken() {
            return mApiToken;
        }

        public Builder setApiToken(String apiToken) {
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
