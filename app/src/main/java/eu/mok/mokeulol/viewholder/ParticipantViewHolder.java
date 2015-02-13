/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.viewholder;

import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.TextView;

import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Participant;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.view.ChampionIconImageView;
import eu.mok.mokeulol.view.SummonerIconImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ParticipantViewHolder extends LayoutViewHolder {
    private Participant mParticipant;

    private SummonerIconImageView mIvSummonerIcon;
    private ChampionIconImageView mIvChampionIcon;
    private TextView mTxtSummonerName, mTxtChampionName;
    private CardView mCardView;
    private Callback<Champion> CHAMPION_CALLBACK = new Callback<Champion>() {
        @Override
        public void success(Champion champion, Response response) {
            mIvChampionIcon.setChampion(champion);
            mTxtChampionName.setText(champion.getName());
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    public ParticipantViewHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.vh_participant);
        this.mIvSummonerIcon = (SummonerIconImageView) this.itemView.findViewById(R.id.ivSummonerIcon);
        this.mIvChampionIcon = (ChampionIconImageView) this.itemView.findViewById(R.id.ivChampionIcon);
        this.mTxtSummonerName = (TextView) this.itemView.findViewById(R.id.txtTitle);
        this.mTxtChampionName = (TextView) this.itemView.findViewById(R.id.txtChampionName);
        this.mCardView = (CardView) this.itemView;
    }

    public void setParticipant(final Participant participant) {
        this.mParticipant = participant;
        if (this.mParticipant != null) {
            this.mTxtSummonerName.setText(this.mParticipant.getSummonerName());
            this.mIvSummonerIcon.setSummonerIcon(this.mParticipant.getProfileIconId());
            Util.getLeagueApi().getStaticEndpoint(Region.EUW).getChampion(this.mParticipant.getChampionId(), Locale.GERMAN, CHAMPION_CALLBACK);
            if (this.mParticipant.getTeamId() == 100) {
                this.mCardView.setCardBackgroundColor(this.itemView.getContext().getResources().getColor(R.color.blue_500));
            } else {
                this.mCardView.setCardBackgroundColor(this.itemView.getContext().getResources().getColor(R.color.red_500));
            }
        }
    }
}
