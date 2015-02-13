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

public enum Platform {
    NA1, BR1, EUW1, LA1, LA2, OC1, EUN1, TR1, RU, KR;

    @Override
    public String toString() {
        return name();
    }

    public static final class TypeAdapter implements JsonDeserializer<Platform>, JsonSerializer<Platform> {

        @Override
        public Platform deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return Platform.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (IllegalArgumentException exp) {
                return null;
            }
        }

        @Override
        public JsonElement serialize(Platform src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name());
        }
    }
}
