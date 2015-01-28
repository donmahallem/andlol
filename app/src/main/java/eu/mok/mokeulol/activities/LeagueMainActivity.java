/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.ApiTokenFragment;
import eu.mok.mokeulol.fragments.ChampionListFragment;
import eu.mok.mokeulol.fragments.LeagueFragment;

public class LeagueMainActivity extends LeagueActivity implements LeagueFragment.LeagueFragmentListener {
    private final String FRAGMENT_CHAMPION_LIST = "fgr_champ_list", FRAGMENT_CHAMPION_DETAIL = "fgr_champ_detail", FRAGMENT_API_TOKEN = "fgr_api_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = this.getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (fragment == null) {
            onShowChampionListFragment();
        }
    }

    @Override
    public void onShowChampionListFragment() {
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, ChampionListFragment.getInstance(), FRAGMENT_CHAMPION_LIST);
        trans.commit();
    }

    @Override
    public void onShowChampionDetailsFragment(int id) {
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.addToBackStack(FRAGMENT_CHAMPION_DETAIL);
        trans.commit();
    }

    @Override
    public void onShowApiTokenFragment() {
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, ApiTokenFragment.getInstance(), FRAGMENT_API_TOKEN);
        trans.commit();
    }
}
