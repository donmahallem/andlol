/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol;

import android.app.Application;

public class LolApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
    }
}
