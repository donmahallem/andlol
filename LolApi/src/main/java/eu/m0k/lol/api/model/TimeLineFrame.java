/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TimeLineFrame {
    @Expose
    @SerializedName("participantFrames")
    private ParticipantFrames mParticipantFrames;
    @Expose
    @SerializedName("timestamp")
    private long mTimestamp;
    @Expose
    @SerializedName("events")
    private ArrayList<Event> mEvents;

    /**
     * Map of each participant ID to the participant's information for the frame
     *
     * @return participant frames
     */
    public ParticipantFrames getParticipantFrames() {
        return mParticipantFrames;
    }

    /**
     * Represents how many milliseconds into the game the frame occurred.
     * @return time
     */
    public long getTimestamp() {
        return mTimestamp;
    }

    /**
     * List of events for this frame.
     *
     * @return the List of events for this frame.
     */
    public ArrayList<Event> getEvents() {
        return mEvents;
    }
}
