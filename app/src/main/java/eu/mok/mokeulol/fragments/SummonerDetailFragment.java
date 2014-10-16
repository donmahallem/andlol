/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.view.StickyHeadContainerView;
import eu.mok.mokeulol.view.StickyHeadScrollView;

public class SummonerDetailFragment extends LeagueFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Drawable mActionBarBackgroundDrawable;
    private StickyHeadScrollView mScrollView;
    private StickyHeadContainerView mStickyContainerView;
    private StickyHeadScrollView.OnScrollListener ActionBarScrollListener = new StickyHeadScrollView.OnScrollListener() {
        @Override
        public void onScrollViewScrolled(int l, int t, int oldl, int oldt) {
            final int headerHeight = getView().findViewById(R.id.icon).getHeight() - getActivity().getActionBar().getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);

        }
    };
    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            if (isAdded())
                getActivity().getActionBar().setBackgroundDrawable(who);
        }

        @Override
        public void scheduleDrawable(Drawable who, Runnable what, long when) {
        }

        @Override
        public void unscheduleDrawable(Drawable who, Runnable what) {
        }
    };

    public static Fragment getInstance() {
        return new SummonerDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_transluecent_background);
        this.mActionBarBackgroundDrawable.setAlpha(0);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activity.getActionBar().setBackgroundDrawable(this.mActionBarBackgroundDrawable);
        if (this.mStickyContainerView != null)
            this.mStickyContainerView.setTopOffset(this.getActivity().getActionBar().getHeight());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mScrollView = (StickyHeadScrollView) view.findViewById(R.id.scrollView);
        this.mScrollView.addOnScrollListener(ActionBarScrollListener);
        this.mStickyContainerView = (StickyHeadContainerView) view.findViewById(R.id.stickyHeadContainer);
        if (isAdded()) {
            this.mStickyContainerView.setTopOffset(this.getActivity().getActionBar().getHeight());
            getActivity().getActionBar().setBackgroundDrawable(this.mActionBarBackgroundDrawable);
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SummonerDetailFragment.this.mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 5000);
    }
}
