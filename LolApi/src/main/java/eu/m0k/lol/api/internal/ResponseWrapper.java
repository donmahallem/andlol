/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import eu.m0k.lol.api.network.LeagueResponse;

/**
 * Created by Don on 05.10.2014.
 */
final class ResponseWrapper {
    final LeagueResponse response;
    final Object responseBody;

    ResponseWrapper(LeagueResponse response, Object responseBody) {
        this.response = response;
        this.responseBody = responseBody;
    }
}