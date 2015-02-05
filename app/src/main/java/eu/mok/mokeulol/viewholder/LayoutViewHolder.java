/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class LayoutViewHolder extends RecyclerView.ViewHolder {
    public LayoutViewHolder(final ViewGroup viewGroup, final int layout) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false));
    }
}
