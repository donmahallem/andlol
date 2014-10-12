/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol.fragments;

import android.app.Activity;
import android.support.v4.app.ListFragment;

public class LeagueListFragment extends ListFragment {

    private LeagueFragment.LeagueFragmentListener mLeagueFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mLeagueFragmentListener = (LeagueFragment.LeagueFragmentListener) activity;
        } catch (ClassCastException exception) {
            throw new ClassCastException(activity.getClass().getSimpleName() + " must include LeagueFragmentListener.");
        }
    }

    /**
     * Gets the LeagueFragmentListener
     *
     * @return the LeagueFragmentListener
     */
    public LeagueFragment.LeagueFragmentListener getLeagueFragmentListener() {
        return this.mLeagueFragmentListener;
    }

}
