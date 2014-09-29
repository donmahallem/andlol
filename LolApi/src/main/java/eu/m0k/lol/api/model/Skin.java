package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 25.09.2014.
 */
public class Skin {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("num")
    private int mNum;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getNum() {
        return mNum;
    }
}
