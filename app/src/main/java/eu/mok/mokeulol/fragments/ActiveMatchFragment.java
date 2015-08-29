/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import eu.m0k.lol.api.model.CurrentGameInfo;
import eu.m0k.lol.api.model.Participant;
import eu.m0k.lol.api.model.Platform;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.viewholder.ParticipantViewHolder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class ActiveMatchFragment extends LeagueFragment {
    private final static String KEY_SUMMONER_ID = "summonerId", KEY_REGION = "region";
    private long mSummonerId = -1;
    private Region mRegion;
    private View mLoadingContainer;
    private RecyclerView mRecyclerView;
    private TextView mTxtStatus;
    private ParticipantAdapter mParticipantAdapter = new ParticipantAdapter();
    private Callback<CurrentGameInfo> CALLBACK = new Callback<CurrentGameInfo>() {
        @Override
        public void success(CurrentGameInfo currentGameInfo, Response response) {
            Timber.d("success - " + currentGameInfo.toString());
            setLoading(false);
            ActiveMatchFragment.this.mParticipantAdapter.setParticipants(currentGameInfo.getParticipants());
        }

        @Override
        public void failure(RetrofitError error) {
            if (error.getResponse().getStatus() == 404) {
                mTxtStatus.setText(R.string.error_player_not_in_match);
            } else {
                mTxtStatus.setText(R.string.error_requesting_server);
            }
        }
    };

    public static Fragment getInstance(Region region, Summoner summoner) {
        return getInstance(region, summoner.getId());
    }

    public static Fragment getInstance(Region region, long summonerId) {
        final ActiveMatchFragment matchHistoryFragment = new ActiveMatchFragment();
        final Bundle bundle = new Bundle();
        bundle.putLong(KEY_SUMMONER_ID, summonerId);
        bundle.putSerializable(KEY_REGION, region);
        matchHistoryFragment.setArguments(bundle);
        return matchHistoryFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSummonerId = this.getArguments().getLong(KEY_SUMMONER_ID);
        this.mRegion = (Region) this.getArguments().getSerializable(KEY_REGION);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_current_game, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mLoadingContainer = view.findViewById(R.id.loadingContainer);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.mRecyclerView.setAdapter(mParticipantAdapter);
        this.mTxtStatus = (TextView) view.findViewById(R.id.txtStatus);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setLoading(true);
        Util.getLeagueApi().getCurrentGameEndpoint().getMatch(Region.EUW, Platform.EUW1, this.mSummonerId, CALLBACK);
    }

    private void setLoading(final boolean loading) {
        this.mRecyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        this.mLoadingContainer.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    private class ParticipantAdapter extends RecyclerView.Adapter<ParticipantViewHolder> {

        private ArrayList<Participant> mParticipants = new ArrayList<Participant>();

        public void setParticipants(final ArrayList<Participant> participants) {
            this.mParticipants.clear();
            this.mParticipants.addAll(participants);
            this.notifyDataSetChanged();
        }

        @Override
        public ParticipantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ParticipantViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ParticipantViewHolder holder, int position) {
            holder.setParticipant(this.mParticipants.get(position));
        }

        @Override
        public int getItemCount() {
            return this.mParticipants.size();
        }
    }

}
