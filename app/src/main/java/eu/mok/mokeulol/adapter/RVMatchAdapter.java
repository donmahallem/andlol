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

import java.util.ArrayList;

import eu.m0k.lol.api.model.Match;
import eu.mok.mokeulol.viewholder.MatchViewHolder;

public class RVMatchAdapter extends RecyclerView.Adapter<MatchViewHolder> {
    private final int TYPE_UNKNOWN = 0, TYPE_MATCH = 1;
    private ArrayList<Match> mMatches = new ArrayList<Match>();
    private MatchViewHolder.OnMatchSelectListener mOnMatchSelectListener;

    public RVMatchAdapter() {
        super();
        this.setHasStableIds(true);
    }

    public void addMatch(Match match) {
        if (this.mMatches.contains(match))
            return;
        this.mMatches.add(match);
        this.notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        return TYPE_MATCH;
    }

    public long getItemId(int position) {
        if (this.mMatches.size() == 0)
            return -1;
        return this.mMatches.get(position).getMatchId();
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_MATCH:
                final MatchViewHolder matchViewHolder = new MatchViewHolder(parent);
                matchViewHolder.setOnMatchSelectListener(this.mOnMatchSelectListener);
                return matchViewHolder;
            default:
                return null;
        }
    }

    public void setOnMatchSelectListener(MatchViewHolder.OnMatchSelectListener onMatchSelectListener) {
        this.mOnMatchSelectListener = onMatchSelectListener;
        this.notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(MatchViewHolder holder) {
        super.onViewRecycled(holder);
        holder.reset();
    }

    @Override
    public void onBindViewHolder(MatchViewHolder matchViewHolder, int position) {
        matchViewHolder.setMatch(this.mMatches.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mMatches.size();
    }

    public void setMatches(ArrayList<Match> matches) {
        this.mMatches.clear();
        this.mMatches.addAll(matches);
        notifyDataSetChanged();
    }
}
