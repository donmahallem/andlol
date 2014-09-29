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

/**
 * Created by Don on 23.09.2014.
 */
public class MasteryList extends HashMap<String, Masteries> {

    public static final class Serializer implements JsonSerializer<MasteryList> {

        @Override
        public JsonElement serialize(MasteryList src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }

    public static final class Deserializer implements JsonDeserializer<MasteryList> {

        @Override
        public MasteryList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            MasteryList list = new MasteryList();
            Iterator<Entry<String, JsonElement>> keys = json.getAsJsonObject().entrySet().iterator();
            while (keys.hasNext()) {
                Entry key = keys.next();
                list.put((String) key.getKey(), (Masteries) context.deserialize((JsonElement) key.getValue(), Masteries.class));
            }
            return list;
        }
    }
}
