package eu.m0k.lol.api.services;

import eu.m0k.lol.api.model.Match;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Don on 23.09.2014.
 */
public interface MatchService {
    @GET("/v2.2/match/{matchid}")
    public Match getScores(@Path("matchid") int id);
}
