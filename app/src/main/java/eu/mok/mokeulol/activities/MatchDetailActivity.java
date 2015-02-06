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

import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;

public class MatchDetailActivity extends LeagueActivity {
    private final static String KEY_REGION = "region", KEY_MATCH_ID = "matchId";
    private Region mRegion;
    private long mMatchId;

    public static Intent generateIntent(final Context context, final Region region, final long matchId) {
        final Intent intent = new Intent(context, MatchDetailActivity.class);
        intent.putExtra(KEY_REGION, region);
        intent.putExtra(KEY_MATCH_ID, matchId);
        return intent;
    }

    @Override
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        this.setContentView(R.layout.activity_summoner_details);
        this.mRegion = (Region) getIntent().getExtras().getSerializable(KEY_REGION);
        this.mMatchId = getIntent().getExtras().getLong(KEY_MATCH_ID, -1);
        showMatchTimeLine();
    }

    private void showMatchTimeLine() {

    }

    @Override
    public void onResume() {
        super.onResume();
        //this.mStickyContainerView.setTopOffset(getActionBar().getHeight());
    }
}
