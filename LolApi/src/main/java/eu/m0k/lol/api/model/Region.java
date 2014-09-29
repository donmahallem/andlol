package eu.m0k.lol.api.model;

/**
 * Created by Don on 23.09.2014.
 */
public enum Region {
    EUW("euw"), NA("na");
    private final String mName;

    Region(String name) {
        this.mName = name;
    }

    @Override
    public String toString() {
        return this.mName;
    }
}
