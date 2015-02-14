/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Leagues {

    private static final Comparator<LeagueEntry> LEAGUE_ENTRY_COMPARATOR = new Comparator<LeagueEntry>() {
        @Override
        public int compare(LeagueEntry lhs, LeagueEntry rhs) {
            return rhs.getLeaguePoints() - lhs.getLeaguePoints();
        }
    };
    private ArrayList<League> mRankedTeam3x3 = new ArrayList<League>();
    private ArrayList<League> mRankedTeam5x5 = new ArrayList<League>();
    private ArrayList<League> mRankedSolo5x5 = new ArrayList<League>();

    public ArrayList<League> getRankedTeam3x3() {
        return mRankedTeam3x3;
    }

    public ArrayList<League> getRankedTeam5x5() {
        return mRankedTeam5x5;
    }

    public ArrayList<League> getRankedSolo5x5() {
        return mRankedSolo5x5;
    }

    public static final class TypeAdapter implements JsonDeserializer<Leagues>, JsonSerializer<Leagues> {

        @Override
        public Leagues deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final Iterator<JsonElement> leaguesIterator = json.getAsJsonArray().iterator();
            final Leagues leagues = new Leagues();
            while (leaguesIterator.hasNext()) {
                final League league = context.deserialize(leaguesIterator.next(), League.class);
                Collections.sort(league.getLeagueEntries(), LEAGUE_ENTRY_COMPARATOR);
                switch (league.getQueue()) {
                    case RANKED_TEAM_5x5:
                        leagues.mRankedTeam5x5.add(league);
                        break;
                    case RANKED_SOLO_5x5:
                        leagues.mRankedSolo5x5.add(league);
                        break;
                    case RANKED_TEAM_3x3:
                        leagues.mRankedTeam3x3.add(league);
                        break;
                }
            }
            return leagues;
        }

        @Override
        public JsonElement serialize(Leagues src, Type typeOfSrc, JsonSerializationContext context) {
            //TODO create serializer
            return new JsonPrimitive("");
        }
    }
}
