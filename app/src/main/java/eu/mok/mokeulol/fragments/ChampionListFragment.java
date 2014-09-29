package eu.mok.mokeulol.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import eu.m0k.lol.api.RequestClient;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.adapter.ChampionAdapter;
import retrofit.RestAdapter;

/**
 * Created by Don on 30.09.2014.
 */
public class ChampionListFragment extends ListFragment {
    private ChampionAdapter mChampionAdapter = new ChampionAdapter();

    public void onCreate() {
        this.onCreate();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.setListAdapter(mChampionAdapter);
        Task task = new Task();
        task.execute();
    }

    private class Task extends AsyncTask<Void, Void, ChampionList> {

        @Override
        protected ChampionList doInBackground(Void... params) {
            RequestClient mRequestClient = new RequestClient(Region.EUW, "106217b4-3ef1-42df-912b-f79ba89715b2", RestAdapter.LogLevel.BASIC);
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
