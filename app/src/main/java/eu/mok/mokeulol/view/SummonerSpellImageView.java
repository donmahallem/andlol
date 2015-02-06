/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.SummonerSpell;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SummonerSpellImageView extends ImageView {
    private SummonerSpell mSummonerSpell;
    private int mSummonerSpellId = -1;
    private Callback<SummonerSpell> SPELL_CALLBACK = new Callback<SummonerSpell>() {

        @Override
        public void success(SummonerSpell summonerSpell, Response response) {
            if (summonerSpell.getId() == mSummonerSpellId) {
                SummonerSpellImageView.this.setSummonerSpell(summonerSpell);
            }
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    public SummonerSpellImageView(Context context) {
        super(context);
    }

    public SummonerSpellImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SummonerSpellImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SummonerSpellImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void loadSummonerSpellById(final Region region, final int id) {
        this.mSummonerSpellId = id;
        Util.getLeagueApi().getStaticEndpoint(region).getSummonerSpell(id, SPELL_CALLBACK);
    }

    public SummonerSpell getSummonerSpell() {
        return this.mSummonerSpell;
    }

    public void setSummonerSpell(final SummonerSpell summonerSpell) {
        if (summonerSpell != null) {
            this.mSummonerSpell = summonerSpell;
            Util.getPicasso().cancelRequest(this);
            Util.getPicasso().load(this.mSummonerSpell.getIconUri())
                    .placeholder(R.drawable.ic_favorite)
                    .error(R.drawable.ic_star_grey600_24dp)
                    .into(this);
        }
    }
}
