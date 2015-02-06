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

public class NameMap extends HashMap<Long, String> {


    /**
     * TypeAdapter required for GSON
     */
    public static class TypeAdapter implements JsonDeserializer<NameMap>, JsonSerializer<NameMap> {

        @Override
        public NameMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            NameMap nameMap = new NameMap();
            for (java.util.Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                try {
                    final String name = context.deserialize(json.getAsJsonObject().get(entry.getKey()), String.class);
                    final Long id = Long.parseLong(entry.getKey());
                    if (name != null)
                        nameMap.put(id, name);
                } catch (NumberFormatException exception) {

                }
            }
            return nameMap;
        }

        @Override
        public JsonElement serialize(NameMap src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            for (Long id : src.keySet()) {
                object.add("" + id, context.serialize(src.get(id)));
            }
            return object;
        }
    }
}
