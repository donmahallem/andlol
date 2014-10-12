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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import eu.m0k.lol.api.model.Champion;
import eu.mok.mokeulol.fragments.ChampionSkinFragment;

/**
 * Created by Don on 11.10.2014.
 */
public class ChampionSkinActivity extends LeagueActivity {
    public final static String EXTRA_CHAMPION_KEY = "extra_key_champion";
    private ViewPager mViewPager;
    private ChampionSkinFragmentAdapter mChampionSkinFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mViewPager = new ViewPager(this);
        this.mChampionSkinFragmentAdapter = new ChampionSkinFragmentAdapter(this.getSupportFragmentManager(), new Champion());
    }

    private class ChampionSkinFragmentAdapter extends FragmentPagerAdapter {
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
