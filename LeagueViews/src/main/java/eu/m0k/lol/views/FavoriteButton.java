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

public class FavoriteButton extends ImageButton {
    public FavoriteButton(Context context) {
        super(context);
    }

    public FavoriteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FavoriteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
