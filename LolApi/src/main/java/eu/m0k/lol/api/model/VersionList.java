/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import java.util.ArrayList;
import java.util.Comparator;

public class VersionList extends ArrayList<Version> {
    /**
     * Sorts the Champions by Name Ascending
     */
    public final static Comparator<Champion> SortChampByNameAsc = new Comparator<Champion>() {

        @Override
        public int compare(Champion lhs, Champion rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };
    /**
     * Sorts the Champions by Name Descending
     */
    public final static Comparator<Champion> SortChampByNameDesc = new Comparator<Champion>() {

        @Override
        public int compare(Champion lhs, Champion rhs) {
            return rhs.getName().compareTo(lhs.getName());
        }
    };
}
