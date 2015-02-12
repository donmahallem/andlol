/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.m0k.lol.api.model.SummonerIds;
import eu.m0k.lol.api.model.SummonerList;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.fragments.ActiveMatchFragment;
import eu.mok.mokeulol.fragments.MatchHistoryFragment;
import eu.mok.mokeulol.view.SlidingTabLayout;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SummonerDetailsActivity extends LeagueActivity {
    private static final String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region", FRAGMENT_MATCH_HISTORY = "fgrMatchHistory";

    private Region mRegion;
    private long mSummonerId;
    private Toolbar mToolbar;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    public static Intent getInstance(final Context context, final Region region, final Summoner summoner) {
        return getInstance(context, region, summoner.getId());
    }

    public static Intent getInstance(final Context context, final Region region, final long summonerId) {
        final Intent intent = new Intent(context, SummonerDetailsActivity.class);
        intent.putExtra(KEY_SUMMONER_ID, summonerId);
        intent.putExtra(KEY_REGION, region);
        return intent;
    }

    @Override
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        this.setContentView(R.layout.activity_summoner_details);
        this.mRegion = (Region) getIntent().getExtras().getSerializable(KEY_REGION);
        this.mSummonerId = getIntent().getExtras().getLong(KEY_SUMMONER_ID, -1);
        //showMatchHistory();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new SamplePagerAdapter(this.getSupportFragmentManager()));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.getLeagueApi().getSummonerEndpoint(this.mRegion).getSummoners(SummonerIds.create(this.mSummonerId), new Callback<SummonerList>() {

            @Override
            public void success(SummonerList summoner, Response response) {
                if (summoner.containsKey(mSummonerId)) {
                    mToolbar.setTitle("" + summoner.get(mSummonerId).getName());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        //this.mStickyContainerView.setTopOffset(getActionBar().getHeight());
    }

    private void showMatchHistory() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fgrContent, MatchHistoryFragment.getInstance(this.mRegion, this.mSummonerId), FRAGMENT_MATCH_HISTORY);
        transaction.commit();
    }

    class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Site " + position;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return ActiveMatchFragment.getInstance(mRegion, mSummonerId);
                default:
                    return MatchHistoryFragment.getInstance(mRegion, mSummonerId);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
