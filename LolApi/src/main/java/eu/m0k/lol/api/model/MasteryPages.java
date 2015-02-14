/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import java.util.ArrayList;

public class MasteryPages extends ArrayList<MasteryPage> {

    @Override
    public String toString() {
        String output = "";
        for (MasteryPage masteryPage : this) {
            if (output.length() != 0)
                output += ",";
            output += masteryPage.toString();
        }
        return "MasteryPages { " + output + " }";
    }
}
