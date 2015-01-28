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

public class RuneMap extends HashMap<Long, RunePages> {

    /**
     * TypeAdapter required for GSON
     */
    public static class TypeAdapter implements JsonDeserializer<RuneMap>, JsonSerializer<RuneMap> {

        @Override
        public RuneMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            RuneMap runeMap = new RuneMap();
            for (Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                try {
                    final RunePages runePages = context.deserialize(json.getAsJsonObject().get(entry.getKey()), RunePages.class);
                    final Long id = Long.parseLong(entry.getKey());
                    if (runePages != null)
                        runeMap.put(id, runePages);
                } catch (NumberFormatException exception) {

                }
            }
            return runeMap;
        }

        @Override
        public JsonElement serialize(RuneMap src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            for (Long id : src.keySet()) {
                object.add("" + id, context.serialize(src.get(id)));
            }
            return object;
        }
    }
}
