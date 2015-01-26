/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.activities.ChampionDetailsActivity;
import eu.mok.mokeulol.adapter.ChampionAdapter;
import eu.mok.mokeulol.adapter.RVChampionAdapter;
import eu.mok.mokeulol.adapter.RVRevealAnimator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class ChampionListFragment extends LeagueFragment implements RVChampionAdapter.OnChampSelectListener {
    private ChampionAdapter mChampionAdapter = new ChampionAdapter();
    private RecyclerView mRecyclerView;
    private RVChampionAdapter mRVChampionAdapter;
    private RVRevealAnimator RevealAnimator = new RVRevealAnimator();
    private Callback<ChampionList> CHAMPIONS_CALLBACK = new Callback<ChampionList>() {
        @Override
        public void success(ChampionList champions, Response response) {
            champions.sortByName(true);
            Timber.d("success - cached: " + response);
            ChampionListFragment.this.mRVChampionAdapter.setChampionList(champions);
            setLoading(false);
        }

        @Override
        public void failure(RetrofitError error) {
            Timber.e(error.getMessage());
        }
    };
    private View mLoadingContainer;

    public static ChampionListFragment getInstance() {
        return new ChampionListFragment();
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        this.getLeagueFragmentListener().onShowChampionDetailsFragment(this.mChampionAdapter.getItem(position).getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_champion_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mLoadingContainer = view.findViewById(R.id.loadingContainer);
        this.mRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        view.getContext().getResources().
                                getInteger(R.integer.columns), StaggeredGridLayoutManager.VERTICAL));
        /**
         * All cards are the same
         */
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setItemAnimator(RevealAnimator);
        super.onViewCreated(view, savedInstanceState);
        this.mRVChampionAdapter = new RVChampionAdapter();
        this.mRVChampionAdapter.setOnChampSelectListener(this);
        this.mRecyclerView.setAdapter(this.mRVChampionAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ChampionListFragment", "onResume()");
        ChampData data = new ChampData();
        data.setImage(true);
        this.setLoading(true);
        Util.getLeagueApi().getEndpointStatic().getChampions(Region.EUW, Locale.GERMAN, "5.2.1", data, CHAMPIONS_CALLBACK);
    }

    private void setLoading(final boolean loading) {
        this.mRecyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
        this.mLoadingContainer.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onChampSelected(Champion champion, View view) {
        startActivity(ChampionDetailsActivity.createIntent(this.getActivity(), champion.getId()));
    }

}
