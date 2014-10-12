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

import eu.m0k.lol.api.model.Champion;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

/**
 * Created by Don on 29.09.2014.
 */
public class ChampionListItem extends LinearLayout {
    private Champion mChampion;
    private ImageView mImageView;
    private TextView mTitle, mSubTitle;

    public ChampionListItem(Context context) {
        this(context, null);
    }

    public ChampionListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChampionListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.listitem_champion, this);
        this.mImageView = (ImageView) this.findViewById(R.id.icon);
        this.mTitle = (TextView) this.findViewById(R.id.title);
        this.mSubTitle = (TextView) this.findViewById(R.id.subTitle);
    }

    public void setChampion(final Champion champion) {
        this.mChampion = champion;
        this.update();
    }

    public void update() {
        if (this.mChampion != null) {
            this.mTitle.setText(this.mChampion.getName());
            this.mSubTitle.setText(this.mChampion.getTitle());
            Util.getPicasso()
                    .load(this.mChampion.getImageUri())
                    .placeholder(android.R.drawable.ic_menu_upload)
                    .error(android.R.drawable.ic_delete)
                    .resize(100, 100)
                    .into(this.mImageView);
        }
    }
}
