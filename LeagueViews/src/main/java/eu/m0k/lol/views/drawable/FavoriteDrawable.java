/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.views.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class FavoriteDrawable extends Drawable {
    private final Paint mPaint = new Paint();

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
        this.invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
