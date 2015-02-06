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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Don on 16.10.2014.
 */
public class ListenerScrollView extends ScrollView {
    private List<OnScrollListener> mOnScrollListener = new ArrayList<OnScrollListener>();

    public ListenerScrollView(Context context) {
        super(context);
    }

    public ListenerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenerScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void removeOnScrollListener() {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.clear();
        }
    }

    public void removeOnScrollListener(OnScrollListener onScrollListener) {
        if (null != onScrollListener) {
            this.mOnScrollListener.remove(onScrollListener);
        }
    }

    @Deprecated
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.addOnScrollListener(onScrollListener);
    }

    public void addOnScrollListener(OnScrollListener onScrollListener) {
        if (onScrollListener != null)
            this.mOnScrollListener.add(onScrollListener);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (OnScrollListener listener : this.mOnScrollListener) {
            listener.onScrollViewScrolled(l, t, oldl, oldt);
        }
    }

    public static interface OnScrollListener {
        public void onScrollViewScrolled(int l, int t, int oldl, int oldt);
    }
}
