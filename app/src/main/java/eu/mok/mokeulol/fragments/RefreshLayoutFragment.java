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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;

abstract class RefreshLayoutFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    protected final static String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mTxtStatus;
    private Button mBtnRetry;
    private View mLoadingContainer;
    private long mSummonerId = -1;
    private Region mRegion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSummonerId = this.getArguments().getLong(KEY_SUMMONER_ID);
        this.mRegion = (Region) this.getArguments().getSerializable(KEY_REGION);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public Region getRegion() {
        return mRegion;
    }

    public long getSummonerId() {
        return mSummonerId;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mLoadingContainer = view.findViewById(R.id.loadingContainer);
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        this.mBtnRetry = (Button) view.findViewById(R.id.btnRetry);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_container);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mTxtStatus = (TextView) view.findViewById(R.id.txtStatus);
    }

    protected RecyclerView getRecyclerView() {
        return this.mRecyclerView;
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

    protected abstract void refresh();

    protected void setStatus(final Status status) {
        this.mSwipeRefreshLayout.setVisibility(status == Status.LOADED ? View.VISIBLE : View.GONE);
        this.mLoadingContainer.setVisibility(status == Status.LOADED ? View.GONE : View.VISIBLE);
        this.mTxtStatus.setText(status == Status.LOADING ? R.string.loading : R.string.error_requesting_server);
        this.mBtnRetry.setVisibility(status == Status.ERROR ? View.VISIBLE : View.GONE);
        this.mSwipeRefreshLayout.setRefreshing(status == Status.LOADING);
    }


    protected enum Status {
        LOADING, ERROR, LOADED;
    }

}
