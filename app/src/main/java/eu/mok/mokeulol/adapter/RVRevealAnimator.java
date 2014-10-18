/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.adapter;

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class RVRevealAnimator extends RecyclerView.ItemAnimator {
    private ValueAnimator mValueAnimator = ValueAnimator.ofFloat(0f, 1f);

    @Override
    public void runPendingAnimations() {
        Log.d("RVRevealAnimator", "runPendingAnimations");
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        Log.d("RVRevealAnimator", "animateRemove");
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setRotation(45);
        Log.d("RVRevealAnimator", "animateAdd");
        return true;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        Log.d("RVRevealAnimator", "animateMove");
        return true;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        Log.d("RVRevealAnimator", "animateChange");
        return true;
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
