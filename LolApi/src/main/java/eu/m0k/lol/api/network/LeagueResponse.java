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

import eu.m0k.lol.api.internal.TypedInput;

public class LeagueResponse<T> {
    private final String mUrl;
    private final int mStatus;
    private final String mReason;
    private final List<Header> mHeaders;
    private final TypedInput mBody;

    public LeagueResponse(String url, int status, String reason, List<Header> headers, TypedInput body) {
        if (url == null) {
            throw new IllegalArgumentException("mUrl == null");
        }
        if (status < 200) {
            throw new IllegalArgumentException("Invalid mStatus code: " + status);
        }
        if (reason == null) {
            throw new IllegalArgumentException("mReason == null");
        }
        if (headers == null) {
            throw new IllegalArgumentException("mHeaders == null");
        }
        this.mUrl = url;
        this.mStatus = status;
        this.mReason = reason;
        this.mHeaders = Collections.unmodifiableList(new ArrayList<Header>(headers));
        this.mBody = body;
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
    public List<Header> getHeaders() {
        return mHeaders;
    }

    /**
     * Response mBody. May be {@code null}.
     */
    public TypedInput getBody() {
        return mBody;
    }
}
