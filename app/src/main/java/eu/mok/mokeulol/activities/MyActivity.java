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
import eu.mok.mokeulol.fragments.ChampionFragment;
import eu.mok.mokeulol.fragments.ChampionListFragment;

public class MyActivity extends FragmentActivity implements ChampionListFragment.OnChampSelectedListener {
    private final String FRAGMENT_CHAMPION_LIST = "fgr_champ_list", FRAGMENT_CHAMPION_DETAIL = "fgr_champ_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Fragment fragment = this.getSupportFragmentManager().findFragmentByTag(FRAGMENT_CHAMPION_LIST);
        if (fragment == null) {
            fragment = new ChampionListFragment();
        }
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, fragment);
        trans.commit();
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
    public void onChampSelected(int champ) {
        showChamp(champ);
    }

    private void showChamp(int id) {
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.addToBackStack(FRAGMENT_CHAMPION_DETAIL);
        trans.replace(android.R.id.content, ChampionFragment.getInstance(id), FRAGMENT_CHAMPION_DETAIL);
        trans.commit();
    }
}
