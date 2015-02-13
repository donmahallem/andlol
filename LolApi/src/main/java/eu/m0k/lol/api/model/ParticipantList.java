/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import java.util.ArrayList;

public class ParticipantList extends ArrayList<Participant> {

    public Participant getParticipantById(final int id) {
        for (Participant participant : this)
            if (participant.getParticipantId() == id)
                return participant;
        return null;
    }
}
