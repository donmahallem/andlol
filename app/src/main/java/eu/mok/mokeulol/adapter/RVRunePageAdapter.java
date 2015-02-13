/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import eu.m0k.lol.api.model.RunePages;
import eu.mok.mokeulol.viewholder.RunePageViewHolder;

public class RVRunePageAdapter extends RecyclerView.Adapter<RunePageViewHolder> {

    private RunePages mRunePages;

    @Override
    public RunePageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RunePageViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RunePageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setRunePages(RunePages runePages) {
        mRunePages = runePages;
    }
}
