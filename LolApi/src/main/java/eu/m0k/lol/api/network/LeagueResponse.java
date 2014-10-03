/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import com.squareup.okhttp.Response;

/**
 * Created by Don on 03.10.2014.
 */
public class LeagueResponse {
    private LeagueRequest mLeagueRequest;
    private Response mResponse;

    public LeagueResponse(LeagueRequest leagueRequest, Response response) {
        this.mLeagueRequest = leagueRequest;
        this.mResponse = response;
    }

    public LeagueRequest getLeagueRequest() {
        return mLeagueRequest;
    }

    public Response getResponse() {
        return mResponse;
    }
}
