/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import com.google.gson.Gson;

import java.io.IOException;

public class LeagueError extends RuntimeException {
    private final String url;
    private final Gson converter;
    private final boolean networkError;

    private LeagueError(String message, String url, boolean networkError) {
        this(message, url, null, networkError, null);
    }

    private LeagueError(String message, String url, Gson converter, boolean networkError, Throwable exception) {
        super(message, exception);
        this.url = url;
        this.converter = converter;
        this.networkError = networkError;
    }

    @SuppressWarnings("unused")
    public static LeagueError networkError(String url, IOException exception) {
        return new LeagueError(exception.getMessage(), url, null, true, exception);
    }

    @SuppressWarnings("unused")
    public static LeagueError conversionError(String msg, String url, Class<?> clazz) {
        return new LeagueError(msg, url, null, false,
                null);
    }

    @SuppressWarnings("unused")
    public static LeagueError httpError(int code, String url) {
        return new LeagueError("HTTP Error: " + code, url, null, false, null);
    }

    public static LeagueError unexpectedError(String url, Throwable exception) {
        return new LeagueError(exception.getMessage(), url, null, false, exception);
    }

    public String getUrl() {
        return url;
    }


    public boolean isNetworkError() {
        return networkError;
    }

}