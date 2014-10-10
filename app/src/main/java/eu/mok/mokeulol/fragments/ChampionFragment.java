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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import java.io.IOException;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.adapter.SkinListAdapter;
import eu.mok.mokeulol.view.ChampionSpellView;
import it.sephiroth.android.library.widget.HListView;

/**
 * Created by Don on 30.09.2014.
 */
public class ChampionFragment extends LeagueFragment {
    private final static String ARGS_CHAMP_ID = "champid";
    private TextView mTxtTitle, mTxtSubTitle, mTxtDescription, mTxtLore;
    private CircularImageView mIvChampIcon;
    private ChampionSpellView mIvSpell1, mIvSpell2, mIvSpell3, mIvSpell4;
    private HListView mHListView;
    private SkinListAdapter mSkinListAdapter = new SkinListAdapter();
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
        this.mTxtSubTitle = (TextView) view.findViewById(R.id.subTitle);
        this.mHListView = (HListView) view.findViewById(R.id.hListView);
        this.mIvChampIcon = (CircularImageView) view.findViewById(R.id.ivChampionIcon);
        this.mTxtSubTitle = (TextView) view.findViewById(R.id.subTitle);
        this.mTxtDescription = (TextView) view.findViewById(R.id.txtDescription);
        this.mIvSpell1 = (ChampionSpellView) view.findViewById(R.id.ivSpell1);
        this.mIvSpell2 = (ChampionSpellView) view.findViewById(R.id.ivSpell2);
        this.mIvSpell3 = (ChampionSpellView) view.findViewById(R.id.ivSpell3);
        this.mIvSpell4 = (ChampionSpellView) view.findViewById(R.id.ivSpell4);
        Task task = new Task();
        task.execute(getArguments().getInt(ARGS_CHAMP_ID, 32));
        this.mHListView.setAdapter(this.mSkinListAdapter);
    }

    private void updateViews() {
        if (this.mChampion != null) {
            this.mTxtTitle.setText(this.mChampion.getName());
            this.mTxtSubTitle.setText(this.mChampion.getTitle());
            Util.getPicasso()
                    .load(this.mChampion.getImageUri())
                    .resize(200, 200)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_menu_rotate)
                    .error(android.R.drawable.ic_delete)
                    .into(this.mIvChampIcon);
            if (mChampion.getSpells() != null) {
                this.mIvSpell1.setChampion(this.mChampion.getSpells().get(0));
                this.mIvSpell2.setChampion(this.mChampion.getSpells().get(1));
                this.mIvSpell3.setChampion(this.mChampion.getSpells().get(2));
                this.mIvSpell4.setChampion(this.mChampion.getSpells().get(3));
            }
            if (this.mChampion.getSkins() != null) {
                this.mSkinListAdapter.setKey(this.mChampion.getKey());
                this.mSkinListAdapter.setSkins(this.mChampion.getSkins());
            }
            if (this.mChampion.getLore() != null) {
                this.mTxtDescription.setText(this.mChampion.getLore());
            }
        }
    }

    private class Task extends AsyncTask<Integer, Void, Champion> {

        @Override
        protected Champion doInBackground(Integer... params) {
            ChampData data = new ChampData();
            data.setSpells(true);
            data.setSkins(true);
            data.setLore(true);
            data.setInfo(true);
            data.setPassive(true);
            data.setImage(true);
            LeagueResponse<Champion> champ = null;
            try {
                champ = Util.getLeagueApi().getChampion(params[0], Region.EUW, data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (champ != null)
                return champ.getBody();
            else
                return null;
        }

        @Override
        protected void onPostExecute(Champion result) {
            mChampion = result;
            updateViews();
        }
    }
}
