/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import eu.m0k.lol.api.model.Summoner;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

public class SummonerViewHolder extends RecyclerView.ViewHolder {
    private Summoner mSummoner;
    private CircularImageView mImageView;
    private TextView mTxtName, mTxtLevel;

    public SummonerViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_summoner, viewGroup, false));
        this.mImageView = (CircularImageView) this.itemView.findViewById(R.id.ivChampionIcon);
        this.mTxtName = (TextView) this.itemView.findViewById(R.id.txtName);
        this.mTxtLevel = (TextView) this.itemView.findViewById(R.id.txtLevel);
    }

    public void setSummoner(Summoner summoner) {
        this.mSummoner = summoner;
        if (this.mSummoner != null) {
            Util.getPicasso().load(this.mSummoner.getProfileIcon()).into(mImageView);
            this.mTxtName.setText(this.mSummoner.getName());
            this.mTxtLevel.setText("" + this.mSummoner.getSummonerLevel());
        }
    }
}
