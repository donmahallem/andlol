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

public class TimeLineFrames extends ArrayList<TimeLineFrame> {
    @Expose
    @SerializedName("participantFrames")
    private ParticipantFrames mParticipantFrames;
    @Expose
    @SerializedName("timestamp")
    private long mTimestamp;
    @Expose
    @SerializedName("events")
    private Event mEvents;

    public ParticipantFrames getParticipantFrames() {
        return mParticipantFrames;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public Event getEvents() {
        return mEvents;
    }
}
