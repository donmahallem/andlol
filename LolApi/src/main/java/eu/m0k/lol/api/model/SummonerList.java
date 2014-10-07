/*
 * Copyright (c) 2014.
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
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;

public class SummonerList extends HashMap<String, Summoner> {

    public static final class Serializer implements JsonSerializer<SummonerList> {

        @Override
        public JsonElement serialize(SummonerList src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }

    public static final class Deserializer implements JsonDeserializer<SummonerList> {

        @Override
        public SummonerList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            SummonerList list = new SummonerList();
            Iterator<Entry<String, JsonElement>> keys = json.getAsJsonObject().entrySet().iterator();
            while (keys.hasNext()) {
                Entry key = keys.next();
                list.put((String) key.getKey(), (Summoner) context.deserialize((JsonElement) key.getValue(), Summoner.class));
            }
            return list;
        }
    }
}
