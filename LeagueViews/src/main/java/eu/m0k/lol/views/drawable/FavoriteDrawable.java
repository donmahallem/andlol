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

    public FavoriteDrawable() {
        this.mPaint.setARGB(128, 255, 0, 0);
        this.mPaint.setStrokeWidth(0.1f);
    }

    @Override
    public void draw(Canvas canvas) {
        final int size = Math.round(Math.min(canvas.getWidth(), canvas.getHeight()) * 0.8f);
        final int marginTop = canvas.getHeight() - size;
        final int marginLeft = canvas.getWidth() - size;
        canvas.scale(canvas.getWidth(), canvas.getHeight());
        canvas.drawLine(0.1f, 0.1f, 0.8f, 0.8f, this.mPaint);
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

    private class Line {
        public void draw(Canvas canvas) {
        }
    }
}
