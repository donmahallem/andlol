/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.network;

import com.squareup.okhttp.Headers;

public class LeagueResponse<T> {
    private final String mUrl;
    private final int mStatus;
    private final boolean mCached;
    private final String mReason;
    private final Headers mHeaders;
    private final T mBody;

    public LeagueResponse(String url, int status, String reason, Headers headers, T body, boolean cached) {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (status < 200) {
            throw new IllegalArgumentException("Invalid status code: " + status);
        }
        if (reason == null) {
            throw new IllegalArgumentException("reason == null");
        }
        if (headers == null) {
            throw new IllegalArgumentException("headers == null");
        }
        this.mUrl = url;
        this.mStatus = status;
        this.mReason = reason;
        this.mHeaders = headers;
        this.mBody = body;
        this.mCached = cached;
    }

    /**
     * is cached response
     * @return true if cached
     */
    public boolean isCached() {
        return mCached;
    }

    /**
     * Request URL.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Status line code.
     */
    public int getStatus() {
        return mStatus;
    }

    /**
     * Status line mReason phrase.
     */
    public String getReason() {
        return mReason;
    }

    /**
     * An unmodifiable collection of mHeaders.
     */
    public Headers getHeaders() {
        return mHeaders;
    }

    /**
     * Response mBody. May be {@code null}.
     */
    public T getBody() {
        return mBody;
    }
}
