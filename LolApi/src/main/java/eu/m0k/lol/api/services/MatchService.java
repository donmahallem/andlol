/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.services;

import eu.m0k.lol.api.model.Match;
import retrofit.http.GET;
import retrofit.http.Path;

public interface MatchService {
    @GET("/v2.2/match/{matchid}")
    public Match getScores(@Path("matchid") int id);
}
