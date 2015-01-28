/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.SkinListAdapter;
import eu.mok.mokeulol.view.ChampionPassiveView;
import eu.mok.mokeulol.view.ChampionSpellView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChampionDetailsActivity extends LeagueActivity implements ObservableScrollViewCallbacks {
    private final static String EXTRA_CHAMP_ID = "champid", KEY_THEME_COLOR = "keyThemeColor";
    private TextView mTxtTitle, mTxtSubTitle, mTxtDescription, mTxtLore;
    private CircularImageView mIvChampIcon;
    private ChampionSpellView mChampionSpellView1, mChampionSpellView2, mChampionSpellView3, mChampionSpellView4;
    private SkinListAdapter mSkinListAdapter = new SkinListAdapter();
    private ChampionPassiveView mChampionPassiveView;
    private Champion mChampion;
    private Callback<Champion> CHAMPION_CALLBACK = new Callback<Champion>() {
        @Override
        public void success(Champion champion, Response response) {
            ChampionDetailsActivity.this.setLoading(false);
            ChampionDetailsActivity.this.mChampion = champion;
            updateViews();
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };
    private ImageView mIvHeader;
    private Toolbar mToolbar;
    private int mChampionId = 32;
    private ObservableScrollView mListenerScrollView;
    private ProgressBar mIvHeaderProgressBar;
    private View mLoadingContainer;
    private int mThemeColor = -1;
    private Palette.PaletteAsyncListener IvHeaderAsyncListener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            /**
             * If Lollipop set StatusBarColor call
             */
            setThemeColors(palette.getVibrantColor(R.color.light_blue_700));
        }
    };
    private ValueAnimator.AnimatorUpdateListener HeaderImageAlphaAnimator = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mIvHeader.setAlpha((Integer) animation.getAnimatedValue());
        }
    };
    private Target IvHeaderTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mIvHeader.setVisibility(View.VISIBLE);
            mIvHeaderProgressBar.setVisibility(View.GONE);
            mIvHeader.setImageDrawable(new BitmapDrawable(bitmap));
            Palette.generateAsync(bitmap, IvHeaderAsyncListener);
            ValueAnimator imageFade = ValueAnimator.ofObject(new IntEvaluator(), 0, 255);
            imageFade.setDuration(250);
            imageFade.addUpdateListener(HeaderImageAlphaAnimator);
            imageFade.start();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            mIvHeader.setImageDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            mIvHeader.setImageDrawable(placeHolderDrawable);
            mIvHeader.setVisibility(View.GONE);
            mIvHeaderProgressBar.setVisibility(View.VISIBLE);
        }
    };


    public static Intent createIntent(Activity activity, int id) {
        Intent intent = new Intent(activity, ChampionDetailsActivity.class);
        intent.putExtra(EXTRA_CHAMP_ID, id);
        return intent;
    }


    private void setThemeSecondaryColor(final int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(color);
        }
    }

    private void setThemePrimaryColor(final int color) {
        mIvChampIcon.setBorderColor(color);
        mChampionPassiveView.setIconBorderColor(color);
        mChampionSpellView1.setIconBorderColor(color);
        mChampionSpellView2.setIconBorderColor(color);
        mChampionSpellView3.setIconBorderColor(color);
        mChampionSpellView4.setIconBorderColor(color);
    }

    private void setThemeColors(final int primaryColor) {
        this.mThemeColor = primaryColor;
        float[] hsv = new float[3];
        Color.colorToHSV(primaryColor, hsv);
        hsv[2] *= 0.75f; // value component
        final int darkerColor = Color.HSVToColor(hsv);
            setThemePrimaryColor(primaryColor);
            setThemeSecondaryColor(darkerColor);
    }

    @Override
    public void onSaveInstanceState(Bundle instanceState) {
        instanceState.putInt(KEY_THEME_COLOR, this.mThemeColor);
        super.onSaveInstanceState(instanceState);

    }

    @Override
    public void onRestoreInstanceState(Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        if (instanceState != null)
            this.setThemeColors(instanceState.getInt(KEY_THEME_COLOR, getResources().getColor(R.color.blue_700)));
        onScrollChanged(this.mListenerScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getIntent() != null && this.getIntent().getExtras() != null)
            this.mChampionId = this.getIntent().getExtras().getInt(EXTRA_CHAMP_ID, 32);
        setContentView(R.layout.fragment_champion_detail);
        this.mThemeColor = getResources().getColor(R.color.blue_700);
        this.mLoadingContainer = this.findViewById(R.id.loadingContainer);
        this.mTxtTitle = (TextView) this.findViewById(R.id.title);
        this.mTxtSubTitle = (TextView) this.findViewById(R.id.subTitle);
        this.mIvChampIcon = (CircularImageView) this.findViewById(R.id.ivChampionIcon);
        this.mTxtSubTitle = (TextView) this.findViewById(R.id.subTitle);
        this.mTxtDescription = (TextView) this.findViewById(R.id.txtDescription);
        this.mChampionSpellView1 = (ChampionSpellView) this.findViewById(R.id.championSpellView1);
        this.mChampionSpellView2 = (ChampionSpellView) this.findViewById(R.id.championSpellView2);
        this.mChampionSpellView3 = (ChampionSpellView) this.findViewById(R.id.championSpellView3);
        this.mChampionSpellView4 = (ChampionSpellView) this.findViewById(R.id.championSpellView4);
        this.mChampionPassiveView = (ChampionPassiveView) this.findViewById(R.id.championPassiveView);
        this.mListenerScrollView = (ObservableScrollView) this.findViewById(R.id.scrollView);
        this.mListenerScrollView.setScrollViewCallbacks(this);
        this.mIvHeaderProgressBar = (ProgressBar) this.findViewById(R.id.progressBar1);
        this.mIvHeader = (ImageView) this.findViewById(R.id.ivHeader);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        //Title and subtitle
        mToolbar.setTitle("MY toolbar");
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, this.mThemeColor));
//Navigation Icon
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("outa", "outa outa outa outa ");
                Toast.makeText(ChampionDetailsActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(mToolbar);
    }


    @Override
    public void onResume() {
        super.onResume();
        this.setLoading(true);
        this.setThemeColors(getResources().getColor(R.color.light_blue_700));
        ChampData data = new ChampData();
        data.setSpells(true);
        data.setSkins(true);
        data.setLore(true);
        data.setInfo(true);
        data.setPassive(true);
        data.setImage(true);
        Util.getLeagueApi().getStaticEndpoint(Region.EUW).getChampion(this.mChampionId, Locale.GERMAN, "5.1.2", true, data, CHAMPION_CALLBACK);
    }

    private void setLoading(final boolean loading) {
        this.mLoadingContainer.setVisibility(loading ? View.VISIBLE : View.GONE);
        this.mListenerScrollView.setVisibility(loading ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_champion_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Toast.makeText(this, "FAVORITE", Toast.LENGTH_LONG).show();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateViews() {
        if (this.mChampion != null) {
            this.mTxtTitle.setText(this.mChampion.getName());
            this.mTxtSubTitle.setText(this.mChampion.getTitle());
            Util.getPicasso()
                    .load("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + this.mChampion.getKey() + "_0.jpg")
                    .placeholder(android.R.drawable.ic_menu_rotate)
                    .error(android.R.drawable.ic_delete)
                    .noFade()
                    .into(IvHeaderTarget);
            Util.getPicasso()
                    .load(this.mChampion.getImageUri())
                    .placeholder(android.R.drawable.ic_menu_rotate)
                    .error(android.R.drawable.ic_delete)
                    .into(mIvChampIcon);
            this.mToolbar.setTitle(this.mChampion.getName());
            this.mToolbar.setSubtitle(this.mChampion.getTitle());
            if (this.mChampion.getSpells() != null) {
                this.mChampionSpellView1.setChampionSpell(this.mChampion.getSpells().get(0));
                this.mChampionSpellView2.setChampionSpell(this.mChampion.getSpells().get(1));
                this.mChampionSpellView3.setChampionSpell(this.mChampion.getSpells().get(2));
                this.mChampionSpellView4.setChampionSpell(this.mChampion.getSpells().get(3));
            }
            if (this.mChampion.getPassive() != null) {
                this.mChampionPassiveView.setChampionPassive(this.mChampion.getPassive());
            }
            if (this.mChampion.getSkins() != null) {
                this.mSkinListAdapter.setKey(this.mChampion.getKey());
                this.mSkinListAdapter.setSkins(this.mChampion.getSkins());
            }
            if (this.mChampion.getLore() != null) {

                this.mTxtDescription.setText(android.text.Html.fromHtml(this.mChampion.getLore()));
            }
        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        float alpha = 1 - (float) Math.max(0, mIvHeader.getHeight() - scrollY) / mIvHeader.getHeight();
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, this.mThemeColor));
        mIvHeader.setTranslationY(scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }


}
