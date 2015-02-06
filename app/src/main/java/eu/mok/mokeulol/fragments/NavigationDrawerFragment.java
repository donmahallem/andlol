/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import eu.mok.mokeulol.R;
import eu.mok.mokeulol.Util;

public class NavigationDrawerFragment extends Fragment {
    private final static String COVER_IMAGE_PATH = "http://euw.leagueoflegends.com/sites/default/files/styles/wide_medium/public/upload/patch_notes_banner_19.jpg";

    private ImageView mCoverImage;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mCoverImage = (ImageView) view.findViewById(R.id.coverImage);
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.getPicasso().load(COVER_IMAGE_PATH).into(mCoverImage);
    }


}
