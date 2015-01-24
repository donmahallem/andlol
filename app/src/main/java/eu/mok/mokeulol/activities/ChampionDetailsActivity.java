/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.SkinListAdapter;
import eu.mok.mokeulol.view.ChampionPassiveView;
import eu.mok.mokeulol.view.ChampionSpellView;
import eu.mok.mokeulol.view.ListenerScrollView;

public class ChampionDetailsActivity extends LeagueActivity {
    private final static String EXTRA_CHAMP_ID = "champid";
    private TextView mTxtTitle, mTxtSubTitle, mTxtDescription, mTxtLore;
    private CircularImageView mIvChampIcon;
    private ChampionSpellView mChampionSpellView1, mChampionSpellView2, mChampionSpellView3, mChampionSpellView4;
    private SkinListAdapter mSkinListAdapter = new SkinListAdapter();
    private ChampionPassiveView mChampionPassiveView;
    private Champion mChampion;
    private ImageView mIvHeader;
    private Toolbar mToolbar;
    private ListenerScrollView mListenerScrollView;
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
            mIvHeader.setImageDrawable(new BitmapDrawable(bitmap));
            Palette.generateAsync(bitmap, IvHeaderAsyncListener);
            ValueAnimator imageFade = ValueAnimator.ofObject(new IntEvaluator(), 0, 255);
            imageFade.setDuration(1000);
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
        }
    };
    private ValueAnimator.AnimatorUpdateListener SecondaryColorAnimator = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            setThemeSecondaryColor((Integer) animation.getAnimatedValue());
        }
    };
    private ToolbarBackground mToolbarBackground = new ToolbarBackground();
    ;
    private ValueAnimator.AnimatorUpdateListener PrimaryColorAnimator = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            setThemePrimaryColor((Integer) animation.getAnimatedValue());
        }
    };
    private ListenerScrollView.OnScrollListener ActionBarFadeListener = new ListenerScrollView.OnScrollListener() {
        @Override
        public void onScrollViewScrolled(int l, int t, int oldl, int oldt) {
            final int h = mIvHeader.getHeight() / 2;
            final int ratio = Math.min(255, Math.max(0, 255 * t / h));
            mToolbarBackground.setAlpha(ratio);
        }
    };

    public static Intent createIntent(Activity activity, int id) {
        Intent intent = new Intent(activity, ChampionDetailsActivity.class);
        intent.putExtra(EXTRA_CHAMP_ID, id);
        return intent;
    }

    private void setThemeColors(final int primaryColor) {
        this.setThemeColors(primaryColor, false);
    }

    private void setThemeSecondaryColor(final int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(color);
        }
        findViewById(R.id.content1).setBackgroundColor(color);
    }

    private void setThemePrimaryColor(final int color) {
        mToolbarBackground.setRgb(color);
        mIvChampIcon.setBorderColor(color);
        mChampionPassiveView.setIconBorderColor(color);
        mChampionSpellView1.setIconBorderColor(color);
        mChampionSpellView2.setIconBorderColor(color);
        mChampionSpellView3.setIconBorderColor(color);
        mChampionSpellView4.setIconBorderColor(color);
    }

    private void setThemeColors(final int primaryColor, boolean instant) {
        float[] hsv = new float[3];
        Color.colorToHSV(primaryColor, hsv);
        hsv[2] *= 0.75f; // value component
        final int darkerColor = Color.HSVToColor(hsv);
        if (instant) {
            setThemePrimaryColor(primaryColor);
            setThemeSecondaryColor(darkerColor);
            return;
        }
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), getResources().getColor(R.color.light_blue_700), primaryColor);
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(PrimaryColorAnimator);
        colorAnimation.start();
        ValueAnimator colorAnimationSecondary = ValueAnimator.ofObject(new ArgbEvaluator(), getResources().getColor(R.color.light_blue_900), darkerColor);
        colorAnimationSecondary.setDuration(1000);
        colorAnimationSecondary.addUpdateListener(SecondaryColorAnimator);
        colorAnimationSecondary.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_champion_detail);
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
        this.mListenerScrollView = (ListenerScrollView) this.findViewById(R.id.scrollView);
        if (this.mListenerScrollView != null) {
            this.mListenerScrollView.addOnScrollListener(ActionBarFadeListener);
        }
        Task task = new Task();
        int id = 32;
        if (this.getIntent() != null && this.getIntent().getExtras() != null)
            id = this.getIntent().getExtras().getInt(EXTRA_CHAMP_ID, 32);
        task.execute(id);
        this.mIvHeader = (ImageView) this.findViewById(R.id.ivHeader);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        //Title and subtitle
        mToolbar.setTitle("MY toolbar");
        mToolbarBackground.setColor(getResources().getColor(R.color.light_blue_600));
        //mToolbarBackground.setAlpha(0);
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
        mToolbar.setBackgroundDrawable(mToolbarBackground);
        this.setThemeColors(getResources().getColor(R.color.light_blue_700), true);
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

    private class ToolbarBackground extends ColorDrawable {
        @Override
        public void setAlpha(int alpha) {
            super.setAlpha(alpha);
            //setRgb(Color.argb(alpha,Color.red(getColor()),Color.green(getColor()),Color.blue(getColor())));
        }

        public int getSupportAlpha() {
            return Color.alpha(this.getColor());
        }

        public void setRgb(int color) {
            this.setRgb(Color.red(color), Color.green(color), Color.blue(color));
        }

        public void setRgb(int red, int green, int blue) {
            setColor(Color.argb(getSupportAlpha(), red, green, blue));
        }
    }

    private class Task extends AsyncTask<Integer, Void, LeagueResponse<Champion>> {

        @Override
        protected LeagueResponse<Champion> doInBackground(Integer... params) {
            ChampData data = new ChampData();
            data.setSpells(true);
            data.setSkins(true);
            data.setLore(true);
            data.setInfo(true);
            data.setPassive(true);
            data.setImage(true);
            LeagueResponse<Champion> champ = null;
            try {
                champ = Util.getLeagueApi().getChampion(params[0], Region.EUW, data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return champ;
        }

        @Override
        protected void onPostExecute(LeagueResponse<Champion> result) {
            if (result != null && result.getBody() != null) {
                mChampion = result.getBody();
                updateViews();
            } else {
                int msg = 0;
                if (result == null) {
                    msg = R.string.unknown_error;
                } else {
                    msg = R.string.network_error;
                }
                Toast.makeText(ChampionDetailsActivity.this, msg, Toast.LENGTH_LONG);
            }
        }
    }
}
