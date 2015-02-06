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

public enum AscendedType {
    CHAMPION_ASCENDED, CLEAR_ASCENDED, MINION_ASCENDED;

    public static final class TypeAdapter implements JsonDeserializer<AscendedType>, JsonSerializer<AscendedType> {

        @Override
        public AscendedType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return AscendedType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (IllegalArgumentException exp) {
                return null;
            }
        }

        @Override
        public JsonElement serialize(AscendedType src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name());
        }
    }
}
