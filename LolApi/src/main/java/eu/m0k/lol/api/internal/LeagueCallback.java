/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import eu.m0k.lol.api.network.LeagueResult;

public interface LeagueCallback<T> {
    public void onSuccess(T t, LeagueResult response);

    public void onFailure(LeagueError error);
}
