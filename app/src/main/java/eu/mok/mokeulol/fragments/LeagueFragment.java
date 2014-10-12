/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

public abstract class LeagueFragment extends Fragment {

    private LeagueFragmentListener mLeagueFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mLeagueFragmentListener = (LeagueFragmentListener) activity;
        } catch (ClassCastException exception) {
            throw new ClassCastException(activity.getClass().getSimpleName() + " must include LeagueFragmentListener.");
        }
    }

    /**
     * Gets the LeagueFragmentListener
     *
     * @return the LeagueFragmentListener
     */
    public LeagueFragmentListener getLeagueFragmentListener() {
        return this.mLeagueFragmentListener;
    }

    /**
     * The LeagueFragmentListener
     * Responsible for switching the Fragments inside the Activity
     */
    public static interface LeagueFragmentListener {
        public void onShowChampionListFragment();

        public void onShowChampionDetailsFragment(int id);

        public void onShowApiTokenFragment();
    }
}
