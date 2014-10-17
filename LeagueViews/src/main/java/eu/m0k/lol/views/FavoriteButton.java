/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;

import eu.m0k.lol.views.drawable.FavoriteDrawable;

public class FavoriteButton extends ImageButton {
    private FavoriteDrawable mFavoriteDrawable = new FavoriteDrawable();

    public FavoriteButton(Context context) {
        super(context);
        super.setImageDrawable(this.mFavoriteDrawable);
    }

    public FavoriteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setImageDrawable(this.mFavoriteDrawable);
    }

    public FavoriteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setImageDrawable(this.mFavoriteDrawable);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
    }

    @Override
    public void setImageResource(int resource) {
    }

    @Override
    public void setImageURI(Uri uri) {
    }
}
