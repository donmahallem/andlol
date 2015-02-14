/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.m0k.lol.api.model.MatchHistory;
import eu.m0k.lol.api.model.MatchSummary;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.activities.MatchDetailActivity;
import eu.mok.mokeulol.adapter.RVMatchAdapter;
import eu.mok.mokeulol.viewholder.MatchViewHolder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SummonerMatchHistoryFragment extends RefreshLayoutFragment implements MatchViewHolder.OnMatchSelectListener {
    private LinearLayoutManager mLinearLayoutManager;
    private RVMatchAdapter mRVMatchAdapter;
    private Callback<MatchHistory> MATCH_CALLBACK = new Callback<MatchHistory>() {
        @Override
        public void success(MatchHistory matches, Response response) {
            SummonerMatchHistoryFragment.this.mRVMatchAdapter.setMatches(matches);
            SummonerMatchHistoryFragment.this.setStatus(Status.LOADED);
        }

        @Override
        public void failure(RetrofitError error) {
            SummonerMatchHistoryFragment.this.setStatus(Status.ERROR);
        }
    };

    public static Fragment getInstance(Region region, Summoner summoner) {
        return getInstance(region, summoner.getId());
    }

    public static Fragment getInstance(Region region, long summonerId) {
        final SummonerMatchHistoryFragment summonerMatchHistoryFragment = new SummonerMatchHistoryFragment();
        final Bundle bundle = new Bundle();
        bundle.putLong(KEY_SUMMONER_ID, summonerId);
        bundle.putSerializable(KEY_REGION, region);
        summonerMatchHistoryFragment.setArguments(bundle);
        return summonerMatchHistoryFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_match_history, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        this.mRVMatchAdapter = new RVMatchAdapter();
        this.getRecyclerView().setLayoutManager(this.mLinearLayoutManager);
        this.getRecyclerView().setAdapter(this.mRVMatchAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    protected void refresh() {
        Util.getLeagueApi().getMatchHistoryEndpoint(this.getRegion()).getMatchHistory(this.getSummonerId(), 0, 15, MATCH_CALLBACK);
        this.setStatus(Status.LOADING);
    }

    @Override
    public void onMatchSelected(final MatchSummary match) {
        startActivity(MatchDetailActivity.generateIntent(getActivity(), match.getRegion(), match.getMatchId()));
    }
}
