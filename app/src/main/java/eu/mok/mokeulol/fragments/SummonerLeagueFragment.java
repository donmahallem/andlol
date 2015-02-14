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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.m0k.lol.api.model.LeagueSummonerMap;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.m0k.lol.api.model.SummonerIds;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.RVLeagueSummonerAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class SummonerLeagueFragment extends LeagueFragment implements SwipeRefreshLayout.OnRefreshListener {
    private final static String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private View mLoadingContainer;
    private RecyclerView.LayoutManager mLayoutManager;
    private RVLeagueSummonerAdapter mRVLeagueSummonerAdapter = new RVLeagueSummonerAdapter();
    private long mSummonerId = -1;
    private Callback<LeagueSummonerMap> LEAGUE_CALLBACK=new Callback<LeagueSummonerMap>() {
        @Override
        public void success(LeagueSummonerMap leagueEntryMap, Response response) {
            Timber.d("success - " + leagueEntryMap);
            if (leagueEntryMap.containsKey(mSummonerId)) {
                if (leagueEntryMap.get(mSummonerId).getRankedSolo5x5().size() > 0)
                    SummonerLeagueFragment.this.mRVLeagueSummonerAdapter
                            .setLeague(leagueEntryMap.get(mSummonerId).getRankedSolo5x5().get(0));
            }
            SummonerLeagueFragment.this.setLoading(false);
        }

        @Override
        public void failure(RetrofitError error) {
            Timber.d("failure - " + error.getLocalizedMessage());
            SummonerLeagueFragment.this.setLoading(false);
        }
    };
    private Region mRegion;

    public static Fragment getInstance(Region region, Summoner summoner) {
        return getInstance(region, summoner.getId());
    }

    public static Fragment getInstance(Region region, long summonerId) {
        final SummonerLeagueFragment matchHistoryFragment = new SummonerLeagueFragment();
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
        return inflater.inflate(R.layout.fragment_summoner_league, container, false);
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
        this.mLayoutManager = new LinearLayoutManager(view.getContext());
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mRVLeagueSummonerAdapter);
        this.mLoadingContainer = view.findViewById(R.id.loadingContainer);
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

    private void setLoading(final boolean loading) {
        this.mSwipeRefreshLayout.setRefreshing(loading);
        this.mSwipeRefreshLayout.setVisibility(loading ? View.GONE : View.VISIBLE);
        this.mLoadingContainer.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    private void refresh() {
        Util.getLeagueApi().getLeagueEndpoint(this.mRegion).getLeague(SummonerIds.create(this.mSummonerId), LEAGUE_CALLBACK);
        this.setLoading(true);
    }
}