/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.m0k.lol.api.picasso.Constants;

public class Item {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("count")
    private int mCount;
    @Expose
    @SerializedName("name")
    private String mName;

    public static String getUri(final int id) {
        return Constants.PATH_IMG_ITEM_ICON + id;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getCount() {
        return mCount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
