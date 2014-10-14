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

import eu.m0k.lol.api.picasso.Constants;

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

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getSanitizedDescription() {
        return mSanitizedDescription;
    }

    public Image getImage() {
        return mImage;
    }

    public String getImageUri() {
        return Constants.PATH_IMG_SPELL + this.getImage().getFull();
    }
}
