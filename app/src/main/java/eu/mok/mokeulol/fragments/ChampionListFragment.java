/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import eu.m0k.lol.api.RequestClient;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.ChampionAdapter;
import retrofit.RestAdapter;

/**
 * Created by Don on 30.09.2014.
 */
public class ChampionListFragment extends LeagueListFragment {
    private ChampionAdapter mChampionAdapter = new ChampionAdapter();

    public static ChampionListFragment getInstance() {
        return new ChampionListFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.setListAdapter(mChampionAdapter);
        Task task = new Task();
        task.execute();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        this.getLeagueFragmentListener().onShowChampionDetailsFragment(this.mChampionAdapter.getItem(position).getId());
    }

    private class Task extends AsyncTask<Void, Void, ChampionList> {

        @Override
        protected ChampionList doInBackground(Void... params) {
            RequestClient mRequestClient = new RequestClient(Region.EUW, Util.getLeagueApiToken(), RestAdapter.LogLevel.BASIC);
            ChampionList champs = mRequestClient.getStaticDataApi().getChampionList("de_DE");
            champs.sortByName(true);
            return champs;
        }

        @Override
        protected void onPostExecute(ChampionList result) {
            mChampionAdapter.setChampionList(result);
        }
    }
}
