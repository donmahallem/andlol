package eu.mok.mokeulol;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import eu.mok.mokeulol.helper.picasso.LolRequestTransformer;

/**
 * Created by Don on 30.09.2014.
 */
public class Util {
    private static Picasso mPicasso;
    private static OkHttpClient mOkHttpClient;
    private static Context mContext;

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
        }
        return mOkHttpClient;
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
}