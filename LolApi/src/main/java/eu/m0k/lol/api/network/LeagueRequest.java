/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import java.util.HashMap;
import java.util.Map;

import eu.m0k.lol.api.model.Region;

/**
 * Created by Don on 24.09.2014.
 */
public abstract class LeagueRequest<TYPE> {
    private final String mUrl;
    private final Region mRegion;
    private Map<String, String> mParameter = new HashMap<String, String>();
    private Class<TYPE> mClass;
    private CachePolicy mCachePolicy = CachePolicy.NETWORK_ONLY;

    public LeagueRequest(Region region, String url, Class<TYPE> _class) {
        this.mRegion = region;
        this.mUrl = url;
        this.mClass = _class;
    }

    /**
     * Sets or replaces the in key given parameter
     *
     * @param key   parameter key
     * @param value parameter value
     */
    public void addParameter(String key, String value) {
        this.mParameter.put(key, value);
    }

    public Map<String, String> getParameters() {
        return this.mParameter;
    }

    public String getUrl() {
        return this.mUrl.replaceAll("\\{region\\}", this.mRegion.toString()) + "?" + getParameterString();
    }

    /**
     * gets the current Cache Policy
     *
     * @return returns the current cache policy
     */
    public CachePolicy getCachePolicy() {
        return this.mCachePolicy;
    }

    /**
     * Sets the CachePolicy
     *
     * @param cachePolicy the CachePolicy to use
     */
    public void setCachePolicy(CachePolicy cachePolicy) {
        if (cachePolicy != null) {
            this.mCachePolicy = cachePolicy;
        }
    }

    public Region getRegion() {
        return mRegion;
    }

    public String getParameterString() {
        if (this.mParameter.size() == 0) {
            return "";
        }
        String ret = "";
        for (String key : this.mParameter.keySet()) {
            if (ret.length() != 0) {
                ret += "&";
            }
            ret += key + "=" + this.mParameter.get(key);
        }
        return ret;
    }
}
