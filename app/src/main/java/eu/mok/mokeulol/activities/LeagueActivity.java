/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.LeagueFragment;

public class LeagueActivity extends AppCompatActivity implements LeagueFragment.LeagueFragmentListener {
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
