/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import static eu.mok.mokeulol.view.ListenerScrollView.OnScrollListener;

public class StickyHeadContainerView extends FrameLayout implements OnScrollListener {
    private ListenerScrollView mListenerScrollView;
    private View mHead, mGhost;
    private int mOffsetTop = 0;

    private ViewTreeObserver.OnGlobalLayoutListener GlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            alignStickyHead();
        }
    };

    public StickyHeadContainerView(Context context) {
        super(context);
    }

    public StickyHeadContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyHeadContainerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.getChildCount() != 2)
            throw new RuntimeException("StickyHeadContainerView should contain 2 childs");
        try {
            this.mListenerScrollView = (ListenerScrollView) this.getChildAt(0);
        } catch (ClassCastException exception) {
            throw new RuntimeException("First child item should be StickyHeadContainerView", exception);
        }
        this.mListenerScrollView.addOnScrollListener(this);
        this.mHead = this.getChildAt(1);
        // this.mGhost = this.findViewById(R.id.test);
        //updateGhost();
        alignStickyHead();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.getViewTreeObserver().removeOnGlobalLayoutListener(GlobalLayoutListener);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getViewTreeObserver().addOnGlobalLayoutListener(GlobalLayoutListener);
    }

    private void updateGhost() {
        final int height = this.mHead.getMeasuredHeight();
        this.mGhost.getLayoutParams().height = height;
    }

    private void alignStickyHead() {
        int[] locationGhost = new int[2];
        int[] locationScrollView = new int[2];
        this.getLocationOnScreen(locationScrollView);
        this.mGhost.getLocationOnScreen(locationGhost);
        final int ghostY = locationGhost[1] - locationScrollView[1];
        ((MarginLayoutParams) this.mHead.getLayoutParams()).setMargins(0, Math.max(ghostY, this.mOffsetTop), 0, 0);
        this.mHead.requestLayout();
    }

    @Override
    public void onScrollViewScrolled(int l, int t, int oldl, int oldt) {
        alignStickyHead();
    }

    public void setTopOffset(int height) {
        this.mOffsetTop = Math.max(0, height);
    }
}
