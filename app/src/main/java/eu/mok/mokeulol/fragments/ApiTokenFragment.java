/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import eu.m0k.lol.api.LeagueApi;
import eu.m0k.lol.api.LogLevel;
import eu.m0k.lol.api.model.ChampionList;
import eu.m0k.lol.api.model.Region;
import eu.m0k.lol.api.network.ApiKey;
import eu.m0k.lol.api.network.LeagueResponse;
import eu.mok.mokeulol.R;

/**
 * Created by Don on 05.10.2014.
 */
public class ApiTokenFragment
        extends LeagueFragment
        implements View.OnClickListener, DialogInterface.OnCancelListener, TextWatcher {
    private EditText mTxtApiToken;
    private Button mBtnCheckToken;
    private ProgressDialog mProgressDialog;
    private Task mTask;

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
        this.mBtnCheckToken = (Button) view.findViewById(R.id.btnCheckToken);
        // Adding Listeners
        this.mTxtApiToken.addTextChangedListener(this);
        this.mBtnCheckToken.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnCheckToken) {
            this.mTask = new Task();
            this.mProgressDialog = new ProgressDialog(this.getActivity());
            this.mProgressDialog.setTitle(R.string.checking);
            this.mProgressDialog.setOnCancelListener(this);
            this.mProgressDialog.show();
            this.mTask.execute("token");
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (this.mTask != null && this.mTask.getStatus() != AsyncTask.Status.FINISHED) {
            this.mTask.cancel(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateViews();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        this.updateViews();
    }

    private void updateViews() {
        this.mBtnCheckToken.setEnabled((mTxtApiToken.getText().length() == 0) ? false : true);
    }

    private class Task extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            this.publishProgress(0);
            if (isCancelled())
                return false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            this.publishProgress(1);
            if (isCancelled())
                return false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            this.publishProgress(2);
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            ApiTokenFragment.this.mProgressDialog.hide();
            ApiTokenFragment.this.mProgressDialog = null;
            ApiTokenFragment.this.getLeagueFragmentListener().onShowChampionListFragment();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values[0] == 0)
                ApiTokenFragment.this.mProgressDialog
                        .setMessage(
                                ApiTokenFragment.this.getActivity().getResources().getString(R.string.starting)
                        );
            if (values[0] == 1)
                ApiTokenFragment.this.mProgressDialog
                        .setMessage(
                                ApiTokenFragment.this.getActivity().getResources().getString(R.string.checking_token)
                        );
            if (values[0] == 2)
                ApiTokenFragment.this.mProgressDialog
                        .setMessage(
                                ApiTokenFragment.this.getActivity().getResources().getString(R.string.checked)
                        );
        }
    }
}
