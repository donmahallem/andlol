/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import eu.m0k.lol.api.network.LeagueResponse;

final class LeagueResponseWrapper {
    final LeagueResponse response;
    final Object responseBody;

    public LeagueResponseWrapper(LeagueResponse response, Object responseBody) {
        this.response = response;
        this.responseBody = responseBody;
    }
}