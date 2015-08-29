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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Event;
import eu.m0k.lol.api.model.Item;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.MatchDetail;
import eu.m0k.lol.api.model.Participant;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.TimeLineFrame;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.view.ChampionIconImageView;
import eu.mok.mokeulol.viewholder.LayoutViewHolder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class RVMatchEventAdapter extends RecyclerView.Adapter<RVMatchEventAdapter.EventViewHolder> {
    private final int TYPE_GENERAL = 0, TYPE_CHAMPION_KILL = 2, TYPE_ITEM_PURCHASE = 3,
            TYPE_WARD_PLACED = 1, TYPE_SPELL_LEVEL_UP = 4;
    private MatchDetail mMatchDetail;
    private ArrayList<Event> mEvents = new ArrayList<Event>();

    @Override
    public RVMatchEventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CHAMPION_KILL:
                return new TimelineChampionKillViewHolder(parent);
            case TYPE_ITEM_PURCHASE:
                return new ItemEventViewHolder(parent);
            case TYPE_WARD_PLACED:
                return new WardEventViewHolder(parent);
            case TYPE_SPELL_LEVEL_UP:
                return new SpellLevelUpEventViewHolder(parent);
            default:
                return new SimpleEventViewHolder(parent);
        }
    }

    @Override
    public void onViewRecycled(EventViewHolder holder) {
        holder.reset();
    }

    @Override
    public void onBindViewHolder(RVMatchEventAdapter.EventViewHolder holder, int position) {
        holder.setEvent(this.mEvents.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        switch (this.mEvents.get(position).getEventType()) {
            case CHAMPION_KILL:
                return TYPE_CHAMPION_KILL;
            case ITEM_DESTROYED:
            case ITEM_UNDO:
            case ITEM_PURCHASED:
            case ITEM_SOLD:
                return TYPE_ITEM_PURCHASE;
            case WARD_PLACED:
                return TYPE_WARD_PLACED;
            case SKILL_LEVEL_UP:
                return TYPE_SPELL_LEVEL_UP;
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
        private TextView mTxtPlaytime;

        public EventViewHolder(ViewGroup viewGroup, int layout) {
            super(viewGroup, layout);
            this.mTxtPlaytime = (TextView) this.itemView.findViewById(R.id.txtPlaytime);
        }

        public Event getEvent() {
            return mEvent;
        }

        public final void setEvent(Event event) {
            if (this.mEvent != event && event != null) {
                this.mEvent = event;
                this.mTxtPlaytime.setText(formatPlaytime(this.mEvent.getTimestamp()));
                this.onEventUpdated(this.mEvent);
            }
        }

        private String formatPlaytime(final long playtime) {
            final long seconds = playtime / 1000L;
            return "" + (seconds / 60) + ":" + (seconds % 60);
        }

        protected abstract void onEventUpdated(final Event event);

        protected abstract void reset();
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

        @Override
        protected void reset() {

        }
    }

    class WardEventViewHolder extends EventViewHolder {
        private TextView mTxtTitle;
        private ImageView mIvIcon;

        public WardEventViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.vh_timeline_event);
            this.mTxtTitle = (TextView) this.itemView.findViewById(R.id.txtTitle);
            this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.ivIcon);
        }

        @Override
        protected void onEventUpdated(Event event) {
            if (event == null)
                return;
            this.mTxtTitle.setText(event.getEventType().name());
            switch (event.getWardType()) {
                case SIGHT_WARD:
                    Util.getPicasso().load(Item.getUri(2044)).placeholder(R.drawable.ic_favorite).into(this.mIvIcon);
                    break;
                case VISION_WARD:
                    Util.getPicasso().load(Item.getUri(2043)).placeholder(R.drawable.ic_favorite).into(this.mIvIcon);
                    break;
                case YELLOW_TRINKET:
                    Util.getPicasso().load(Item.getUri(3340)).placeholder(R.drawable.ic_favorite).into(this.mIvIcon);
                    break;
                case YELLOW_TRINKET_UPGRADE:
                    Util.getPicasso().load(Item.getUri(3361)).placeholder(R.drawable.ic_favorite).into(this.mIvIcon);
                    break;
            }
        }

        @Override
        protected void reset() {
            Util.getPicasso().cancelRequest(this.mIvIcon);
            this.mIvIcon.setImageResource(R.drawable.ic_launcher);
            this.mTxtTitle.setText("Ward Event");
        }
    }

    class SpellLevelUpEventViewHolder extends EventViewHolder {
        private TextView mTxtTitle;
        private ImageView mIvIcon;
        private Callback<Champion> CALLBACK = new Callback<Champion>() {
            @Override
            public void success(Champion champion, Response response) {
                Util.getPicasso().load(champion.getSpells().get(getEvent().getSkillSlot() - 1).getImageUri()).placeholder(R.drawable.ic_favorite).into(mIvIcon);
                SpellLevelUpEventViewHolder.this.mTxtTitle.setText(champion.getSpells().get(getEvent().getSkillSlot() - 1).getName());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };

        public SpellLevelUpEventViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.vh_timeline_event);
            this.mTxtTitle = (TextView) this.itemView.findViewById(R.id.txtTitle);
            this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.ivIcon);
        }

        @Override
        protected void onEventUpdated(Event event) {
            if (event == null)
                return;
            Util.getLeagueApi().getStaticEndpoint().getChampion(Region.EUW, mMatchDetail.getParticipants().getParticipantById(event.getParticipantId()).getChampionId(), Locale.GERMAN, CALLBACK);

        }

        @Override
        protected void reset() {
            Util.getPicasso().cancelRequest(this.mIvIcon);
            this.mTxtTitle.setText("Spell Skilled");
            this.mIvIcon.setImageResource(R.drawable.ic_launcher);
        }
    }


    class ItemEventViewHolder extends EventViewHolder {
        private TextView mTxtTitle;
        private ImageView mIvIcon, mIvItem;
        private Callback<Item> CALLBACK = new Callback<Item>() {
            @Override
            public void success(Item item, Response response) {
                if (getEvent().getItemId() == item.getId())
                    mTxtTitle.setText(item.getName());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };

        public ItemEventViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.vh_timeline_event_item);
            this.mTxtTitle = (TextView) this.itemView.findViewById(R.id.txtTitle);
            this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.ivIcon);
            this.mIvItem = (ImageView) this.itemView.findViewById(R.id.ivItem);
        }

        @Override
        protected void onEventUpdated(Event event) {
            if (event == null)
                return;
            switch (event.getEventType()) {
                case ITEM_DESTROYED:
                    this.mTxtTitle.setText(R.string.item_destroyed);
                    this.mIvIcon.setImageResource(R.drawable.ic_delete_grey600_18dp);
                    break;
                case ITEM_PURCHASED:
                    this.mTxtTitle.setText(R.string.item_purchased);
                    this.mIvIcon.setImageResource(R.drawable.ic_add_grey600_18dp);
                    break;
                case ITEM_SOLD:
                    this.mTxtTitle.setText(R.string.item_sold);
                    break;
                case ITEM_UNDO:
                    this.mTxtTitle.setText(R.string.item_undo);
                    this.mIvIcon.setImageResource(R.drawable.ic_undo_grey600_18dp);
                    break;
            }
            Util.getPicasso().load(Item.getUri(event.getItemId())).placeholder(R.drawable.ic_favorite).into(this.mIvItem);
            Util.getLeagueApi().getStaticEndpoint().getItem(Region.EUW, event.getItemId(), Locale.GERMAN, CALLBACK);
        }

        @Override
        protected void reset() {
            Util.getPicasso().cancelRequest(this.mIvItem);
            this.mTxtTitle.setText("Buy Item");
            this.mIvIcon.setImageResource(R.drawable.ic_launcher);
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

        @Override
        protected void reset() {
            Util.getPicasso().cancelRequest(this.mIvChampionIcon1);
            Util.getPicasso().cancelRequest(this.mIvChampionIcon2);
            this.mIvChampionIcon1.setImageResource(R.drawable.ic_launcher);
            this.mIvChampionIcon2.setImageResource(R.drawable.ic_launcher);
        }
    }

}
