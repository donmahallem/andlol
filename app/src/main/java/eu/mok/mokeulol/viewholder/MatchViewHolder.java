/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eu.m0k.lol.api.model.MatchSummary;
import eu.m0k.lol.api.model.Participant;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.view.ChampionIconImageView;
import eu.mok.mokeulol.view.SummonerSpellImageView;

public class MatchViewHolder extends LayoutViewHolder implements View.OnClickListener {
    private SummonerSpellImageView mIvSummonerSpell1, mIvSummonerSpell2;
    private ChampionIconImageView mIvChampionIcon;
    private TextView mTxtTitle, mTxtDeaths, mTxtKills, mTxtAssists, mTxtMatchType;
    private MatchSummary mMatch;
    private OnMatchSelectListener mOnMatchSelectListener;

    public MatchViewHolder(ViewGroup parent) {
        super(parent, R.layout.vh_match);
        this.mTxtTitle = (TextView) this.itemView.findViewById(R.id.txtTitle);
        this.mTxtKills = (TextView) this.itemView.findViewById(R.id.txtKills);
        this.mTxtAssists = (TextView) this.itemView.findViewById(R.id.txtAssists);
        this.mTxtDeaths = (TextView) this.itemView.findViewById(R.id.txtDeaths);
        this.mTxtMatchType = (TextView) this.itemView.findViewById(R.id.txtMatchType);
        this.mIvChampionIcon = (ChampionIconImageView) this.itemView.findViewById(R.id.ivChampionIcon);
        this.mIvSummonerSpell1 = (SummonerSpellImageView) this.itemView.findViewById(R.id.ivSummonerSpell1);
        this.mIvSummonerSpell2 = (SummonerSpellImageView) this.itemView.findViewById(R.id.ivSummonerSpell2);
        this.itemView.setOnClickListener(this);
    }


    public void setMatch(MatchSummary match) {
        if (match != null) {
            this.mMatch = match;
            final Participant participant = match.getParticipants().get(0);
            this.mIvChampionIcon.loadChampionById(Region.EUW, participant.getChampionId());
            this.mIvSummonerSpell1.loadSummonerSpellById(Region.EUW, participant.getSpell1Id());
            this.mIvSummonerSpell2.loadSummonerSpellById(Region.EUW, participant.getSpell2Id());
            if (match.getParticipants().get(0).getStats().isWinner()) {
                this.mTxtTitle.setTextColor(itemView.getResources().getColor(R.color.green_600));
                this.mTxtTitle.setText(R.string.victory);
            } else {
                this.mTxtTitle.setTextColor(itemView.getResources().getColor(R.color.red_600));
                this.mTxtTitle.setText(R.string.defeat);
            }
            this.mTxtKills.setText("" + participant.getStats().getKills());
            this.mTxtAssists.setText("" + participant.getStats().getAssists());
            this.mTxtDeaths.setText("" + participant.getStats().getDeaths());
            this.mTxtMatchType.setText(this.mMatch.getMatchType().name() + " - " + this.mMatch.getMatchMode().name() + " - " + this.mMatch.getQueueType().name());
        }
    }

    public void reset() {
        this.mIvChampionIcon.setImageResource(R.drawable.ic_favorite);
        this.mIvSummonerSpell1.setImageResource(R.drawable.ic_favorite);
        this.mIvSummonerSpell2.setImageResource(R.drawable.ic_favorite);
    }

    @Override
    public void onClick(View v) {
        if (v == this.itemView && this.mMatch != null)
            this.mOnMatchSelectListener.onMatchSelected(this.mMatch);
    }

    public void setOnMatchSelectListener(final OnMatchSelectListener onMatchSelectListener) {
        this.mOnMatchSelectListener = onMatchSelectListener;
    }

    public static interface OnMatchSelectListener {
        public void onMatchSelected(MatchSummary match);
    }
}
