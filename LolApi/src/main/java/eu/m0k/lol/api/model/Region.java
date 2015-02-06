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

public enum Region {
    EUW("euw"), NA("na"), BR("br"), EUNE("eune"), KR("kr"),
    LAS("las"), LAN("lan"), OCE("oce"), TR("tr"), RU("ru"), STATIC("global"), UNKNOWN("unknown");

    private final String mRegion;

    Region(String name) {
        this.mRegion = name;
    }

    public String getRegion() {
        return mRegion;
    }

    @Override
    public String toString() {
        return mRegion;
    }

    public static final class TypeAdapter implements JsonDeserializer<Region>, JsonSerializer<Region> {

        @Override
        public Region deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return Region.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (IllegalArgumentException exp) {
                return Region.UNKNOWN;
            }
        }

        @Override
        public JsonElement serialize(Region src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name());
        }
    }
}
