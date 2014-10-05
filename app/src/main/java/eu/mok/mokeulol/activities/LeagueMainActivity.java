/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.ApiTokenFragment;
import eu.mok.mokeulol.fragments.ChampionFragment;
import eu.mok.mokeulol.fragments.LeagueFragment;

public class LeagueMainActivity extends FragmentActivity implements LeagueFragment.LeagueFragmentListener {
    private final String FRAGMENT_CHAMPION_LIST = "fgr_champ_list", FRAGMENT_CHAMPION_DETAIL = "fgr_champ_detail", FRAGMENT_API_TOKEN = "fgr_api_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Fragment fragment = this.getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (fragment == null) {
            onShowApiTokenFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowChampionListFragment() {

    }

    @Override
    public void onShowChampionDetailsFragment(int id) {
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.addToBackStack(FRAGMENT_CHAMPION_DETAIL);
        trans.replace(android.R.id.content, ChampionFragment.getInstance(id), FRAGMENT_CHAMPION_DETAIL);
        trans.commit();
    }

    @Override
    public void onShowApiTokenFragment() {
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.addToBackStack(FRAGMENT_API_TOKEN);
        trans.replace(android.R.id.content, ApiTokenFragment.getInstance(), FRAGMENT_API_TOKEN);
        trans.commit();
    }
}
