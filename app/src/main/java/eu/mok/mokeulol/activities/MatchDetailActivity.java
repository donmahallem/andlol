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

import eu.m0k.lol.api.model.MatchDetail;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.fragments.MatchTimelineFragment;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class MatchDetailActivity extends LeagueActivity {
    private final static String KEY_REGION = "region", KEY_MATCH_ID = "matchId", FRAGMENT_TIMELINE = "fragmentTimeline";
    private Region mRegion;
    private long mMatchId;
    private MatchTimelineFragment mMatchDetailFragment = MatchTimelineFragment.getInstance();
    private Callback<MatchDetail> MATCH_CALLBACK = new Callback<MatchDetail>() {

        @Override
        public void success(MatchDetail matchDetail, Response response) {
            mMatchDetailFragment.setTimeLine(matchDetail);
        }

        @Override
        public void failure(RetrofitError error) {
            Timber.d("error: " + error.getLocalizedMessage());
        }
    };

    public static Intent generateIntent(final Context context, final Region region, final long matchId) {
        final Intent intent = new Intent(context, MatchDetailActivity.class);
        intent.putExtra(KEY_REGION, region);
        intent.putExtra(KEY_MATCH_ID, matchId);
        return intent;
    }

    @Override
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        this.setContentView(R.layout.activity_match_detail);
        this.mRegion = (Region) getIntent().getExtras().getSerializable(KEY_REGION);
        this.mMatchId = getIntent().getExtras().getLong(KEY_MATCH_ID, -1);
        showMatchTimeLine();
    }

    private void showMatchTimeLine() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fgrContent, mMatchDetailFragment, FRAGMENT_TIMELINE);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.getLeagueApi().getMatchEndpoint().getMatch(Region.EUW, this.mMatchId, true, MATCH_CALLBACK);
    }
}
