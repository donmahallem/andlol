/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import eu.m0k.lol.api.model.ChampData;
import eu.m0k.lol.api.model.Locale;

public final class Parameter {
    public final static String LOCALE = "locale", CHAMPDATA = "champData", API_KEY = "api_key";
    private String mKey, mValue;

    public Parameter(String key, String value) {
        if (key == null)
            throw new IllegalArgumentException("Key must not be null");
        if (value == null)
            throw new IllegalArgumentException("Value must not be null");
        this.mKey = key;
        this.mValue = value;
    }

    public static Parameter from(Locale locale) {
        return new Parameter(LOCALE, locale.toString());
    }

    public static Parameter from(ChampData champData) {
        return new Parameter(CHAMPDATA, champData.toString());
    }

    public static Parameter from(ApiKey apiKey) {
        return new Parameter(API_KEY, apiKey.getToken());
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameter that = (Parameter) o;

        if (!mKey.equals(that.mKey)) return false;
        if (!mValue.equals(that.mValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int mresult = mKey.hashCode();
        mresult = 31 * mresult + mValue.hashCode();
        return mresult;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "key='" + mKey + '\'' +
                ", value='" + mValue + '\'' +
                '}';
    }

}
