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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.m0k.lol.api.model.TimeLine;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.RVMatchAdapter;

public class MatchTimelineFragment extends LeagueFragment {
    private final static String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RVMatchAdapter mRVMatchAdapter = new RVMatchAdapter();
    private long mSummonerId = -1;
    private Region mRegion;
    private TimeLine mTimeLine;

    public static Fragment getInstance(Region region, Summoner summoner) {
        return getInstance(region, summoner.getId());
    }

    public static Fragment getInstance(Region region, long summonerId) {
        final MatchTimelineFragment matchHistoryFragment = new MatchTimelineFragment();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_timeline, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mRVMatchAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setTimeLine(final TimeLine timeLine) {
        if (timeLine != null) {
            this.mTimeLine = timeLine;
        }
    }
}
