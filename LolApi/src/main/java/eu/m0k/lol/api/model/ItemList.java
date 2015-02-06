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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ItemList extends ArrayList<Item> {

    public static final class TypeAdapter implements JsonDeserializer<ItemList>, JsonSerializer<ItemList> {

        @Override
        public ItemList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ItemList list = new ItemList();
            final JsonObject dataPoint = json.getAsJsonObject().get("data").getAsJsonObject();
            final Iterator<Map.Entry<String, JsonElement>> entrySet = dataPoint.entrySet().iterator();
            while (entrySet.hasNext()) {
                Map.Entry<String, JsonElement> key = entrySet.next();
                list.add((Item) context.deserialize(key.getValue(), Item.class));
            }
            return list;
        }

        @Override
        public JsonElement serialize(ItemList src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            return object;
        }
    }
}
