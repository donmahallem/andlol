/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.m0k.lol.api.internal.LeagueCallback;

/**
 * Created by Don on 05.10.2014.
 */
public class LeagueRequest {
    private final String mUrl;
    private final List<Header> mHeaders;
    private final CachePolicy mCachePolicy;
    private final LeagueCallback mLeagueCallback;

    private LeagueRequest(String url, List<Header> headers, CachePolicy cachePolicy, LeagueCallback leagueCallback) {
        if (url == null) {
            throw new NullPointerException("URL must not be null.");
        }
        if (cachePolicy == null)
            this.mCachePolicy = CachePolicy.NORMAL;
        else
            this.mCachePolicy = cachePolicy;
        this.mUrl = url;
        this.mLeagueCallback = leagueCallback;
        if (headers == null) {
            this.mHeaders = Collections.emptyList();
        } else {
            this.mHeaders = Collections.unmodifiableList(new ArrayList<Header>(headers));
        }
    }

    public String getUrl() {
        return mUrl;
    }

    public List<Header> getHeaders() {
        return mHeaders;
    }

    public CachePolicy getCachePolicy() {
        return mCachePolicy;
    }

    public static class Builder {
        private String mUrl;
        private List<Header> mHeaders;
        private CachePolicy mCachePolicy = CachePolicy.NORMAL;
        private LeagueCallback mLeagueCallback;
        public Builder() {
        }

        public Builder addHeader(String name, String value) {
            this.addHeader(new Header(name, value));
            return this;
        }

        public String getUrl() {
            return mUrl;
        }

        public Builder setUrl(String url) {
            this.mUrl = url;
            return this;
        }

        public List<Header> getHeaders() {
            return mHeaders;
        }

        public CachePolicy getCachePolicy() {
            return mCachePolicy;
        }

        public Builder setCachePolicy(CachePolicy cachePolicy) {
            this.mCachePolicy = cachePolicy;
            return this;
        }

        public Builder setLeagueCallback(LeagueCallback leagueCallback) {
            this.mLeagueCallback = leagueCallback;
            return this;
        }

        public Builder addHeader(Header header) {
            if (header == null)
                return this;
            this.mHeaders.add(header);
            return this;
        }

        public LeagueRequest build() {
            return new LeagueRequest(this.mUrl, this.mHeaders, this.mCachePolicy, this.mLeagueCallback);
        }
    }
}
