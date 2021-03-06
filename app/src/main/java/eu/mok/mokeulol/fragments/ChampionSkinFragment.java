/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import eu.m0k.lol.api.picasso.Constants;
import eu.mok.mokeulol.Util;

public class ChampionSkinFragment extends LeagueFragment {
    private final static String ARGS_CHAMPION_KEY = "championKey", ARGS_SKIN_NUM = "skinNUm";
    private ImageView mImageView;

    public ChampionSkinFragment() {

    }

    public static ChampionSkinFragment createInstance(String championKey, int skinNum) {
        ChampionSkinFragment fragment = new ChampionSkinFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_SKIN_NUM, skinNum);
        bundle.putString(ARGS_CHAMPION_KEY, championKey);
        fragment.setArguments(bundle);
        return fragment;
    }

    private String getChampionKey() {
        if (this.getArguments() == null)
            return null;
        return this.getArguments().getString(ARGS_CHAMPION_KEY, null);
    }

    private int getChampionSkinNum() {
        if (this.getArguments() == null)
            return 0;
        return this.getArguments().getInt(ARGS_SKIN_NUM, 0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mImageView = new ImageView(container.getContext());
        return this.mImageView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final String key = this.getChampionKey();
        final int num = this.getChampionSkinNum();
        Util.getPicasso()
                .load(Constants.PATH_IMG_CHAMPION_SPLASH + key + "_" + num)
                .placeholder(android.R.drawable.ic_menu_rotate)
                .error(android.R.drawable.ic_delete)
                .into(mImageView);
    }
}
