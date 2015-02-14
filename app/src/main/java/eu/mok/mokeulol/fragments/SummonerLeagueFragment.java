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

public class SummonerLeagueFragment extends RefreshLayoutFragment {
    private LinearLayoutManager mLinearLayoutManager;
    private RVLeagueSummonerAdapter mRVLeagueSummonerAdapter;

    private Callback<LeagueSummonerMap> LEAGUE_CALLBACK=new Callback<LeagueSummonerMap>() {
        @Override
        public void success(LeagueSummonerMap leagueEntryMap, Response response) {
            Timber.d("success - " + leagueEntryMap);
            if (leagueEntryMap.containsKey(getSummonerId())) {
                if (leagueEntryMap.get(getSummonerId()).getRankedSolo5x5().size() > 0)
                    SummonerLeagueFragment.this.mRVLeagueSummonerAdapter
                            .setLeague(leagueEntryMap.get(getSummonerId()).getRankedSolo5x5().get(0));
            }
            SummonerLeagueFragment.this.setStatus(Status.LOADED);
        }

        @Override
        public void failure(RetrofitError error) {
            Timber.d("failure - " + error.getLocalizedMessage());
            SummonerLeagueFragment.this.setStatus(Status.ERROR);
        }
    };

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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_match_history, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        this.mRVLeagueSummonerAdapter = new RVLeagueSummonerAdapter();
        this.getRecyclerView().setLayoutManager(this.mLinearLayoutManager);
        this.getRecyclerView().setAdapter(this.mRVLeagueSummonerAdapter);
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
        Util.getLeagueApi().getLeagueEndpoint(this.getRegion()).getLeague(SummonerIds.create(this.getSummonerId()), LEAGUE_CALLBACK);
        this.setStatus(Status.LOADING);
    }

}


