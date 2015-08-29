package eu.m0k.lol.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.okhttp.HttpUrl;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Version;

/**
 * Created by Don on 19/08/2015.
 */
public final class LolApi {
    public final static class Static {
        public static Response<ChampionList> getChampionList(@NonNull LeagueClient leagueClient,
                                                             @NonNull Region region,
                                                             @Nullable Locale locale,
                                                             @Nullable  ChampData champData) {
            return getChampionList(leagueClient,region,locale,null,champData);
        }

        public static Response<ChampionList> getChampionList(@NonNull LeagueClient leagueClient,
                                                             @NonNull  Region region,
                                                             @Nullable  ChampData champData) {
            return getChampionList(leagueClient,region,null,null,champData);
        }
        public static Response<ChampionList> getChampionList(@NonNull LeagueClient leagueClient,
                                                             @NonNull  Region region,
                                                             @Nullable Locale locale,
                                                             @Nullable  Version version,
                                                             @Nullable  ChampData champData) {
            HttpUrl.Builder builder = leagueClient.createDefaultUrlBuilder(region);
            if (version != null)
                builder.setQueryParameter("version", version.toString());
            if (locale != null)
                builder.setQueryParameter("locale", locale.getLocal());
            if (champData != null)
                builder.setQueryParameter("champData", champData.toString());
            return leagueClient.query(builder, ChampionList.class);
        }

    }
}
