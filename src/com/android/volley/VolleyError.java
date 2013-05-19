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
 *
 * Modified by Vinay S Shenoy on 19/5/13
 */

package com.android.volley;

/**
 * Exception style class encapsulating Volley errors
 */
@SuppressWarnings("serial")
public class VolleyError extends Exception {

    public interface ErrorCode {

        public static final int GENERIC_ERROR = 0;
        public static final int NETWORK_ERROR = -1;
        public static final int SERVER_ERROR = -2;
        public static final int TIMEOUT_ERROR = -3;
        public static final int NO_CONNECTION_ERROR = -4;
        public static final int PARSE_ERROR = -5;
        public static final int BAD_REQUEST_ERROR = -6;
        public static final int AUTH_FAILURE_ERROR = -7;

    }

    public final NetworkResponse networkResponse;

    /**
     * Error code to identify the error type
     */
    public int errorCode;

    public VolleyError() {
        networkResponse = null;
        errorCode = ErrorCode.GENERIC_ERROR;
    }

    public VolleyError(NetworkResponse response) {
        networkResponse = response;
        errorCode = ErrorCode.GENERIC_ERROR;
    }

    public VolleyError(String exceptionMessage) {
        super(exceptionMessage);
        networkResponse = null;
        errorCode = ErrorCode.GENERIC_ERROR;
    }

    public VolleyError(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        networkResponse = null;
        errorCode = ErrorCode.GENERIC_ERROR;
    }

    public VolleyError(Throwable cause) {
        super(cause);
        networkResponse = null;
        errorCode = ErrorCode.GENERIC_ERROR;
    }
}
