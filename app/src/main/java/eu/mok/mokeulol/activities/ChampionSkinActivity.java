/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.io.IOException;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

public class ChampionSkinActivity extends LeagueActivity {
    public final static String EXTRA_CHAMPION_ID = "extra_id_champion";
    private ViewPager mViewPager;
    private ChampionSkinFragmentAdapter mChampionSkinFragmentAdapter;
    private Champion mChampion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mViewPager = new ViewPager(this);
        this.mViewPager.setId(R.id.viewPager);
        this.setContentView(this.mViewPager);
        if (this.getChampionId() != -1) {
            Task t = new Task();
            t.execute(this.getChampionId());
        }
    }

    private int getChampionId() {
        if (this.getIntent() == null || this.getIntent().getExtras() == null)
            return -1;
        return this.getIntent().getExtras().getInt(EXTRA_CHAMPION_ID, -1);
    }

    private void updateViews() {
        if (this.mChampion != null) {
            /*this.mChampionSkinFragmentAdapter = new ChampionSkinFragmentAdapter(this.getSupportFragmentManager(), this.mChampion);
            this.mViewPager.setAdapter(this.mChampionSkinFragmentAdapter);*/
        }
    }

    private class Task extends AsyncTask<Integer, Void, LeagueResponse<Champion>> {

        @Override
        protected LeagueResponse<Champion> doInBackground(Integer... params) {
            ChampData data = new ChampData();
            data.setSpells(true);
            data.setSkins(true);
            data.setLore(true);
            data.setInfo(true);
            data.setPassive(true);
            data.setImage(true);
            LeagueResponse<Champion> champ = null;
            try {
                champ = Util.getLeagueApi().getChampion(params[0], Region.EUW, data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return champ;
        }

        @Override
        protected void onPostExecute(LeagueResponse<Champion> result) {
            if (result != null && result.getBody() != null) {
                mChampion = result.getBody();
                updateViews();
            } else {
                int msg = 0;
                if (result == null) {
                    msg = R.string.unknown_error;
                } else {
                    msg = R.string.network_error;
                }
                Toast.makeText(ChampionSkinActivity.this, msg, Toast.LENGTH_LONG);
            }
        }
    }

    private class ChampionSkinFragmentAdapter extends FragmentStatePagerAdapter {
        private Champion mChampion;

        public ChampionSkinFragmentAdapter(FragmentManager fm, Champion champion) {
            super(fm);
            if (champion == null)
                throw new IllegalArgumentException("Champion must not be null");
            this.mChampion = champion;
        }

        @Override
        public Fragment getItem(int i) {
            return null;//ChampionSkinFragment.createInstance(this.mChampion.getKey(), i);
        }

        @Override
        public int getCount() {
            return this.mChampion.getSkins().size();
        }
    }
}
