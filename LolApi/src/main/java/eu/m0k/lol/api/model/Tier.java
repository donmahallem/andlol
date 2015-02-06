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

public enum Tier {
    UNRANKED, BRONZE, SILVER, GOLD, PLATINUM, DIAMOND, MASTER, CHALLENGER;


    public static final class TypeAdapter implements JsonDeserializer<Tier>, JsonSerializer<Tier> {

        @Override
        public Tier deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return Tier.valueOf(json.getAsJsonPrimitive().getAsString());
            } catch (IllegalArgumentException exp) {
                return Tier.UNRANKED;
            }
        }

        @Override
        public JsonElement serialize(Tier src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name());
        }
    }
}
