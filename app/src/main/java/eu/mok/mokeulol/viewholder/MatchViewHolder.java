/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import eu.m0k.lol.api.model.Match;
import eu.m0k.lol.api.model.Participant;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.view.ChampionIconImageView;
import eu.mok.mokeulol.view.SummonerSpellImageView;

public class MatchViewHolder extends LayoutViewHolder {
    private SummonerSpellImageView mIvSummonerSpell1, mIvSummonerSpell2;
    private ChampionIconImageView mIvChampionIcon;
    private TextView mTxtTitle;

    public MatchViewHolder(ViewGroup parent) {
        super(parent, R.layout.vh_match);
        this.mTxtTitle = (TextView) this.itemView.findViewById(R.id.txtTitle);
        this.mIvChampionIcon = (ChampionIconImageView) this.itemView.findViewById(R.id.ivChampionIcon);
        this.mIvSummonerSpell1 = (SummonerSpellImageView) this.itemView.findViewById(R.id.ivSummonerSpell1);
        this.mIvSummonerSpell2 = (SummonerSpellImageView) this.itemView.findViewById(R.id.ivSummonerSpell2);
    }


    public void setMatch(Match match) {
        if (match != null) {
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
        }
    }

    public void reset() {
        this.mIvChampionIcon.setImageResource(R.drawable.ic_favorite);
        this.mIvSummonerSpell1.setImageResource(R.drawable.ic_favorite);
        this.mIvSummonerSpell2.setImageResource(R.drawable.ic_favorite);
    }
}
