/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import timber.log.Timber;

public class LolApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
        Firebase.setAndroidContext(this);
        Timber.plant(new Timber.DebugTree());
        Firebase myFirebaseRef = new Firebase("https://drop-dots.firebaseio.com/");
        myFirebaseRef.child("foo").setValue("RANTANPLAN");
    }
}
