/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.model.Summoner;
import eu.m0k.lol.api.model.SummonerList;
import eu.m0k.lol.api.model.SummonerNames;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.viewholder.SummonerViewHolder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SummonerSearchActivity extends LeagueActivity {

    private String mSearchQuery = null;
    private RecyclerView mRecylcerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ADAPTER mAdapter = new ADAPTER();

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mSearchQuery = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            Log.d("search activity", "Query: " + mSearchQuery);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mSearchQuery != null)
            Util.getLeagueApi().getSummonerEndpoint(Region.EUW).getSummonersByName(SummonerNames.create(mSearchQuery), new Callback<SummonerList>() {
                @Override
                public void success(SummonerList summonerList, Response response) {
                    for (String key : summonerList.keySet()) {
                        mAdapter.addSummoner(summonerList.get(key));
                        Log.d("found", summonerList.get(key).toString());
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_search);
        this.mRecylcerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        this.mLinearLayoutManager = new LinearLayoutManager(this);
        this.mRecylcerView.setLayoutManager(this.mLinearLayoutManager);
        this.mRecylcerView.setAdapter(this.mAdapter);
        handleIntent(getIntent());
    }

    private class ADAPTER extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SummonerViewHolder.OnSummonerClickListener {
        private final int TYPE_SUMMONER = 1, TYPE_UNKNOWN = 0;
        private ArrayList<Summoner> mSummoners = new ArrayList<Summoner>();

        @Override
        public int getItemViewType(int position) {
            return TYPE_SUMMONER;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_SUMMONER:
                    final SummonerViewHolder summonerViewHolder = new SummonerViewHolder(parent);
                    summonerViewHolder.setOnSummonerClickListener(this);
                    return summonerViewHolder;
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SummonerViewHolder) {
                ((SummonerViewHolder) holder).setSummoner(this.mSummoners.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return this.mSummoners.size();
        }

        public void addSummoner(Summoner summoner) {
            if (!this.mSummoners.contains(summoner)) {
                this.mSummoners.add(summoner);
                notifyDataSetChanged();
            }
        }

        @Override
        public void onSummonerClicked(Summoner summoner) {
            startActivity(SummonerDetailsActivity.getInstance(SummonerSearchActivity.this, Region.EUW, summoner));
        }
    }


}
