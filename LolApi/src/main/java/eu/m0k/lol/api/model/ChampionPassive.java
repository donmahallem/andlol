package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampionPassive {
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("sanitizedDescription")
    private String mSanitizedDescription;
    @Expose
    @SerializedName("image")
    private Image mImage;
}
