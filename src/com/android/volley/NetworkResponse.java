/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpStatus;

/**
 * Data and headers returned from {@link Network#performRequest(Request)}.
 */
public class NetworkResponse {
    /**
     * Creates a new network response.
     * @param statusCode the HTTP status code
     * @param responseStream the stream which holds the response body
     * @param headers Headers returned with this response, or null for none
     * @param notModified True if the server returned a 304 and the data was already in cache
     */
    public NetworkResponse(int statusCode, InputStream responseStream, Map<String, String> headers,
            boolean notModified) {
        this.statusCode = statusCode;
        this.mResponseStream = responseStream;
        this.headers = headers;
        this.notModified = notModified;
    }

    public NetworkResponse(InputStream responseStream) {
        this(HttpStatus.SC_OK, responseStream, Collections.<String, String>emptyMap(), false);
    }

    public NetworkResponse(InputStream responseStream, Map<String, String> headers) {
        this(HttpStatus.SC_OK, responseStream, headers, false);
    }
    
    /**
     * @return the response stream for this response
     */
    public InputStream getResponseStream() {
    	return mResponseStream;
    }
    
    /**
     * Set the response stream
     */
    public void setResponseStream(InputStream in) {
    	mResponseStream = in;
    }

    /** The HTTP status code. */
    public final int statusCode;

    /** Inputstream from this response */
    private InputStream mResponseStream;

    /** Response headers. */
    public final Map<String, String> headers;

    /** True if the server returned a 304 (Not Modified). */
    public final boolean notModified;
}