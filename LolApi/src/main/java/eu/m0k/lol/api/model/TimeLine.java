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

public class TimeLine {
    @Expose
    @SerializedName("frames")
    private ArrayList<TimeLineFrame> mFrames;
    @Expose
    @SerializedName("frameInterval")
    private long mFrameInterval;

    public ArrayList<TimeLineFrame> getFrames() {
        return mFrames;
    }

    public long getFrameInterval() {
        return mFrameInterval;
    }

    public int getTotalEvents() {
        int totalEvents = 0;
        for (TimeLineFrame frame : this.mFrames)
            if (frame.getEvents() != null)
                totalEvents += frame.getEvents().size();
        return totalEvents;
    }
}
