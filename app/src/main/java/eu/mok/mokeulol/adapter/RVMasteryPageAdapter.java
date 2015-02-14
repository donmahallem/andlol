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

import eu.m0k.lol.api.model.MasteryPages;
import eu.mok.mokeulol.viewholder.MasteryPageViewHolder;

public class RVMasteryPageAdapter extends RecyclerView.Adapter<MasteryPageViewHolder> {

    private MasteryPages mMasteryPages = new MasteryPages();

    @Override
    public MasteryPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MasteryPageViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(MasteryPageViewHolder holder, int position) {
        holder.setMasteryPage(this.mMasteryPages.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mMasteryPages.size();
    }


    public void setMasteryPages(MasteryPages masteryPages) {
        this.mMasteryPages.clear();
        this.mMasteryPages.addAll(masteryPages);
        this.notifyDataSetChanged();
    }
}
