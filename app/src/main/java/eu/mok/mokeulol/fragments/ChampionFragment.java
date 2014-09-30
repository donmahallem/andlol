package eu.mok.mokeulol.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eu.m0k.lol.api.RequestClient;
import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import retrofit.RestAdapter;

/**
 * Created by Don on 30.09.2014.
 */
public class ChampionFragment extends Fragment {
    private final static String ARGS_CHAMP_ID = "champid";
    private TextView mTxtTitle, mTxtSubTitle;
    private ImageView mIvHeader, mIvSpell1, mIvSpell2, mIvSpell3, mIvSpell4;
    private Champion mChampion;

    public ChampionFragment() {
        super();
    }

    public static ChampionFragment getInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_CHAMP_ID, id);
        ChampionFragment fragment = new ChampionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate() {
        this.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_champion_detail, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mTxtTitle = (TextView) view.findViewById(R.id.title);
        this.mIvHeader = (ImageView) view.findViewById(R.id.icon);
        this.mTxtSubTitle = (TextView) view.findViewById(R.id.subTitle);
        this.mIvSpell1 = (ImageView) view.findViewById(R.id.ivSpell1);
        this.mIvSpell2 = (ImageView) view.findViewById(R.id.ivSpell2);
        this.mIvSpell3 = (ImageView) view.findViewById(R.id.ivSpell3);
        this.mIvSpell4 = (ImageView) view.findViewById(R.id.ivSpell4);
        Task task = new Task();
        task.execute(getArguments().getInt(ARGS_CHAMP_ID, 32));
    }

    private void updateViews() {
        if (this.mChampion != null) {
            this.mTxtTitle.setText(this.mChampion.getName());
            if (mChampion.getSpells() != null) {
                Picasso.with(this.getActivity()).load("file:///android_asset/img/spell/" + this.mChampion.getSpells().get(0).getImage().getFull()).into(this.mIvSpell1);
                Picasso.with(this.getActivity()).load("file:///android_asset/img/spell/" + this.mChampion.getSpells().get(1).getImage().getFull()).into(this.mIvSpell2);
                Picasso.with(this.getActivity()).load("file:///android_asset/img/spell/" + this.mChampion.getSpells().get(2).getImage().getFull()).into(this.mIvSpell3);
                Picasso.with(this.getActivity()).load("file:///android_asset/img/spell/" + this.mChampion.getSpells().get(3).getImage().getFull()).into(this.mIvSpell4);
            }
        }
    }

    private class Task extends AsyncTask<Integer, Void, Champion> {

        @Override
        protected Champion doInBackground(Integer... params) {
            RequestClient mRequestClient = new RequestClient(Region.EUW, "106217b4-3ef1-42df-912b-f79ba89715b2", RestAdapter.LogLevel.BASIC);
            ChampData data = new ChampData();
            data.setSpells(true);

            Champion champs = mRequestClient.getStaticDataApi().getChampion(params[0], "de_DE", data);
            return champs;
        }

        @Override
        protected void onPostExecute(Champion result) {
            mChampion = result;
            updateViews();
        }
    }
}
