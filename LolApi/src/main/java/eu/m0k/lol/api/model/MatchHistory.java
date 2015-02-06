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
import java.util.ArrayList;

public class MatchHistory extends ArrayList<MatchSummary> {
    /**
     * TypeAdapter required for GSON
     */
    public static class TypeAdapter implements JsonDeserializer<MatchHistory>, JsonSerializer<MatchHistory> {

        @Override
        public MatchHistory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            MatchHistory matchHistory = new MatchHistory();
            final JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray("matches");
            for (JsonElement element : jsonArray)
                matchHistory.add((MatchSummary) context.deserialize(element, MatchDetail.class));
            return matchHistory;
        }

        @Override
        public JsonElement serialize(MatchHistory src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }
}
