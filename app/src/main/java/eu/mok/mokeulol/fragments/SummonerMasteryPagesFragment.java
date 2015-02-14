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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.m0k.lol.api.model.MasteryPagesResponse;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.RVMasteryPageAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SummonerMasteryPagesFragment extends LeagueFragment implements SwipeRefreshLayout.OnRefreshListener {
    private final static String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private RVMasteryPageAdapter mRVMasteryPageAdapter = new RVMasteryPageAdapter();
    private long mSummonerId = -1;
    private Region mRegion;
    private Callback<MasteryPagesResponse> MASTERY_CALLBACK = new Callback<MasteryPagesResponse>() {
        @Override
        public void success(MasteryPagesResponse matches, Response response) {
            SummonerMasteryPagesFragment.this.mRVMasteryPageAdapter.setMasteryPages(matches.getMasteryPages());
            SummonerMasteryPagesFragment.this.mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void failure(RetrofitError error) {
            SummonerMasteryPagesFragment.this.mSwipeRefreshLayout.setRefreshing(false);
        }
    };

    public static Fragment getInstance(Region region, Summoner summoner) {
        return getInstance(region, summoner.getId());
    }

    public static Fragment getInstance(Region region, long summonerId) {
        final SummonerMasteryPagesFragment matchHistoryFragment = new SummonerMasteryPagesFragment();
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
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_mastery_pages, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_container);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        this.mGridLayoutManager = new GridLayoutManager(view.getContext(), view.getContext().getResources().getInteger(R.integer.columns));
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(this.mGridLayoutManager);
        this.mRecyclerView.setAdapter(this.mRVMasteryPageAdapter);
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

    private void refresh() {
        Util.getLeagueApi().getSummonerEndpoint(this.mRegion).getMasteryPages(this.mSummonerId, MASTERY_CALLBACK);
        this.mSwipeRefreshLayout.setRefreshing(true);
    }

}
