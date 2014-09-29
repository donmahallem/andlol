package eu.m0k.lol.api.services;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.ChampionList;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Don on 29.09.2014.
 */
public interface StaticDataService {
    @GET("/v1.2/champion")
    public ChampionList getChampionList();

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") String locale);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") String locale, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") String locale, @Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public ChampionList getChampionList(@Query("locale") String locale, @Query("version") String version, @Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion/{id}")
    public ChampionList getChampionList(@Path("id") int id, @Query("locale") String locale, @Query("version") String version, @Query("champData") ChampData champData);
}
