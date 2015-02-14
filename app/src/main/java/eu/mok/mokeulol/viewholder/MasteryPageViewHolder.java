/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Masteries;
import eu.m0k.lol.api.model.MasteryPage;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MasteryPageViewHolder extends LayoutViewHolder {
    private TextView mTxtName, mTxtActive;
    private CardView mCardView;
    private MasteryPage mMasteryPage;
    private Callback<Masteries> MASTERIES_CALLBACK = new Callback<Masteries>() {
        @Override
        public void success(Masteries masteries, Response response) {

        }

        @Override
        public void failure(RetrofitError error) {

        }
    };
    public MasteryPageViewHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.vh_mastery_page);
        this.mTxtName = (TextView) this.itemView.findViewById(R.id.txtTitle);
        this.mCardView = (CardView) this.itemView;
        this.mTxtActive = (TextView) this.itemView.findViewById(R.id.txtActiveMastery);
    }

    public void setMasteryPage(final MasteryPage masteryPage) {
        if (masteryPage != null) {
            this.mMasteryPage = masteryPage;
            this.mTxtName.setText(masteryPage.getName());
            this.mTxtActive.setVisibility(masteryPage.isCurrent() ? View.VISIBLE : View.GONE);
            Util.getLeagueApi().getStaticEndpoint(Region.EUW).getMasteries(Locale.GERMAN, MASTERIES_CALLBACK);
        }
    }
}
