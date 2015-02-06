/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

public class PreferencesCacheStatistics extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences_cache_statistics from an XML resource
        addPreferencesFromResource(R.xml.preferences_cache_statistics);
        this.findPreference("pref_key_auto_delete").setSummary("" + Util.getLeagueApi().getCache().getRequestCount());
        this.findPreference("pref_key_sms_delete_limit").setSummary("" + Util.getLeagueApi().getCache().getHitCount());
        this.findPreference("pref_key_mms_delete_limit").setSummary("" + Util.getLeagueApi().getCache().getNetworkCount());
    }

}
