package eu.mok.mokeulol;

import android.app.Application;

/**
 * Created by Don on 02.10.2014.
 */
public class LolApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
    }
}
