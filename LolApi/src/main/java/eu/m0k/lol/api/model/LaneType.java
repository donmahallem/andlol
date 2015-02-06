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

public enum LaneType {
    BOT_LANE, MID_LANE, TOP_LANE;

    public static final class TypeAdapter implements JsonDeserializer<LaneType>, JsonSerializer<LaneType> {

        @Override
        public LaneType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return LaneType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (IllegalArgumentException exp) {
                return null;
            }
        }

        @Override
        public JsonElement serialize(LaneType src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name());
        }
    }
}
