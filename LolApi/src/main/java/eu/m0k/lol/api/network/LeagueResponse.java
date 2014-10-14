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

    private LeagueResponse(String url, int status, String reason, Headers headers, T body, boolean cached) {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (status < 200) {
            throw new IllegalArgumentException("Invalid status code: " + status);
        }
        if (reason == null) {
            throw new IllegalArgumentException("reason == null");
        }
        this.mUrl = url;
        this.mStatus = status;
        this.mReason = reason;
        this.mHeaders = headers;
        this.mBody = body;
        this.mCached = cached;
    }

    public static <T> LeagueResponse<T> cache(final String url, final boolean success, final T body) {
        return new LeagueResponse<T>(url, 200, "", null, body, true);
    }

    public static <T> LeagueResponse<T> network(final String url, final int code, final String reason, final Headers headers, final T body) {
        return new LeagueResponse<T>(url, code, reason, headers, body, false);
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
