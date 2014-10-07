/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
