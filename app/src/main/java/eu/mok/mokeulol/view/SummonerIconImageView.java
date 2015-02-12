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

import eu.m0k.lol.api.picasso.Constants;
import eu.mok.mokeulol.Util;

public class SummonerIconImageView extends ImageView {

    public SummonerIconImageView(Context context) {
        super(context);
    }

    public SummonerIconImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SummonerIconImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SummonerIconImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSummonerIcon(final long id) {
        Util.getPicasso().load(Constants.PATH_IMG_PROFILE_ICON + id).into(this);
    }
}
