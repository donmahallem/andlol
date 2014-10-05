/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;

/**
 * Created by Don on 05.10.2014.
 */
public class LeagueError extends RuntimeException {
    private final String url;
    private final LeagueResponse response;
    private final Gson converter;
    private final Type successType;
    private final boolean networkError;
    LeagueError(String message, String url, LeagueResponse response, Gson converter,
                Type successType, boolean networkError, Throwable exception) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.converter = converter;
        this.successType = successType;
        this.networkError = networkError;
    }

    public static LeagueError networkError(String url, IOException exception) {
        return new LeagueError(exception.getMessage(), url, null, null, null, true, exception);
    }

    public static LeagueError conversionError(String url, LeagueResponse response, Gson converter,
                                              Type successType, ConversionException exception) {
        return new LeagueError(exception.getMessage(), url, response, converter, successType, false,
                exception);
    }

    public static LeagueError httpError(String url, LeagueResponse response, Gson converter,
                                        Type successType) {
        String message = response.getStatus() + " " + response.getReason();
        return new LeagueError(message, url, response, converter, successType, false, null);
    }

    public static LeagueError unexpectedError(String url, Throwable exception) {
        return new LeagueError(exception.getMessage(), url, null, null, null, false, exception);
    }

    public String getUrl() {
        return url;
    }

    public LeagueResponse getResponse() {
        return response;
    }

    public boolean isNetworkError() {
        return networkError;
    }

    public Object getBody() {
        return getBodyAs(successType);
    }

    public Type getSuccessType() {
        return successType;
    }

    public Object getBodyAs(Type type) {
        if (response == null) {
            return null;
        }
        TypedInput body = response.getBody();
        if (body == null) {
            return null;
        }
        try {
            return converter.fromJson(new BufferedReader(new InputStreamReader(body.in())), type);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}