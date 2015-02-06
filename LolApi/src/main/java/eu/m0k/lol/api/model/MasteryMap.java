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

public class MasteryMap extends HashMap<Long, MasteryPages> {

    /**
     * TypeAdapter required for GSON
     */
    public static class TypeAdapter implements JsonDeserializer<MasteryMap>, JsonSerializer<MasteryMap> {

        @Override
        public MasteryMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            MasteryMap masteryMap = new MasteryMap();
            for (java.util.Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                try {
                    final MasteryPages masteryPages = context.deserialize(json.getAsJsonObject().get(entry.getKey()), MasteryPages.class);
                    final Long id = Long.parseLong(entry.getKey());
                    if (masteryPages != null)
                        masteryMap.put(id, masteryPages);
                } catch (NumberFormatException exception) {

                }
            }
            return masteryMap;
        }

        @Override
        public JsonElement serialize(MasteryMap src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            for (Long id : src.keySet()) {
                object.add("" + id, context.serialize(src.get(id)));
            }
            return object;
        }
    }
}
