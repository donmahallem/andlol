/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Champion;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.activities.ChampionSkinActivity;
import eu.mok.mokeulol.adapter.SkinListAdapter;
import eu.mok.mokeulol.view.ChampionPassiveView;
import eu.mok.mokeulol.view.ChampionSpellView;
import it.sephiroth.android.library.widget.AdapterView;

public class ChampionDetailsFragment extends LeagueFragment implements AdapterView.OnItemClickListener {
    private final static String ARGS_CHAMP_ID = "champid";
    private TextView mTxtTitle, mTxtSubTitle, mTxtDescription, mTxtLore;
    private CircularImageView mIvChampIcon;
    private ChampionSpellView mChampionSpellView1, mChampionSpellView2, mChampionSpellView3, mChampionSpellView4;
    private SkinListAdapter mSkinListAdapter = new SkinListAdapter();
    private ChampionPassiveView mChampionPassiveView;
    private Champion mChampion;
    private ImageView mIvHeader;
    private Palette.PaletteAsyncListener IvHeaderAsyncListener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            if (isAdded()) {
                getActivity().getWindow().getDecorView().setBackgroundColor(palette.getVibrantColor(R.color.red_200));
            }
        }
    };
    private Target IvHeaderTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mIvHeader.setImageDrawable(new BitmapDrawable(bitmap));
            Palette.generateAsync(bitmap, IvHeaderAsyncListener);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            mIvHeader.setImageDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            mIvHeader.setImageDrawable(placeHolderDrawable);
        }
    };

    public ChampionDetailsFragment() {
        super();
    }

    public static ChampionDetailsFragment getInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_CHAMP_ID, id);
        ChampionDetailsFragment fragment = new ChampionDetailsFragment();
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
        this.mIvChampIcon = (CircularImageView) view.findViewById(R.id.ivChampionIcon);
        this.mTxtSubTitle = (TextView) view.findViewById(R.id.subTitle);
        this.mTxtDescription = (TextView) view.findViewById(R.id.txtDescription);
        this.mChampionSpellView1 = (ChampionSpellView) view.findViewById(R.id.championSpellView1);
        this.mChampionSpellView2 = (ChampionSpellView) view.findViewById(R.id.championSpellView2);
        this.mChampionSpellView3 = (ChampionSpellView) view.findViewById(R.id.championSpellView3);
        this.mChampionSpellView4 = (ChampionSpellView) view.findViewById(R.id.championSpellView4);
        this.mChampionPassiveView = (ChampionPassiveView) view.findViewById(R.id.championPassiveView);
        Task task = new Task();
        task.execute(getArguments().getInt(ARGS_CHAMP_ID, 32));
        this.mIvHeader = (ImageView) view.findViewById(R.id.ivHeader);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
//Title and subtitle
        mToolbar.setTitle("MY toolbar");
        mToolbar.setSubtitle("Subtitle");
        mToolbar.setBackgroundColor(getResources().getColor(R.color.light_blue_600));
//Menu
        mToolbar.inflateMenu(R.menu.toolbar_menu);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_share:
                        Toast.makeText(ChampionDetailsFragment.this.getActivity(), "Share", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });
//Navigation Icon
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChampionDetailsFragment.this.getActivity(), "Navigation", Toast.LENGTH_SHORT).show();
            }
        });
        ((ActionBarActivity) getActivity()).setSupportActionBar(mToolbar);
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
                    .into(IvHeaderTarget);
            if (this.mChampion.getSpells() != null) {
                this.mChampionSpellView1.setChampionSpell(this.mChampion.getSpells().get(0));
                this.mChampionSpellView2.setChampionSpell(this.mChampion.getSpells().get(1));
                this.mChampionSpellView3.setChampionSpell(this.mChampion.getSpells().get(2));
                this.mChampionSpellView4.setChampionSpell(this.mChampion.getSpells().get(3));
            }
            if (this.mChampion.getPassive() != null) {
                this.mChampionPassiveView.setChampionPassive(this.mChampion.getPassive());
            }
            if (this.mChampion.getSkins() != null) {
                this.mSkinListAdapter.setKey(this.mChampion.getKey());
                this.mSkinListAdapter.setSkins(this.mChampion.getSkins());
            }
            if (this.mChampion.getLore() != null) {

                this.mTxtDescription.setText(android.text.Html.fromHtml(this.mChampion.getLore()));
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this.getActivity(), ChampionSkinActivity.class);
        intent.putExtra(ChampionSkinActivity.EXTRA_CHAMPION_ID, this.mChampion.getId());
        startActivity(intent);
        Log.d("outa", " outa " + i + " - " + l);
    }

    private class Task extends AsyncTask<Integer, Void, LeagueResponse<Champion>> {

        @Override
        protected LeagueResponse<Champion> doInBackground(Integer... params) {
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
            return champ;
        }

        @Override
        protected void onPostExecute(LeagueResponse<Champion> result) {
            if (result != null && result.getBody() != null) {
                mChampion = result.getBody();
                updateViews();
            } else {
                int msg = 0;
                if (result == null) {
                    msg = R.string.unknown_error;
                } else {
                    msg = R.string.network_error;
                }
                if (ChampionDetailsFragment.this.isAdded()) {
                    Toast.makeText(ChampionDetailsFragment.this.getActivity(), msg, Toast.LENGTH_LONG);
                }
            }
        }
    }
}
