/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Don on 05.10.2014.
 */
public interface TypedInput {
    /**
     * Returns the mime type.
     */
    public String mimeType();

    /**
     * Length in bytes. Returns {@code -1} if length is unknown.
     */
    public long length();

    /**
     * Read bytes as stream. Unless otherwise specified, this method may only be called once. It is
     * the responsibility of the caller to close the stream.
     */
    public InputStream in() throws IOException;
}
