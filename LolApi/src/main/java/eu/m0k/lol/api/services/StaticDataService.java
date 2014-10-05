/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.services;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Locale;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface StaticDataService {
    @GET("/v1.2/champion")
    public ChampionList getChampionList();

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") Locale locale);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") Locale locale, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") Locale locale, @Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") Locale locale, @Query("version") String version, @Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion/{id}")
    public Champion getChampion(@Path("id") int id, @Query("locale") Locale locale);

    @GET("/v1.2/champion/{id}")
    public Champion getChampion(@Path("id") int id, @Query("locale") Locale locale, @Query("champData") ChampData champData);

    @GET("/v1.2/champion/{id}")
    public Champion getChampion(@Path("id") int id, @Query("locale") Locale locale, @Query("version") String version, @Query("champData") ChampData champData);
}
