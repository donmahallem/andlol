/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.fragments.SummonerDetailFragment;
import eu.mok.mokeulol.view.ListenerScrollView;
import eu.mok.mokeulol.view.StickyHeadContainerView;

public class SummonerDetailsActivity extends LeagueActivity {
    private final String FRAGMENT_DETAILS = "fragment_details";
    private Drawable mActionBarBackgroundDrawable;
    private ListenerScrollView mScrollView;
    private StickyHeadContainerView mStickyContainerView;
    private ImageView mIvHeader;
    private Toolbar mToolbar;
    private ListenerScrollView.OnScrollListener ActionBarScrollListener = new ListenerScrollView.OnScrollListener() {
        @Override
        public void onScrollViewScrolled(int l, int t, int oldl, int oldt) {
            final int headerHeight = findViewById(R.id.icon).getHeight() - mToolbar.getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);

        }
    };
    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            mToolbar.setBackgroundDrawable(who);
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
            mStickyContainerView.setTopOffset(getSupportActionBar().getHeight());
            mStickyContainerView.invalidate();
        }
    };

    private Palette.PaletteAsyncListener ThemePaletteListener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            mToolbar.setBackgroundColor(palette.getMutedColor(R.color.light_blue_700));
            findViewById(R.id.test1).setBackgroundColor(palette.getMutedColor(R.color.light_blue_700));
            getWindow().setStatusBarColor(palette.getDarkMutedColor(R.color.light_blue_900));

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
        this.mScrollView = (ListenerScrollView) this.findViewById(R.id.scrollView);
        this.mScrollView.addOnScrollListener(ActionBarScrollListener);
        this.mStickyContainerView = (StickyHeadContainerView) this.findViewById(R.id.stickyHeadContainer);
//        this.mStickyContainerView.setTopOffset(getActionBar().getHeight());
//
        this.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(GlobalLayoutListener);

        ///////////////////////////////
        this.mIvHeader = (ImageView) findViewById(R.id.icon);
        Util.getPicasso()
                .load("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sona_1.jpg")
                .placeholder(android.R.drawable.ic_menu_rotate)
                .error(android.R.drawable.ic_delete)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mIvHeader.setImageDrawable(new BitmapDrawable(bitmap));
                        Palette.generateAsync(bitmap, ThemePaletteListener);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        mIvHeader.setImageDrawable(errorDrawable);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        mIvHeader.setImageDrawable(placeHolderDrawable);
                    }
                });
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//Title and subtitle
        mToolbar.setTitle("MY toolbar");
        mToolbar.setSubtitle("Subtitle");
        mToolbar.setBackgroundColor(getResources().getColor(R.color.light_blue_600));
//Menu
        mToolbar.inflateMenu(R.menu.toolbar_menu);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_share:
                        Toast.makeText(SummonerDetailsActivity.this, "Share", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
//Navigation Icon
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SummonerDetailsActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
            }
        });
        this.setSupportActionBar(mToolbar);
        mToolbar.setBackgroundDrawable(this.mActionBarBackgroundDrawable);
    }

    @Override
    public void onResume() {
        super.onResume();
        //this.mStickyContainerView.setTopOffset(getActionBar().getHeight());
    }

    private void showSummonerFragment() {
        FragmentTransaction mTransaction = getFragmentManager().beginTransaction();
        mTransaction.replace(android.R.id.content, new SummonerDetailFragment(), FRAGMENT_DETAILS);
        mTransaction.commit();
    }
}
