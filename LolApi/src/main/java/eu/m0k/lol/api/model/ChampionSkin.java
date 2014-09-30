package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampionSkin {
    private final static String BASE_URL = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("num")
    private int mNum;

    public static String getUrl(Champion champion, int num) {
        return BASE_URL + champion.getKey() + "_" + num + ".jpg";
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getNum() {
        return mNum;
    }

    public String getUrl(Champion champion) {
        return this.getUrl(champion, mNum);
    }

    @Override
    public String toString() {
        return "ChampionSkin{" +
                "id=" + mId +
                ", name='" + mName + '\'' +
                ", num=" + mNum +
                '}';
    }
}
