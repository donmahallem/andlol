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
import java.util.ArrayList;
import java.util.Iterator;

public class MasteryTree extends ArrayList<MasteryTreeItem> {
    public static final class TypeAdapter implements JsonDeserializer<MasteryTree>, JsonSerializer<MasteryTree> {

        @Override
        public MasteryTree deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final MasteryTree masteryTree = new MasteryTree();
            final Iterator<JsonElement> rows = json.getAsJsonArray().iterator();
            int x = 0;
            while (rows.hasNext()) {
                final JsonElement row = rows.next();
                final Iterator<JsonElement> columns = row.getAsJsonArray().iterator();
                int y = 0;
                while (columns.hasNext()) {
                    final JsonElement column = columns.next();
                    if (column != null) {
                        final MasteryTreeItem masteryTreeItem = context.deserialize(column, MasteryTreeItem.class);
                        masteryTreeItem.x = x;
                        masteryTreeItem.y = y;
                        masteryTree.add(masteryTreeItem);
                    }
                }
                x++;
            }
            return masteryTree;
        }

        @Override
        public JsonElement serialize(MasteryTree src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive("");
        }
    }
}
