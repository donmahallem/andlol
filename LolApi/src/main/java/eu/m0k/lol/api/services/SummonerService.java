/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.services;

import eu.m0k.lol.api.model.MasteryList;
import eu.m0k.lol.api.model.NameList;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.response.RunePageResponse;
import retrofit.http.GET;
import retrofit.http.Path;

public interface SummonerService {
    @GET("/v1.4/summoner/by-name/{names}")
    public SummonerList getSummonersByName(@Path("names") String names);

    @GET("/v1.4/summoner/{summonerIds}")
    public SummonerList getSummonersById(@Path("summonerIds") String ids);

    @GET("/v1.4/summoner/{summonerIds}/masteries")
    public MasteryList getMasteriesById(@Path("summonerIds") String ids);

    @GET("/v1.4/summoner/{summonerIds}/name")
    public NameList getSummonerName(@Path("summonerIds") String ids);

    @GET("/v1.4/summoner/{summonerIds}/runes")
    public RunePageResponse getRunesById(@Path("summonerIds") String ids);
}
