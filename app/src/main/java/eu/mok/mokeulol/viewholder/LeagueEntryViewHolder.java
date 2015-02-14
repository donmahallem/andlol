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

import eu.m0k.lol.api.model.LeagueEntry;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.view.SummonerIconImageView;

public class LeagueEntryViewHolder extends LayoutViewHolder {

    private SummonerIconImageView mIvSummonerIcon;
    private TextView mTxtSummonerName, mTxtLeaguePoints;
    private CardView mCardView;
    private LeagueEntry mLeagueEntry;

    public LeagueEntryViewHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.vh_league_summoner);
        this.mIvSummonerIcon = (SummonerIconImageView) this.itemView.findViewById(R.id.ivSummonerIcon);
        this.mTxtSummonerName = (TextView) this.itemView.findViewById(R.id.txtTitle);
        this.mTxtLeaguePoints = (TextView) this.itemView.findViewById(R.id.txtLeaguePoints);
        this.mCardView = (CardView) this.itemView;
    }

    public void setLeagueEntry(LeagueEntry leagueEntry) {
        this.mLeagueEntry = leagueEntry;
        if (this.mLeagueEntry != null) {
            this.mTxtSummonerName.setText(this.mLeagueEntry.getPlayerOrTeamName());
            this.mTxtLeaguePoints.setText("" + this.mLeagueEntry.getLeaguePoints());
            //this.mIvSummonerIcon.setSummonerIcon(this.mLeagueEntry.get);
        }
    }
}
