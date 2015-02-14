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

import eu.m0k.lol.api.model.League;
import eu.mok.mokeulol.viewholder.LeagueEntryViewHolder;

public class RVLeagueSummonerAdapter extends RecyclerView.Adapter<LeagueEntryViewHolder> {

    private League mLeague;

    @Override
    public LeagueEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeagueEntryViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(LeagueEntryViewHolder holder, int position) {
        holder.setLeagueEntry(this.mLeague.getLeagueEntries().get(position));
    }

    @Override
    public int getItemCount() {
        if (this.mLeague == null)
            return 0;
        if (this.mLeague.getLeagueEntries() == null)
            return 0;
        return this.mLeague.getLeagueEntries().size();
    }

    public void setLeague(League league) {
        this.mLeague = league;
        notifyDataSetChanged();
    }
}
