package eu.mok.mokeulol.helper.picasso;

import android.net.Uri;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import eu.m0k.lol.api.picasso.Constants;

/**
 * Created by Don on 30.09.2014.
 */
public class LolRequestTransformer implements Picasso.RequestTransformer {
    @Override
    public Request transformRequest(Request request) {
        if (request.uri.getScheme().equals(Constants.SCHEME_SPELL)) {
            return request.buildUpon().setUri(Uri.parse(request.uri.toString().replace(Constants.SCHEME_SPELL + "://", "https://ddragon.leagueoflegends.com/cdn/4.17.1/img/spell/"))).build();
        } else if (request.uri.getScheme().equals(Constants.SCHEME_CHAMPION_ICON)) {
            return request.buildUpon().setUri(Uri.parse(request.uri.toString().replace(Constants.SCHEME_CHAMPION_ICON + "://", "https://ddragon.leagueoflegends.com/cdn/4.17.1/img/champion/") + ".png")).build();
        } else if (request.uri.getScheme().equals(Constants.SCHEME_SPLASH)) {
            return request.buildUpon().setUri(Uri.parse(request.uri.toString().replace(Constants.SCHEME_SPLASH + "://", "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/"))).build();
        } else
            return request;
    }
}
