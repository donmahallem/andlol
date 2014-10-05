/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import eu.mok.mokeulol.R;

/**
 * Created by Don on 05.10.2014.
 */
public class ApiTokenFragment extends LeagueFragment {
    private EditText mTxtApiToken;

    public ApiTokenFragment() {
        super();
    }

    public static ApiTokenFragment getInstance() {
        return new ApiTokenFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_api_token, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mTxtApiToken = (EditText) view.findViewById(R.id.apiToken);
    }
}
