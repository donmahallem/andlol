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
import android.widget.ScrollView;

import java.lang.ref.WeakReference;

/**
 * Created by Don on 16.10.2014.
 */
public class StickyHeadScrollView extends ScrollView {
    private WeakReference<OnScrollListener> mOnScrollListener;

    public StickyHeadScrollView(Context context) {
        super(context);
    }

    public StickyHeadScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyHeadScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void removeOnScrollListener() {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.clear();
            this.mOnScrollListener = null;
        }
    }

    public void removeOnScrollListener(OnScrollListener onScrollListener) {
        if (this.mOnScrollListener != null && this.mOnScrollListener.get() == onScrollListener) {
            this.removeOnScrollListener();
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        if (onScrollListener != null)
            this.mOnScrollListener = new WeakReference<OnScrollListener>(onScrollListener);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.mOnScrollListener != null)
            this.mOnScrollListener.get().onScrollViewScrolled(l, t, oldl, oldt);
    }

    public static interface OnScrollListener {
        public void onScrollViewScrolled(int l, int t, int oldl, int oldt);
    }
}
