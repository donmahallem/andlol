/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.LeagueFragment;

/**
 * Created by Don on 11.10.2014.
 */
public class LeagueActivity extends ActionBarActivity implements LeagueFragment.LeagueFragmentListener {
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
