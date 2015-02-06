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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import eu.m0k.lol.api.model.Champion;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.ChampionSkinFragment;

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
        }
    }

    private int getChampionId() {
        if (this.getIntent() == null || this.getIntent().getExtras() == null)
            return -1;
        return this.getIntent().getExtras().getInt(EXTRA_CHAMPION_ID, -1);
    }

    private void updateViews() {
        if (this.mChampion != null) {
            this.mChampionSkinFragmentAdapter = new ChampionSkinFragmentAdapter(this.getSupportFragmentManager(), this.mChampion);
            this.mViewPager.setAdapter(this.mChampionSkinFragmentAdapter);
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
            return ChampionSkinFragment.createInstance(this.mChampion.getKey(), i);
        }

        @Override
        public int getCount() {
            return this.mChampion.getSkins().size();
        }
    }
}
