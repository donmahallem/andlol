/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import eu.mok.mokeulol.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences_cache_statistics from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}
