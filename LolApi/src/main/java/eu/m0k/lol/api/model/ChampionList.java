package eu.m0k.lol.api.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampionList extends ArrayList<Champion> {
    private String mVersion;

    public String getVersion() {
        return mVersion;
    }

    @Override
    public String toString() {
        return "ChampionList{" +
                "version='" + mVersion + '\'' +
                "num=" + this.size() + +
                '}';
    }

    public static class Serializer implements JsonDeserializer<ChampionList>, JsonSerializer<ChampionList> {

        @Override
        public ChampionList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ChampionList list = new ChampionList();
            list.mVersion = json.getAsJsonObject().get("version").getAsString();
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().get("data").getAsJsonObject().entrySet()) {
                Champion champion = context.deserialize(json.getAsJsonObject().get("data").getAsJsonObject().get(entry.getKey()), Champion.class);
                if (champion != null)
                    list.add(champion);
            }
            return list;
        }

        @Override
        public JsonElement serialize(ChampionList src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }
    }
}
