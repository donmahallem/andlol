/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import eu.m0k.lol.api.model.Champion;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.SkinListAdapter;
import eu.mok.mokeulol.view.ChampionPassiveView;
import eu.mok.mokeulol.view.ChampionSpellView;
import timber.log.Timber;

public class ChampionDetailsFragment extends LeagueFragment {
    private final static String ARGS_CHAMP_ID = "champid";
    private TextView mTxtTitle, mTxtSubTitle, mTxtDescription, mTxtLore;
    private CircularImageView mIvChampIcon;
    private ChampionSpellView mChampionSpellView1, mChampionSpellView2, mChampionSpellView3, mChampionSpellView4;
    private SkinListAdapter mSkinListAdapter = new SkinListAdapter();
    private ChampionPassiveView mChampionPassiveView;
    private Champion mChampion;
    private ImageView mIvHeader;
    private Toolbar mToolbar;
    private Palette.PaletteAsyncListener IvHeaderAsyncListener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            Log.d("ChampionDetailsFragment", "onGenerated()");
            final int primaryColor = palette.getVibrantColor(R.color.light_blue_700);
            int darkerColor = getResources().getColor(R.color.light_blue_900);
            if (isAdded()) {
                float[] hsv = new float[3];
                Color.colorToHSV(primaryColor, hsv);
                hsv[2] = Math.min(1f, Math.max(0f, hsv[2] * 0.9f)); // value component
                darkerColor = Color.HSVToColor(hsv);
                /**
                 * If Lollipop set StatusBarColor call
                 */
                if (Build.VERSION.SDK_INT >= 21) {
                    getActivity().getWindow().setStatusBarColor(darkerColor);
                }
                getActivity().getWindow().getDecorView().setBackgroundColor(darkerColor);
            }
            mToolbar.setBackgroundColor(primaryColor);
            mIvChampIcon.setBorderColor(primaryColor);
            Log.d("IvHeaderAsync", "C _ " + colorToString(primaryColor) + " - " + colorToString(darkerColor));
        }
    };
    private Target IvHeaderTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mIvHeader.setImageDrawable(new BitmapDrawable(bitmap));
            Palette.generateAsync(bitmap, IvHeaderAsyncListener);
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
    private int mChampionId;

    public ChampionDetailsFragment() {
        super();
    }

    private static String colorToString(int color) {
        return "(" + Color.red(color) + " , " + Color.green(color) + " , " + Color.blue(color) + ")";
    }

    public static ChampionDetailsFragment getInstance(final int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_CHAMP_ID, id);
        ChampionDetailsFragment fragment = new ChampionDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate() {
        this.onCreate();
        this.mChampionId = getArguments().getInt(ARGS_CHAMP_ID, 32);
        Timber.d("onCreate() - champId: " + this.mChampionId);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_champion_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mTxtTitle = (TextView) view.findViewById(R.id.title);
        this.mTxtSubTitle = (TextView) view.findViewById(R.id.subTitle);
        this.mIvChampIcon = (CircularImageView) view.findViewById(R.id.ivChampionIcon);
        this.mTxtSubTitle = (TextView) view.findViewById(R.id.subTitle);
        this.mTxtDescription = (TextView) view.findViewById(R.id.txtDescription);
        this.mChampionSpellView1 = (ChampionSpellView) view.findViewById(R.id.championSpellView1);
        this.mChampionSpellView2 = (ChampionSpellView) view.findViewById(R.id.championSpellView2);
        this.mChampionSpellView3 = (ChampionSpellView) view.findViewById(R.id.championSpellView3);
        this.mChampionSpellView4 = (ChampionSpellView) view.findViewById(R.id.championSpellView4);
        this.mChampionPassiveView = (ChampionPassiveView) view.findViewById(R.id.championPassiveView);
        this.mIvHeader = (ImageView) view.findViewById(R.id.ivHeader);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
//Title and subtitle
        mToolbar.setTitle("MY toolbar");
        mToolbar.setBackgroundColor(getResources().getColor(R.color.light_blue_600));
//Menu
        mToolbar.inflateMenu(R.menu.toolbar_menu);

        ((ActionBarActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    private void updateViews() {
        if (this.mChampion != null) {
            this.mTxtTitle.setText(this.mChampion.getName());
            this.mTxtSubTitle.setText(this.mChampion.getTitle());
            Util.getPicasso()
                    .load("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + this.mChampion.getKey() + "_0.jpg")
                    .placeholder(android.R.drawable.ic_menu_rotate)
                    .error(android.R.drawable.ic_delete)
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

}
