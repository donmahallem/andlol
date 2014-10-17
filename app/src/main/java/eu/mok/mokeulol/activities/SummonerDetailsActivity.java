/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import eu.mok.mokeulol.fragments.SummonerDetailFragment;

/**
 * Created by Don on 16.10.2014.
 */
public class SummonerDetailsActivity extends LeagueActivity {
    private final String FRAGMENT_DETAILS = "fragment_details";

    @Override
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null)
            showSummonerFragment();
    }

    private void showSummonerFragment() {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(android.R.id.content, new SummonerDetailFragment(), FRAGMENT_DETAILS);
        mTransaction.commit();
    }
}
