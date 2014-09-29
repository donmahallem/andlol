package eu.m0k.lol.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Don on 23.09.2014.
 */
public class RunePage {
    @Expose
    @SerializedName("id")
    private long mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("current")
    private boolean mCurrent;
    @Expose
    @SerializedName("slots")
    private List<Rune> mSlots;

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isCurrent() {
        return mCurrent;
    }

    public List<Rune> getSlots() {
        return mSlots;
    }

    @Override
    public String toString() {
        return "RunePage{" +
                "id=" + mId +
                ", name='" + mName + '\'' +
                ", current=" + mCurrent +
                '}';
    }
}
