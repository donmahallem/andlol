/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Ladder {
    private League mRankedSolo;
    private League mRankedTeam3x3;
    private League mRankedTeam5x5;

    public League getRankedSolo() {
        return mRankedSolo;
    }

    public League getRankedTeam3x3() {
        return mRankedTeam3x3;
    }

    public League getRankedTeam5x5() {
        return mRankedTeam5x5;
    }

    /**
     * TypeAdapter required for GSON
     */
    public static class TypeAdapter implements JsonDeserializer<Ladder>, JsonSerializer<Ladder> {

        @Override
        public Ladder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final Ladder ladder = new Ladder();
            final JsonArray items = json.getAsJsonArray();
            for (JsonElement element : items) {
                final League league = context.deserialize(element, League.class);
                switch (league.getQueue()) {
                    case RANKED_SOLO_5x5:
                        ladder.mRankedSolo = league;
                        break;
                    case RANKED_TEAM_3x3:
                        ladder.mRankedTeam3x3 = league;
                        break;
                    case RANKED_TEAM_5x5:
                        ladder.mRankedTeam5x5 = league;
                        break;
                }
            }
            return ladder;
        }

        @Override
        public JsonElement serialize(Ladder src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }
}
