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

import eu.m0k.lol.api.model.Participant;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.view.SummonerIconImageView;

public class ParticipantViewHolder extends LayoutViewHolder {
    private Participant mParticipant;

    private SummonerIconImageView mIvSummonerIcon;
    private TextView mTxtSummonerName;
    private CardView mCardView;

    public ParticipantViewHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.vh_participant);
        this.mIvSummonerIcon = (SummonerIconImageView) this.itemView.findViewById(R.id.ivChampionIcon);
        this.mTxtSummonerName = (TextView) this.itemView.findViewById(R.id.txtTitle);
        this.mCardView = (CardView) this.itemView;
    }

    public void setParticipant(final Participant participant) {
        this.mParticipant = participant;
        if (this.mParticipant != null) {
            this.mTxtSummonerName.setText(this.mParticipant.getSummonerName());
            this.mIvSummonerIcon.setSummonerIcon(this.mParticipant.getProfileIconId());
            if (this.mParticipant.getTeamId() == 100) {
                this.mCardView.setCardBackgroundColor(this.itemView.getContext().getResources().getColor(R.color.blue_500));
            } else {
                this.mCardView.setCardBackgroundColor(this.itemView.getContext().getResources().getColor(R.color.red_500));
            }
        }
    }
}
