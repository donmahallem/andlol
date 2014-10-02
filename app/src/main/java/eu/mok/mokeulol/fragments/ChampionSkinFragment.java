package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import eu.mok.mokeulol.Util;
import eu.mok.mokeulol.helper.picasso.SCHEME;

/**
 * Created by Don on 30.09.2014.
 */
public class ChampionSkinFragment extends Fragment {
    private final static String ARGS_CHAMPION_KEY = "championKey", ARGS_SKIN_NUM = "skinNUm";
    private ImageView mImageView;

    public ChampionSkinFragment() {

    }

    public static ChampionSkinFragment createInstance(String championKey, int skinNum) {
        ChampionSkinFragment fragment = new ChampionSkinFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_SKIN_NUM, skinNum);
        bundle.putString(ARGS_CHAMPION_KEY, championKey);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mImageView = new ImageView(container.getContext());
        return this.mImageView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final String key = this.getArguments().getString(ARGS_CHAMPION_KEY);
        final int num = this.getArguments().getInt(ARGS_SKIN_NUM, 0);
        Util.getPicasso().load(SCHEME.SPLASH + "://" + key + "_" + num + ".jpg").placeholder(android.R.drawable.ic_menu_upload).error(android.R.drawable.ic_delete).into(mImageView);
    }
}