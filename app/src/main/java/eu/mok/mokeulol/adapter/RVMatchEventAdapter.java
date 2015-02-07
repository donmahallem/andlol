/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import eu.m0k.lol.api.model.Event;
import eu.m0k.lol.api.model.MatchDetail;
import eu.m0k.lol.api.model.Participant;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.TimeLineFrame;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.view.ChampionIconImageView;
import eu.mok.mokeulol.viewholder.LayoutViewHolder;
import timber.log.Timber;

public class RVMatchEventAdapter extends RecyclerView.Adapter<RVMatchEventAdapter.EventViewHolder> {
    private final int TYPE_GENERAL = 0, TYPE_PURCHASE = 1, TYPE_CHAMPION_KILL = 2;
    private MatchDetail mMatchDetail;
    private ArrayList<Event> mEvents = new ArrayList<Event>();

    @Override
    public RVMatchEventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CHAMPION_KILL:
                return new TimelineChampionKillViewHolder(parent);
            default:
                return new SimpleEventViewHolder(parent);
        }
    }

    @Override
    public void onBindViewHolder(RVMatchEventAdapter.EventViewHolder holder, int position) {
        holder.setEvent(this.mEvents.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        switch (this.mEvents.get(position).getEventType()) {
            case ITEM_PURCHASED:
                return TYPE_PURCHASE;
            case CHAMPION_KILL:
                return TYPE_CHAMPION_KILL;
            default:
                return TYPE_GENERAL;
        }
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

    static abstract class EventViewHolder extends LayoutViewHolder {
        private Event mEvent;

        public EventViewHolder(ViewGroup viewGroup, int layout) {
            super(viewGroup, layout);
        }

        public Event getEvent() {
            return mEvent;
        }

        public final void setEvent(Event event) {
            if (this.mEvent != event && event != null) {
                this.mEvent = event;
                this.onEventUpdated(this.mEvent);
            }
        }

        protected abstract void onEventUpdated(final Event event);
    }

    class SimpleEventViewHolder extends EventViewHolder {
        private TextView mTxtTitle;

        public SimpleEventViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.vh_timeline_event);
            this.mTxtTitle = (TextView) this.itemView.findViewById(R.id.txtTitle);
        }

        @Override
        protected void onEventUpdated(Event event) {
            if (event == null)
                return;
            switch (event.getEventType()) {
                case ASCENDED_EVENT:
                    this.mTxtTitle.setText(R.string.ascended_event);
                    break;
                case BUILDING_KILL:
                    this.mTxtTitle.setText(R.string.building_kill);
                    break;
                case CAPTURE_POINT:
                    this.mTxtTitle.setText(R.string.capture_point);
                    break;
                case CHAMPION_KILL:
                    this.mTxtTitle.setText(R.string.champion_kill);
                    break;
                case ELITE_MONSTER_KILL:
                    this.mTxtTitle.setText(R.string.elite_monster_kill);
                    break;
                case ITEM_DESTROYED:
                    this.mTxtTitle.setText(R.string.item_destroyed);
                    break;
                case ITEM_PURCHASED:
                    this.mTxtTitle.setText(R.string.item_purchased);
                    break;
                case ITEM_SOLD:
                    this.mTxtTitle.setText(R.string.item_sold);
                    break;
                case ITEM_UNDO:
                    this.mTxtTitle.setText(R.string.item_undo);
                    break;
                case PORO_KING_SUMMON:
                    this.mTxtTitle.setText(R.string.poro_king_summon);
                    break;
                case SKILL_LEVEL_UP:
                    this.mTxtTitle.setText(R.string.skill_level_up);
                    break;
                case WARD_KILL:
                    this.mTxtTitle.setText(R.string.ward_kill);
                    break;
                case WARD_PLACED:
                    this.mTxtTitle.setText(R.string.ward_placed);
                    break;
                default:
                    this.mTxtTitle.setText(R.string.unknown_error);
            }
        }
    }

    class TimelineChampionKillViewHolder extends EventViewHolder {
        private TextView mTxtTitle;
        private ChampionIconImageView mIvChampionIcon1, mIvChampionIcon2;

        public TimelineChampionKillViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.vh_timeline_event_champion_kill);
            this.mTxtTitle = (TextView) this.itemView.findViewById(R.id.txtTitle);
            this.mIvChampionIcon1 = (ChampionIconImageView) this.itemView.findViewById(R.id.ivChampionIcon1);
            this.mIvChampionIcon2 = (ChampionIconImageView) this.itemView.findViewById(R.id.ivChampionIcon2);
        }

        @Override
        protected void onEventUpdated(Event event) {
            Log.d("---", "killer: " + event.getKillerId() + " assists: " + event.getAssistingParticipantIds() + " dead: " + event.getVictimId());
            final Participant victim = mMatchDetail.getParticipants().getParticipantById(event.getVictimId());
            final Participant killer = mMatchDetail.getParticipants().getParticipantById(event.getKillerId());
            if (killer != null)
                this.mIvChampionIcon1.loadChampionById(Region.EUW, killer.getChampionId());
            if (victim != null)
                this.mIvChampionIcon2.loadChampionById(Region.EUW, victim.getChampionId());
        }
    }

}
