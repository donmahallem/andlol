package eu.mok.mokeulol.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.m0k.lol.api.RequestClient;
import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.ChampionSkin;
import eu.m0k.lol.api.model.Region;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.helper.picasso.SCHEME;
import eu.mok.mokeulol.view.ChampionSpellView;
import it.sephiroth.android.library.widget.HListView;
import retrofit.RestAdapter;

/**
 * Created by Don on 30.09.2014.
 */
public class ChampionFragment extends Fragment {
    private final static String ARGS_CHAMP_ID = "champid";
    private TextView mTxtTitle, mTxtSubTitle, mTxtDescription, mTxtLore;
    private ImageView mIvChampIcon;
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
        this.mIvChampIcon = (ImageView) view.findViewById(R.id.ivChampionIcon);
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
            Util.getPicasso().load(SCHEME.CHAMPION_ICON + "://" + this.mChampion.getImage().getFull()).resize(200, 200).centerCrop().placeholder(android.R.drawable.ic_menu_upload).error(android.R.drawable.ic_delete).into(this.mIvChampIcon);
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

    private class SkinListAdapter extends BaseAdapter {
        private String mKey;
        private List<ChampionSkin> mSkins;

        public SkinListAdapter() {
            this("");
        }

        public SkinListAdapter(String championKey) {
            this(championKey, new ArrayList<ChampionSkin>());
        }

        public SkinListAdapter(String championKey, List<ChampionSkin> skins) {
            this.mKey = championKey;
            this.mSkins = skins;
        }

        @Override
        public int getCount() {
            return this.mSkins.size();
        }

        public void setKey(String key) {
            this.mKey = key;
            notifyDataSetChanged();
        }

        public void setSkins(List<ChampionSkin> skins) {
            this.mSkins.clear();
            this.mSkins.addAll(skins);
            notifyDataSetChanged();
        }

        @Override
        public ChampionSkin getItem(int position) {
            return this.mSkins.get(position);
        }

        @Override
        public long getItemId(int position) {
            return this.getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView view = null;
            if (convertView != null && convertView instanceof ImageView) {
                view = (ImageView) convertView;
            } else
                view = new ImageView(parent.getContext());
            Util.getPicasso().load(SCHEME.SPLASH + "://" + mKey + "_" + position + ".jpg").into(view);
            return view;
        }
    }

    private class VP extends FragmentPagerAdapter {
        private List<ChampionSkin> mSkins;
        private String mKey;

        public VP(FragmentManager fm, String key, List<ChampionSkin> skins) {
            super(fm);
            this.mKey = key;
            this.mSkins = skins;
        }

        @Override
        public int getCount() {
            return this.mSkins.size();
        }

        @Override
        public Fragment getItem(int i) {
            return ChampionSkinFragment.createInstance(mKey, i);
        }
    }

    private class Task extends AsyncTask<Integer, Void, Champion> {

        @Override
        protected Champion doInBackground(Integer... params) {
            RequestClient mRequestClient = new RequestClient(Region.EUW, "106217b4-3ef1-42df-912b-f79ba89715b2", RestAdapter.LogLevel.BASIC);
            ChampData data = new ChampData();
            data.setSpells(true);
            data.setSkins(true);
            data.setLore(true);
            data.setInfo(true);
            data.setPassive(true);
            data.setImage(true);

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
