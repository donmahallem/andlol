/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.helper.picasso;

import android.net.Uri;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.util.List;

import eu.m0k.lol.api.picasso.Constants;

public class LolRequestTransformer implements Picasso.RequestTransformer {
    private final static String VERSION = "5.16.1";

    @Override
    public Request transformRequest(Request request) {
        if (request.uri.getScheme().equals(Constants.SCHEME)) {
            final List<String> pathSegments = request.uri.getPathSegments();
            if (request.uri.getHost().equals(Constants.IMAGE) && pathSegments.size() == 2) {
                if (pathSegments.get(0).equals(Constants.SPELL)) {
                    return request.buildUpon().setUri(Uri.parse("https://ddragon.leagueoflegends.com/cdn/" + VERSION + "/img/spell/" + pathSegments.get(1) + ".png")).build();
                } else if (pathSegments.get(0).equals(Constants.PASSIVE)) {
                    return request.buildUpon().setUri(Uri.parse("https://ddragon.leagueoflegends.com/cdn/" + VERSION + "/img/passive/" + pathSegments.get(1))).build();
                } else if (pathSegments.get(0).equals(Constants.CHAMPION_ICON)) {
                    return request.buildUpon().setUri(Uri.parse("https://ddragon.leagueoflegends.com/cdn/" + VERSION + "/img/champion/" + pathSegments.get(1) + ".png")).build();
                } else if (pathSegments.get(0).equals(Constants.SPLASH)) {
                    return request.buildUpon().setUri(Uri.parse("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + pathSegments.get(1) + ".jpg")).build();
                } else if (pathSegments.get(0).equals(Constants.LOADING)) {
                    return request.buildUpon().setUri(Uri.parse("https://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + pathSegments.get(1) + ".jpg")).build();
                } else if (pathSegments.get(0).equals(Constants.PROFILE_ICON)) {
                    return request.buildUpon().setUri(Uri.parse("https://ddragon.leagueoflegends.com/cdn/" + VERSION + "/img/profileicon/" + pathSegments.get(1) + ".png")).build();
                } else if (pathSegments.get(0).equals(Constants.ITEM)) {
                    return request.buildUpon().setUri(Uri.parse("https://ddragon.leagueoflegends.com/cdn/" + VERSION + "/img/item/" + pathSegments.get(1) + ".png")).build();
                }
            }
        }
        return request;
    }
}
