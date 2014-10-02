/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.m0k.lol.api.model.ChampionSpell;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

/**
 * Created by Don on 30.09.2014.
 */
public class ChampionSpellView extends LinearLayout {
    private ChampionSpell mChampionSpell;
    private ImageView mImageView;
    private TextView mTitle, mSubTitle;

    public ChampionSpellView(Context context) {
        this(context, null);
    }

    public ChampionSpellView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChampionSpellView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.listitem_champion, this);
        this.mImageView = (ImageView) this.findViewById(R.id.icon);
        this.mTitle = (TextView) this.findViewById(R.id.title);
        this.mSubTitle = (TextView) this.findViewById(R.id.subTitle);
    }

    public void setChampion(final ChampionSpell champion) {
        this.mChampionSpell = champion;
        this.update();
    }

    public void update() {
        if (this.mChampionSpell != null) {
            this.mTitle.setText(this.mChampionSpell.getName());
            this.mSubTitle.setText(this.mChampionSpell.getSanitizedDescription());
            Util.getPicasso().load(this.mChampionSpell.getImageUri()).placeholder(android.R.drawable.ic_menu_rotate).error(android.R.drawable.ic_delete).into(this.mImageView);
        }
    }
}
