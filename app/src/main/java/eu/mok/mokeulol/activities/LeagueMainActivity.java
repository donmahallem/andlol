/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.ApiTokenFragment;
import eu.mok.mokeulol.fragments.ChampionDetailsFragment;
import eu.mok.mokeulol.fragments.ChampionListFragment;
import eu.mok.mokeulol.fragments.LeagueFragment;

public class LeagueMainActivity extends LeagueActivity implements LeagueFragment.LeagueFragmentListener {
    private final String FRAGMENT_CHAMPION_LIST = "fgr_champ_list", FRAGMENT_CHAMPION_DETAIL = "fgr_champ_detail", FRAGMENT_API_TOKEN = "fgr_api_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = this.getFragmentManager().findFragmentById(android.R.id.content);
        if (fragment == null) {
            onShowApiTokenFragment();
        }
    }

    @Override
    public void onShowChampionListFragment() {
        FragmentTransaction trans = this.getFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, ChampionListFragment.getInstance(), FRAGMENT_CHAMPION_LIST);
        trans.commit();
    }

    @Override
    public void onShowChampionDetailsFragment(int id) {
        FragmentTransaction trans = this.getFragmentManager().beginTransaction();
        trans.addToBackStack(FRAGMENT_CHAMPION_DETAIL);
        trans.replace(android.R.id.content, ChampionDetailsFragment.getInstance(id), FRAGMENT_CHAMPION_DETAIL);
        trans.commit();
    }

    @Override
    public void onShowApiTokenFragment() {
        FragmentTransaction trans = this.getFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, ApiTokenFragment.getInstance(), FRAGMENT_API_TOKEN);
        trans.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, LeaguePreferenceActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_summoner_detail:
                this.onShowSummonerDetailsFragment(99);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
