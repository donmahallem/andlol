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
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.SummonerIds;
import eu.m0k.lol.api.model.SummonerList;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.view.SummonerIconImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class LeagueEntryViewHolder extends LayoutViewHolder {

    private SummonerIconImageView mIvSummonerIcon;
    private TextView mTxtSummonerName, mTxtLeaguePoints;
    private CardView mCardView;
    private LeagueEntry mLeagueEntry;
    private Callback<SummonerList> CALLBACK = new Callback<SummonerList>() {
        @Override
        public void success(SummonerList summonerList, Response response) {
            final long id = Long.parseLong(mLeagueEntry.getPlayerOrTeamId());
            Timber.d("success - " + id + " - " + summonerList);
            if (summonerList.containsKey(id)) {
                Timber.d("contains - " + id);
                mIvSummonerIcon.setSummonerIcon(summonerList.get(id).getProfileIconId());
            }
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };


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
            Util.getLeagueApi()
                    .getSummonerEndpoint(Region.EUW)
                    .getSummoners(
                            SummonerIds.create(Long.parseLong(this.mLeagueEntry.getPlayerOrTeamId())), CALLBACK);
            //this.mIvSummonerIcon.setSummonerIcon(this.mLeagueEntry.get);
        }
    }
}
