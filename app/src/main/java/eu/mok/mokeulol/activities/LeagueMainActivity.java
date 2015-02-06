/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.ApiTokenFragment;
import eu.mok.mokeulol.fragments.ChampionListFragment;
import eu.mok.mokeulol.fragments.LeagueFragment;

public class LeagueMainActivity extends LeagueActivity implements LeagueFragment.LeagueFragmentListener {
    private final String FRAGMENT_CHAMPION_LIST = "fgr_champ_list", FRAGMENT_CHAMPION_DETAIL = "fgr_champ_detail", FRAGMENT_API_TOKEN = "fgr_api_token";
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        this.mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(this.mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(
                getResources().getColor(R.color.light_blue_900));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        Fragment fragment = this.getSupportFragmentManager().findFragmentById(R.id.fgrContent);
        if (fragment == null) {
            onShowChampionListFragment();
        }
    }

    @Override
    public void onShowChampionListFragment() {
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fgrContent, ChampionListFragment.getInstance(), FRAGMENT_CHAMPION_LIST);
        trans.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_activity, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START | Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
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
        trans.replace(R.id.fgrContent, ApiTokenFragment.getInstance(), FRAGMENT_API_TOKEN);
        trans.commit();
    }
}
