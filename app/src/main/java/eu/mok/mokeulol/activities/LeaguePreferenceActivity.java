/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.preference.PreferenceActivity;

import java.util.List;

import eu.mok.mokeulol.R;

public class LeaguePreferenceActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences, target);
    }

    @Override
    public boolean isValidFragment(String name) {
        return true;
    }
}
