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
import java.util.Collections;

import eu.m0k.lol.api.model.Event;
import eu.m0k.lol.api.model.MatchDetail;
import eu.m0k.lol.api.model.TimeLineFrame;
import eu.mok.mokeulol.viewholder.TimelineFrameViewHolder;
import timber.log.Timber;

public class RVMatchEventAdapter extends RecyclerView.Adapter<TimelineFrameViewHolder> {

    private MatchDetail mMatchDetail;
    private ArrayList<Event> mEvents = new ArrayList<Event>();

    @Override
    public TimelineFrameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimelineFrameViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimelineFrameViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.mEvents.size();
    }

    public void setMatchDetail(MatchDetail detail) {
        if (detail != null) {
            this.mMatchDetail = detail;
            if (this.mMatchDetail.getTimeLine() != null) {
                this.mEvents.clear();
                for (TimeLineFrame timeLineFrame : this.mMatchDetail.getTimeLine().getFrames())
                    if (timeLineFrame.getEvents() != null)
                        this.mEvents.addAll(timeLineFrame.getEvents());
                Collections.sort(this.mEvents, Event.SortDescending);
                Timber.d("setMatchDetail - Events added: " + this.mEvents.size());
            }
            notifyDataSetChanged();
        }
    }
}
