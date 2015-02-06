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
import android.support.v4.app.FragmentTransaction;

import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.fragments.MatchHistoryFragment;

public class SummonerDetailsActivity extends LeagueActivity {
    private static final String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region", FRAGMENT_MATCH_HISTORY = "fgrMatchHistory";

    private Region mRegion;
    private long mSummonerId;

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
        showMatchHistory();
    }

    @Override
    public void onResume() {
        super.onResume();
        //this.mStickyContainerView.setTopOffset(getActionBar().getHeight());
    }

    private void showMatchHistory() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fgrContent, MatchHistoryFragment.getInstance(this.mRegion, this.mSummonerId), FRAGMENT_MATCH_HISTORY);
        transaction.commit();
    }
}
