package eu.m0k.lol.api.services;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Match;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Don on 29.09.2014.
 */
public interface StaticDataService {
    @GET("/v1.2/champion")
    public ChampionList getScores();

    @GET("/v1.2/champion")
    public String getScores(@Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public String getScores(@Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public String getScores(@Query("locale") String locale, @Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion")
    public String getScores(@Query("locale") String locale, @Query("version") String version, @Query("dataById") boolean databyId, @Query("champData") ChampData champData);

    @GET("/v1.2/champion/{id}")
    public Match getScores(@Path("id") int id, @Query("locale") String locale, @Query("version") String version, @Query("champData") ChampData champData);
}
