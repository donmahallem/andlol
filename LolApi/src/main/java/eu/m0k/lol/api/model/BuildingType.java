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

public enum BuildingType {
    TOWER_BUILDING, INHIBITOR_BUILDING;

    public static final class TypeAdapter implements JsonDeserializer<BuildingType>, JsonSerializer<BuildingType> {

        @Override
        public BuildingType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return BuildingType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (IllegalArgumentException exp) {
                return null;
            }
        }

        @Override
        public JsonElement serialize(BuildingType src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name());
        }
    }
}
