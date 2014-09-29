package eu.m0k.lol.api.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import eu.m0k.lol.api.model.RunePage;

/**
 * Created by Don on 23.09.2014.
 */
public class RunePageResponse extends HashMap<String, List<RunePage>> {
    public static final class Deserializer implements JsonDeserializer<RunePageResponse> {

        @Override
        public RunePageResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            RunePageResponse list = new RunePageResponse();
            Iterator<Entry<String, JsonElement>> keys = json.getAsJsonObject().entrySet().iterator();
            while (keys.hasNext()) {
                Entry key = keys.next();
                Type type = new TypeToken<List<RunePage>>() {
                }.getType();
                list.put((String) key.getKey(), (List<RunePage>) context.deserialize(json.getAsJsonObject().get((String) key.getKey()).getAsJsonObject().get("pages"), type));
            }
            return list;
        }
    }
}
