/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import eu.m0k.lol.api.network.LeagueResult;

final class LeagueResponseWrapper {
    public final LeagueResult response;
    public final Object responseBody;

    public LeagueResponseWrapper(LeagueResult response, Object responseBody) {
        this.response = response;
        this.responseBody = responseBody;
    }
}