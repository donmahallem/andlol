/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import eu.m0k.lol.api.network.LeagueResponse;

public interface LeagueCallback<T> {
    public void onSuccess(T t, LeagueResponse response);

    public void onFailure(LeagueError error);
}
