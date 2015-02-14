/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import eu.m0k.lol.api.model.LeagueEntryMap;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.m0k.lol.api.model.SummonerIds;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.helper.picasso.PaletteTransformation;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SummonerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Summoner mSummoner;
    private CircularImageView mImageView;
    private TextView mTxtName, mTxtLevel;
    private CardView mCardView;
    private Callback<LeagueEntryMap> LEAGUE_CALLBACK = new Callback<LeagueEntryMap>() {
        @Override
        public void success(LeagueEntryMap leagueEntryMap, Response response) {
            Log.d("success", "yeah");
            if (leagueEntryMap.get(mSummoner.getId()) != null) {
                if (leagueEntryMap.get(mSummoner.getId()).getRankedSolo() != null) {
                    switch (leagueEntryMap.get(mSummoner.getId()).getRankedSolo().getTier()) {
                        case BRONZE:
                            mImageView.setBorderColor(itemView.getContext().getResources().getColor(R.color.league_bronze));
                            break;
                        case SILVER:
                            mImageView.setBorderColor(itemView.getContext().getResources().getColor(R.color.league_silver));
                            break;
                        case GOLD:
                            mImageView.setBorderColor(itemView.getContext().getResources().getColor(R.color.league_gold));
                            break;
                        case PLATINUM:
                            mImageView.setBorderColor(itemView.getContext().getResources().getColor(R.color.league_platinum));
                            break;
                    }
                }
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.d("failue", error.getLocalizedMessage());

        }
    };
    private OnSummonerClickListener mOnSummonerClickListener;

    public SummonerViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_summoner, viewGroup, false));
        this.mImageView = (CircularImageView) this.itemView.findViewById(R.id.ivChampionIcon);
        this.mTxtName = (TextView) this.itemView.findViewById(R.id.txtName);
        this.mTxtLevel = (TextView) this.itemView.findViewById(R.id.txtLevel);
        this.mCardView = (CardView) this.itemView;
        this.itemView.setClickable(true);
        this.itemView.setOnClickListener(this);
    }

    public void setSummoner(Summoner summoner) {
        this.mSummoner = summoner;
        if (this.mSummoner != null) {
            Util.getPicasso()
                    .load(this.mSummoner.getProfileIcon())
                    .transform(PaletteTransformation.instance())
                    .into(mImageView, new com.squareup.picasso.Callback.EmptyCallback() {
                        @Override
                        public void onSuccess() {
                            Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
                            Palette palette = PaletteTransformation.getPalette(bitmap);
                            mCardView.setCardBackgroundColor(palette.getVibrantColor(R.color.light_blue_500));
                        }
                    });
            this.mTxtName.setText(this.mSummoner.getName());
            this.mTxtLevel.setText("" + this.mSummoner.getSummonerLevel());
            Util.getLeagueApi().getLeagueEndpoint(Region.EUW).getLeagueEntryForSummoner(SummonerIds.create(this.mSummoner.getId()), LEAGUE_CALLBACK);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == this.itemView) {
            if (this.mOnSummonerClickListener != null)
                this.mOnSummonerClickListener.onSummonerClicked(this.mSummoner);
        }
    }

    public void setOnSummonerClickListener(final OnSummonerClickListener onSummonerClickListener) {
        this.mOnSummonerClickListener = onSummonerClickListener;
    }

    public static interface OnSummonerClickListener {
        public void onSummonerClicked(Summoner summoner);
    }
}
