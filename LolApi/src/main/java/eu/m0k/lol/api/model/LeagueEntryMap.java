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
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;

public class LeagueEntryMap extends HashMap<Long, Ladder> {

    /**
     * TypeAdapter required for GSON
     */
    public static class TypeAdapter implements JsonDeserializer<LeagueEntryMap>, JsonSerializer<LeagueEntryMap> {

        @Override
        public LeagueEntryMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            LeagueEntryMap leagueEntryMap = new LeagueEntryMap();
            for (java.util.Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                try {
                    final Ladder leagueEntry = context.deserialize(json.getAsJsonObject().get(entry.getKey()), Ladder.class);
                    final Long id = Long.parseLong(entry.getKey());
                    if (id != null)
                        leagueEntryMap.put(id, leagueEntry);
                } catch (NumberFormatException exception) {

                }
            }
            return leagueEntryMap;
        }

        @Override
        public JsonElement serialize(LeagueEntryMap src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            for (Long id : src.keySet()) {
                object.add("" + id, context.serialize(src.get(id)));
            }
            return object;
        }
    }
}
