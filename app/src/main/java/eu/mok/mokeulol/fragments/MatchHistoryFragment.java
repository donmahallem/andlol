/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.m0k.lol.api.model.MatchHistory;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.RVMatchAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MatchHistoryFragment extends LeagueFragment implements SwipeRefreshLayout.OnRefreshListener {
    private final static String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RVMatchAdapter mRVMatchAdapter = new RVMatchAdapter();
    private long mSummonerId = -1;
    private Region mRegion;
    private Callback<MatchHistory> MATCH_CALLBACK = new Callback<MatchHistory>() {
        @Override
        public void success(MatchHistory matches, Response response) {
            MatchHistoryFragment.this.mRVMatchAdapter.setMatches(matches);
        }

        @Override
        public void failure(RetrofitError error) {
        }
    };

    public static Fragment getInstance(Region region, Summoner summoner) {
        return getInstance(region, summoner.getId());
    }

    public static Fragment getInstance(Region region, long summonerId) {
        final MatchHistoryFragment matchHistoryFragment = new MatchHistoryFragment();
        final Bundle bundle = new Bundle();
        bundle.putLong(KEY_SUMMONER_ID, summonerId);
        bundle.putSerializable(KEY_REGION, region);
        matchHistoryFragment.setArguments(bundle);
        return matchHistoryFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSummonerId = this.getArguments().getLong(KEY_SUMMONER_ID);
        this.mRegion = (Region) this.getArguments().getSerializable(KEY_REGION);
        this.mLayoutManager = new LinearLayoutManager(Util.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_match_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_container);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mRVMatchAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.getLeagueApi().getMatchHistoryEndpoint(this.mRegion).getMatchHistory(this.mSummonerId, 0, 15, MATCH_CALLBACK);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MatchHistoryFragment.this.mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 5000);
    }
}
