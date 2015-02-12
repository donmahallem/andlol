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

import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChampionIconImageView extends ImageView {
    private Champion mChampion;
    private long mChampionId = -1;
    private Callback<Champion> SPELL_CALLBACK = new Callback<Champion>() {

        @Override
        public void success(Champion champion, Response response) {
            if (champion.getId() == mChampionId) {
                ChampionIconImageView.this.setSummonerSpell(champion);
            }
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    public ChampionIconImageView(Context context) {
        super(context);
    }

    public ChampionIconImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChampionIconImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ChampionIconImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void loadChampionById(final Region region, final int id) {
        this.mChampionId = id;
        Util.getLeagueApi().getStaticEndpoint(region).getChampion(id, Locale.ENGLISH_US, SPELL_CALLBACK);
    }

    public void setSummonerSpell(final Champion summonerSpell) {
        if (summonerSpell != null) {
            this.mChampion = summonerSpell;
            Util.getPicasso().cancelRequest(this);
            Util.getPicasso().load(this.mChampion.getImageUri())
                    .placeholder(R.drawable.ic_favorite)
                    .error(R.drawable.ic_star_grey600_24dp)
                    .into(this);
        }
    }

    public Champion getChampion() {
        return this.mChampion;
    }
}
