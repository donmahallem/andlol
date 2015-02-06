/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.views.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class FavoriteDrawable extends Drawable {
    private final Paint mPaint = new Paint();
    private final Bitmap mBitmap;
    private float mRotation = 0f;

    public FavoriteDrawable(Drawable drawable) {
        this.mBitmap = drawableToBitmap(drawable);
        this.mPaint.setARGB(255, 255, 0, 0);
        this.mPaint.setStrokeWidth(0.1f);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        final int size = Math.round(Math.min(canvas.getWidth(), canvas.getHeight()) * 0.8f);
        final int marginTop = canvas.getHeight() - size;
        final int marginLeft = canvas.getWidth() - size;
        Rect src = new Rect();
        Rect dst = new Rect();
        src.set(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
        dst.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.rotate(mRotation, canvas.getWidth() / 2f, canvas.getHeight() / 2f);
        canvas.drawBitmap(this.mBitmap, src, dst, mPaint);
    }

    public void setRotation(float rotation) {
        this.mRotation = rotation % 360;
        invalidateSelf();
    }

    public void addRotation(float rotation) {
        this.setRotation(this.mRotation + rotation);
    }

    @Override
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
        this.invalidateSelf();
        ColorFilter fi = new ColorFilter();
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
