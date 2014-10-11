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

import java.io.IOException;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Locale;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.ChampionAdapter;

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
            try {
                ChampData champData = new ChampData();
                champData.setImage(true);
                LeagueResponse<ChampionList> list = Util.getLeagueApi().getChampionList(Region.EUW, champData, Locale.GERMAN, false);
                if (list.getBody() != null) {
                    list.getBody().sortByName(true);
                    return list.getBody();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ChampionList result) {
            mChampionAdapter.setChampionList(result);
        }
    }
}
