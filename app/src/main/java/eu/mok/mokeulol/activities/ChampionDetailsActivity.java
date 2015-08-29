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
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class ChampionDetailsActivity extends LeagueActivity {
    private final static String EXTRA_CHAMP_ID = "champid", KEY_THEME_COLOR = "keyThemeColor";
    private TextView mTxtTitle, mTxtSubTitle, mTxtDescription, mTxtLore;
    private CircularImageView mIvChampIcon;
    private ChampionSpellView mChampionSpellView1, mChampionSpellView2, mChampionSpellView3, mChampionSpellView4;
    private SkinListAdapter mSkinListAdapter = new SkinListAdapter();
    private ChampionPassiveView mChampionPassiveView;
    private Champion mChampion;
    private ImageView mIvHeader;
    private Toolbar mToolbar;
    private int mChampionId = 32;
    private ProgressBar mIvHeaderProgressBar;
    private int mThemeColor = -1;
    private Target IvHeaderTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mIvHeader.setVisibility(View.VISIBLE);
            //mIvHeaderProgressBar.setVisibility(View.GONE);
            mIvHeader.setImageDrawable(new BitmapDrawable(bitmap));
            ValueAnimator imageFade = ValueAnimator.ofObject(new IntEvaluator(), 0, 255);
            imageFade.setDuration(250);
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
            //mIvHeaderProgressBar.setVisibility(View.VISIBLE);
        }
    };
    private TextView mTxtChampionTitle;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getIntent() != null && this.getIntent().getExtras() != null)
            this.mChampionId = this.getIntent().getExtras().getInt(EXTRA_CHAMP_ID, 32);
        setContentView(R.layout.fragment_champion_detail);
        this.mThemeColor = getResources().getColor(R.color.blue_700);
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
        this.mIvHeaderProgressBar = (ProgressBar) this.findViewById(R.id.progressBar1);
        this.mIvHeader = (ImageView) this.findViewById(R.id.ivHeader);
        this.mTxtChampionTitle = (TextView) this.findViewById(R.id.txtChampionTitle);
        this.mCollapsingToolbarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.collapsingToolbar);
        this.mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(mToolbar);
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
        Util.getLeagueApi().getStaticEndpoint().getChampion(Region.EUW, this.mChampionId, Locale.GERMAN, "5.16.1", true, data, CHAMPION_CALLBACK);
    }

    private void setLoading(final boolean loading) {
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
            this.mCollapsingToolbarLayout.setTitle(this.mChampion.getName());
            this.mTxtChampionTitle.setText(this.mChampion.getTitle());
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


}
