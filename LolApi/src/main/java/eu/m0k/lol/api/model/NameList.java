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
public class NameList extends HashMap<String, String> {

    public static final class Serializer implements JsonSerializer<NameList> {

        @Override
        public JsonElement serialize(NameList src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }

    public static final class Deserializer implements JsonDeserializer<NameList> {

        @Override
        public NameList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            NameList list = new NameList();
            Iterator<Entry<String, JsonElement>> keys = json.getAsJsonObject().entrySet().iterator();
            while (keys.hasNext()) {
                Entry key = keys.next();
                System.out.println((String) key.getKey());
                list.put((String) key.getKey(), (String) ((JsonElement) key.getValue()).getAsJsonPrimitive().getAsString());
            }
            return list;
        }
    }
}
