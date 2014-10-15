/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.LeagueFragment;
import eu.mok.mokeulol.fragments.SummonerDetailFragment;

public class LeagueActivity extends ActionBarActivity implements LeagueFragment.LeagueFragmentListener {

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
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, SummonerDetailFragment.getInstance(), FRAGMENT_SUMMONER_DETAILS);
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
