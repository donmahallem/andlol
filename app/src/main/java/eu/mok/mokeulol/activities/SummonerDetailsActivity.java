/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.fragments.SummonerDetailFragment;
import eu.mok.mokeulol.view.StickyHeadContainerView;
import eu.mok.mokeulol.view.StickyHeadScrollView;

public class SummonerDetailsActivity extends LeagueActivity {
    private final String FRAGMENT_DETAILS = "fragment_details";
    private Drawable mActionBarBackgroundDrawable;
    private StickyHeadScrollView mScrollView;
    private StickyHeadContainerView mStickyContainerView;
    private ImageView mIvHeader;

    private StickyHeadScrollView.OnScrollListener ActionBarScrollListener = new StickyHeadScrollView.OnScrollListener() {
        @Override
        public void onScrollViewScrolled(int l, int t, int oldl, int oldt) {
            final int headerHeight = findViewById(R.id.icon).getHeight() - getActionBar().getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);

        }
    };
    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            getActionBar().setBackgroundDrawable(who);
        }

        @Override
        public void scheduleDrawable(Drawable who, Runnable what, long when) {
        }

        @Override
        public void unscheduleDrawable(Drawable who, Runnable what) {
        }
    };

    private ViewTreeObserver.OnGlobalLayoutListener GlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            mStickyContainerView.setTopOffset(getActionBar().getHeight());
            mStickyContainerView.invalidate();
        }
    };
    @Override
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        this.setContentView(R.layout.activity_summoner_details);
        //if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null)
        //     showSummonerFragment();
        this.mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_transluecent_background);
        this.mActionBarBackgroundDrawable.setAlpha(0);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
        }
        this.mScrollView = (StickyHeadScrollView) this.findViewById(R.id.scrollView);
        this.mScrollView.addOnScrollListener(ActionBarScrollListener);
        this.mStickyContainerView = (StickyHeadContainerView) this.findViewById(R.id.stickyHeadContainer);
        this.mStickyContainerView.setTopOffset(getActionBar().getHeight());
        this.getActionBar().setBackgroundDrawable(this.mActionBarBackgroundDrawable);
        this.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(GlobalLayoutListener);

        ///////////////////////////////
        this.mIvHeader = (ImageView) findViewById(R.id.icon);
        Util.getPicasso()
                .load("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Draven_1.jpg")
                .placeholder(android.R.drawable.ic_menu_rotate)
                .error(android.R.drawable.ic_delete).into(this.mIvHeader);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mStickyContainerView.setTopOffset(getActionBar().getHeight());
    }

    private void showSummonerFragment() {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(android.R.id.content, new SummonerDetailFragment(), FRAGMENT_DETAILS);
        mTransaction.commit();
    }
}
