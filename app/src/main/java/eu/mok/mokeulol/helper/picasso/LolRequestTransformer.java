package eu.mok.mokeulol.helper.picasso;

import android.net.Uri;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import eu.m0k.lol.api.picasso.SCHEME;

/**
 * Created by Don on 30.09.2014.
 */
public class LolRequestTransformer implements Picasso.RequestTransformer {
    @Override
    public Request transformRequest(Request request) {
        if (request.uri.getScheme().equals(SCHEME.SPELL)) {
            return request.buildUpon().setUri(Uri.parse(request.uri.toString().replace(SCHEME.SPELL + "://", "https://ddragon.leagueoflegends.com/cdn/4.17.1/img/spell/"))).build();
        } else if (request.uri.getScheme().equals(SCHEME.CHAMPION_ICON)) {
            return request.buildUpon().setUri(Uri.parse(request.uri.toString().replace(SCHEME.CHAMPION_ICON + "://", "https://ddragon.leagueoflegends.com/cdn/4.17.1/img/champion/") + ".png")).build();
        } else if (request.uri.getScheme().equals(SCHEME.SPLASH)) {
            return request.buildUpon().setUri(Uri.parse(request.uri.toString().replace(SCHEME.SPLASH + "://", "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/"))).build();
        } else
            return request;
    }
}
