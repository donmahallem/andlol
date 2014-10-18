/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import eu.mok.mokeulol.fragments.LeagueFragment;

public abstract class LeagueActivity extends ActionBarActivity implements LeagueFragment.LeagueFragmentListener {

    private final static String FRAGMENT_SUMMONER_DETAILS = "fragment_summoner_details";

    @Override
    public void onShowChampionListFragment() {

    }

    @Override
    public void onShowChampionDetailsFragment(int id) {

    }

    @Override
    public void onShowApiTokenFragment() {

    }

    @Override
    public void onShowSummonerDetailsFragment(long id) {
        Intent intent = new Intent(this, SummonerDetailsActivity.class);
        startActivity(intent);
    }

}
