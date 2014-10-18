/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.LeagueFragment;

public abstract class LeagueActivity extends Activity implements LeagueFragment.LeagueFragmentListener {

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
