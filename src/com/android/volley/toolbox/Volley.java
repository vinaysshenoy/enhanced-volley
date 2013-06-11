/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build;

import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;

import java.io.File;
import java.io.IOException;

public class Volley {

    /** Default on-disk cache directory. */
    private static final String DEFAULT_CACHE_DIR = "volley";

    /**
     * Creates a default instance of the worker pool and calls
     * {@link RequestQueue#start()} on it.
     * 
     * @param context
     *            A {@link Context} to use for creating the cache dir.
     * @param stack
     *            An {@link HttpStack} to use for the network, or null for
     *            default.
     * @return A started {@link RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context, HttpStack stack) {
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);

        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();

            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (NameNotFoundException e) {
        }

        if (stack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                stack = new HurlStack(new BasicUrlRewriter(), userAgent);
            } else {
                // Prior to Gingerbread, HttpUrlConnection was unreliable.
                // See:
                // http://android-developers.blogspot.com/2011/09/androids-http-clients.html
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent), new BasicUrlRewriter());
            }
        }

        Network network = new BasicNetwork(stack);

        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();

        return queue;
    }
    
    /**
     * Set Logs to enabled or disabled
     * @param isLoggable <code>true</code> to enable Logs, <code>false</code> to disable logs
     */
    public static void setLoggable(boolean isLoggable) {
        VolleyLog.sDebug = isLoggable;
    }
    
    /**
     * Creates a default instance of the worker pool and calls {@link RequestQueue#start()} on it.
     *
     * @param context A {@link Context} to use for creating the cache dir.
     * @return A started {@link RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }

    /**
     * Class to automatically rewrite URLs for GET methods based on whether the
     * request has params or not
     */
    private static class BasicUrlRewriter implements HttpStack.UrlRewriter {

        @Override
        public String rewriteUrl(Request<?> request) throws IOException {

            switch (request.getMethod()) {

            case Request.Method.GET: {

                String url = request.getUrl();

                try {
                    String encodedParams = request.getEncodedUrlBody();

                    if (encodedParams != null && encodedParams.length() > 0) {
                        if (!url.endsWith("?")) {
                            url += "?";
                        }
                        url += encodedParams;
                    }

                } catch (AuthFailureError e) {
                    return null;
                }
                return url;
            }

            case Request.Method.POST:
            case Request.Method.PUT:
            case Request.Method.DELETE: {
                return request.getUrl();
            }

            default:
                throw new IllegalStateException("Unknown request method.");

            }
        }
    }
}
