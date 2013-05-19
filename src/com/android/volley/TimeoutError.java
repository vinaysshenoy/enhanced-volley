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
 * Indicates that the connection or the socket timed out.
 */
@SuppressWarnings("serial")
public class TimeoutError extends VolleyError {

    public TimeoutError() {
        errorCode = ErrorCode.TIMEOUT_ERROR;
    }

    public TimeoutError(NetworkResponse response) {
        super(response);
        errorCode = ErrorCode.TIMEOUT_ERROR;
    }

    public TimeoutError(String exceptionMessage) {
        super(exceptionMessage);
        errorCode = ErrorCode.TIMEOUT_ERROR;
    }

    public TimeoutError(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        errorCode = ErrorCode.TIMEOUT_ERROR;
    }

    public TimeoutError(Throwable cause) {
        super(cause);
        errorCode = ErrorCode.TIMEOUT_ERROR;
    }
}
