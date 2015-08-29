/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import eu.m0k.lol.api.LeagueClient;
import eu.mok.mokeulol.helper.picasso.LolRequestTransformer;

public class Util {
    private static Cache mOkHttpCache;
    private static OkHttpClient mOkHttpClient;
    private static Picasso mPicasso;
    private static Context mContext;
    private static LeagueClient mLeagueClient;

    /**
     * Inits with the given Context
     */
    public static void init(@NonNull Context context) {
        mContext = context;
    }

    /**
     * Retrieves and/or creates the OkHttpClient
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
            mOkHttpClient.setCache(getOkHttpCache());
        }
        return mOkHttpClient;
    }

    public static Cache getOkHttpCache() {
        if (mOkHttpCache == null) {
            mOkHttpCache = new Cache(new File(mContext.getCacheDir(), "app"), 50 * 1024 * 1024);
        }
        return mOkHttpCache;
    }

    /**
     * Retrieves and/or creates the Picasso Instance
     *
     * @return
     */
    public static Picasso getPicasso() {
        if (mPicasso == null) {
            Picasso.Builder builder = new Picasso.Builder(mContext);
            builder.downloader(new OkHttpDownloader(getOkHttpClient()));
            builder.requestTransformer(new LolRequestTransformer());
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    Log.e("Picasso Load Failed", uri.toString());
                }
            });
            mPicasso = builder.build();
        }
        return mPicasso;
    }


    public static LeagueClient getLeagueApi() {
        if (mLeagueClient == null) {
            mLeagueClient = new LeagueClient.Builder().cacheDir(new File(mContext.getCacheDir(), "api")).build();
        }
        return mLeagueClient;
    }

    public static String formatUsername(final String username) {
        String output = username.replaceAll("\\s", "");
        output = output.toLowerCase();
        return output;
    }

    public static Context getContext() {
        return mContext;
    }
}
