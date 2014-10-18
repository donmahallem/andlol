/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.ChampionList;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

public class RVChampionAdapter extends RecyclerView.Adapter<RVChampionAdapter.ChampionViewHolder> {

    private ChampionList mChampionList = new ChampionList();
    private List<OnChampSelectListener> mOnChampSelectListener = new ArrayList<OnChampSelectListener>();

    public void setChampionList(ChampionList championList) {
        if (championList != null) {
            this.mChampionList.clear();
            this.mChampionList.addAll(championList);
            this.notifyDataSetChanged();
        }
    }

    public void setOnChampSelectListener(OnChampSelectListener onChampSelectListener) {
        this.mOnChampSelectListener.add(onChampSelectListener);
    }

    @Override
    public ChampionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ChampionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_champion_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ChampionViewHolder championViewHolder, int i) {
        final Champion champion = this.mChampionList.get(i);
        championViewHolder.mTextView.setText(champion.getName());
        /**
         * Remove eventual loading of recycled image
         */
        Util.getPicasso().cancelRequest(championViewHolder.mImageView);
        Util.getPicasso()
                .load(champion.getImageUri())
                .placeholder(android.R.drawable.ic_menu_upload)
                .error(android.R.drawable.ic_delete)
                .into(championViewHolder.ChampionTarget);
    }

    @Override
    public int getItemCount() {
        return this.mChampionList.size();
    }

    public static interface OnChampSelectListener {
        public void onChampSelected(Champion champion);
    }

    public class ChampionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mImageView;
        public final TextView mTextView;
        private Target ChampionTarget = new Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mImageView.setImageDrawable(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                mImageView.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                mImageView.setImageDrawable(placeHolderDrawable);
            }
        };

        public ChampionViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            this.mImageView = (ImageView) itemView.findViewById(R.id.ivChampionIcon);
            this.mTextView = (TextView) itemView.findViewById(R.id.txtTitle);
        }

        @Override
        public void onClick(View v) {
            for (OnChampSelectListener champSelectListener : mOnChampSelectListener) {
                champSelectListener.onChampSelected(mChampionList.get(getPosition()));
            }
        }
    }
}