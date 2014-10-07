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

import eu.m0k.lol.api.internal.Util;
import eu.m0k.lol.api.model.Region;

public class LeagueRequest<T> {
    private final String mUrl;
    private final List<Header> mHeaders;
    private final Class<T> mClass;

    private LeagueRequest(String url, List<Header> headers, Class<T> clazz) {
        if (url == null) {
            throw new NullPointerException("URL must not be null.");
        }
        this.mUrl = url;
        this.mClass = clazz;
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

    public static class Builder<T> {
        private String mUrl;
        private List<Header> mHeaders;
        private List<Parameter> mParameters;
        private Region mRegion;
        private ApiToken mApiToken;
        private Class<T> mClass;

        public Builder() {
            this.mHeaders = new ArrayList<Header>();
            this.mParameters = new ArrayList<Parameter>();
        }

        public Builder addHeader(String name, String value) {
            this.addHeader(new Header(name, value));
            return this;
        }

        public Region getRegion() {
            return this.mRegion;
        }

        public Builder setRegion(Region region) {
            this.mRegion = region;
            return this;
        }

        public Builder setApiToken(ApiToken token) {
            this.mApiToken = token;
            return this;
        }

        public String getUrl() {
            return mUrl;
        }

        public Builder setUrl(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder addParameter(String key, String value) {
            this.addParameter(new Parameter(key, value));
            return this;
        }

        public Builder addParameter(Parameter parameter) {
            if (parameter != null)
                this.mParameters.add(parameter);
            return this;
        }

        public List<Header> getHeaders() {
            return mHeaders;
        }

        public Builder addHeader(Header header) {
            if (header != null)
                this.mHeaders.add(header);
            return this;
        }

        public LeagueRequest<T> build() {
            if (this.mApiToken == null)
                throw new RuntimeException("ApiToken should be set");
            if (this.mClass == null)
                throw new RuntimeException("Class Type should be set");
            return new LeagueRequest<T>(this.mUrl + "?" + Util.parameterToString(this.mParameters), this.mHeaders, this.mClass);
        }

        public void setType(Class<T> clazz) {
            this.mClass = clazz;
        }

    }
}
