/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

public class ApiKey {
    private String mToken;

    public ApiKey(String token) {
        this.mToken = token;
    }

    public String getToken() {
        return this.mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    @Override
    public String toString() {
        return "ApiToken{" +
                "mToken='" + mToken + '\'' +
                '}';
    }
}
