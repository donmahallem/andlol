package eu.mok.mokeulol.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

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
    private OnChampSelectedListener mOnChampSelectedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mOnChampSelectedListener = (OnChampSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
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
        this.mOnChampSelectedListener.onChampSelected(this.mChampionAdapter.getItem(position).getId());
    }

    public static interface OnChampSelectedListener {
        public void onChampSelected(int champ);
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
