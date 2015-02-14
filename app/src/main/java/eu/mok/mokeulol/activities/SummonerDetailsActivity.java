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
import eu.mok.mokeulol.fragments.SummonerLeagueFragment;
import eu.mok.mokeulol.fragments.SummonerMasteryPagesFragment;
import eu.mok.mokeulol.fragments.SummonerRunePagesFragment;
import eu.mok.mokeulol.view.SlidingTabLayout;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new SamplePagerAdapter(this.getSupportFragmentManager()));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.yellow_400));
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.getLeagueApi().getSummonerEndpoint(this.mRegion).getSummoners(SummonerIds.create(this.mSummonerId), new Callback<SummonerList>() {

            @Override
            public void success(SummonerList summoner, Response response) {
                Timber.d("Success: " + summoner);
                if (summoner.containsKey(mSummonerId)) {
                    mToolbar.setTitle("" + summoner.get(mSummonerId).getName());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 1:
                    return getResources().getString(R.string.match_history);
                case 2:
                    return getResources().getString(R.string.current_game);
                case 3:
                    return getResources().getString(R.string.league);
                case 4:
                    return getResources().getString(R.string.runes);
                case 5:
                    return getResources().getString(R.string.masteries);
                default:
                    return getResources().getString(R.string.summary);
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return MatchHistoryFragment.getInstance(mRegion, mSummonerId);
                case 2:
                    return ActiveMatchFragment.getInstance(mRegion, mSummonerId);
                case 3:
                    return SummonerLeagueFragment.getInstance(mRegion, mSummonerId);
                case 4:
                    return SummonerRunePagesFragment.getInstance(mRegion, mSummonerId);
                case 5:
                    return SummonerMasteryPagesFragment.getInstance(mRegion, mSummonerId);
                default:
                    return MatchHistoryFragment.getInstance(mRegion, mSummonerId);
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
}
