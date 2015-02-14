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

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

public class MasteryPagesResponse {

    private long mSummonerId;
    private MasteryPages mMasteryPages;

    public MasteryPages getMasteryPages() {
        return mMasteryPages;
    }

    public static final class TypeAdapter implements JsonDeserializer<MasteryPagesResponse> {

        @Override
        public MasteryPagesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                if (json.getAsJsonObject().entrySet().size() == 1) {
                    final Iterator<Map.Entry<String, JsonElement>> iterator = json.getAsJsonObject().entrySet().iterator();
                    final MasteryPagesResponse masteryPagesResponse = new MasteryPagesResponse();
                    masteryPagesResponse.mMasteryPages = context.deserialize(iterator.next().getValue().getAsJsonObject().get("pages"), MasteryPages.class);
                    return masteryPagesResponse;
                }
            }
            return null;
        }
    }
}
