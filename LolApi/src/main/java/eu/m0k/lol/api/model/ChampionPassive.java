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

    /**
     * gets the name of the passive
     *
     * @return the passive
     */
    public String getName() {
        return mName;
    }

    /**
     * gets the Passives Description
     * @return
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * gets the sanitized Description of the Passive
     * @return the sanitized Description
     */
    public String getSanitizedDescription() {
        return mSanitizedDescription;
    }

    /**
     * returns the Image containing infos
     * @return the Image Object for the Spell
     */
    public Image getImage() {
        return mImage;
    }

    /**
     * returns the ImageUri required for Picasso
     * @return
     */
    public String getImageUri() {
        return Constants.PATH_IMG_PASSIVE + this.getImage().getFull();
    }
}
