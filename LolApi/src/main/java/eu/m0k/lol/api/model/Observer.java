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

public class Observer {
    @Expose
    @SerializedName("encryptionKey")
    private String mEncryptionKey;

    public String getEncryptionKey() {
        return mEncryptionKey;
    }
}
