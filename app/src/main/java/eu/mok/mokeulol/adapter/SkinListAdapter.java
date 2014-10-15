/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import eu.m0k.lol.api.model.ChampionSkin;
import eu.m0k.lol.api.picasso.Constants;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

/**
 * Created by Don on 04.10.2014.
 */

public class SkinListAdapter extends BaseAdapter {
    private String mKey;
    private List<ChampionSkin> mSkins;

    public SkinListAdapter() {
        this("");
    }

    public SkinListAdapter(String championKey) {
        this(championKey, new ArrayList<ChampionSkin>());
    }

    public SkinListAdapter(String championKey, List<ChampionSkin> skins) {
        this.mKey = championKey;
        this.mSkins = skins;
    }

    @Override
    public int getCount() {
        return this.mSkins.size();
    }

    public void setKey(String key) {
        this.mKey = key;
        notifyDataSetChanged();
    }

    public void setSkins(List<ChampionSkin> skins) {
        this.mSkins.clear();
        this.mSkins.addAll(skins);
        notifyDataSetChanged();
    }

    @Override
    public ChampionSkin getItem(int position) {
        return this.mSkins.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view = null;
        if (convertView != null && convertView instanceof ImageView) {
            view = (ImageView) convertView;
        } else
            view = new ImageView(parent.getContext());
        view.setImageResource(R.drawable.league_circle_progress);
        view.setMinimumHeight(parent.getContext().getResources().getDimensionPixelSize(R.dimen.skin_placeholder));
        view.setMinimumWidth(parent.getContext().getResources().getDimensionPixelSize(R.dimen.skin_placeholder));
        Util.getPicasso()
                .load(Constants.PATH_IMG_CHAMPION_LOADING + this.mKey + "_" + position)
                .error(android.R.drawable.ic_delete)
                .into(view);
        return view;
    }
}