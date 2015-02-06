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
import android.graphics.drawable.RotateDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import eu.m0k.lol.views.drawable.FavoriteDrawable;

public class FavoriteButton extends ImageButton {
    private Drawable mDrawable;
    private FavoriteDrawable aaaa;

    private RotateDrawable RotateIcon = new RotateDrawable() {
    };
    public FavoriteButton(Context context) {
        super(context);
        setup(context);
    }

    public FavoriteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public FavoriteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    private void setup(Context context) {
        aaaa = new FavoriteDrawable(context.getResources().getDrawable(R.drawable.ic_favorite));
        super.setImageDrawable(aaaa);
        //super.setImageResource(R.drawable.btn_favorite_drawable);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                aaaa.addRotation(10);
            }
        });
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
