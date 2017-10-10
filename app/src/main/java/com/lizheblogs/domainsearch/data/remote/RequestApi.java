/*
 * Copyright 2016 Li Zhe <pulqueli@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lizheblogs.domainsearch.data.remote;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lizheblogs.domainsearch.bean.WhoisInfoError;
import com.lizheblogs.domainsearch.common.BaseCallBack;
import com.lizheblogs.domainsearch.common.SubApplication;
import com.lizheblogs.domainsearch.util.SubXStream;

import java.util.Iterator;
import java.util.Map;


/**
 * https://developer.android.com/training/volley/index.html
 * Base Request Class
 * Created by Norman.Li on 6/2/2016.
 * 网络交互
 */
public class RequestApi {
    private static final String TAG = "Request";
    private static final int socketTimeout = 60000;


    public static void RequestString(String url, final BaseCallBack mCallBack) {
        RequestString(url, null, mCallBack);
    }

    public static void RequestString(String url, String tag, final BaseCallBack mCallBack) {

        // pass second argument as "null" for GET requests
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, "Norman-Response: " + response);
                        if (mCallBack != null)
                            mCallBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Norman-ErrorResponse: " + error.getMessage());
                Log.i(TAG, "Norman-ErrorResponse: " + error.toString());
                Log.i(TAG, "Norman-ErrorResponse: " + error.getLocalizedMessage());
                if (error.networkResponse != null) {
                    Map<String, String> headers = error.networkResponse.headers;
                    if (headers != null) {
                        Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, String> entry = it.next();
                            Log.i(TAG, "key= " + entry.getKey() + " and value= " + entry.getValue());
                        }
                    }
                    Log.i(TAG, "Norman-ErrorResponse: " + error.networkResponse.statusCode);
                    Log.i(TAG, "Norman-ErrorResponse: " + new String(error.networkResponse.data));

                }
                String meg = error.getMessage();
                if (TextUtils.isEmpty(meg)) {
                    if (error.networkResponse != null) {
                        meg = new String(error.networkResponse.data);
                        if (!TextUtils.isEmpty(meg)) {
                            SubXStream xStream = new SubXStream();
                            xStream.alias("Error", WhoisInfoError.class);
                            WhoisInfoError whoisInfoError = (WhoisInfoError) xStream.fromXML(meg);
                            if (whoisInfoError != null && !TextUtils.isEmpty(whoisInfoError.getMessage())) {
                                meg = whoisInfoError.getMessage();
                            }
                        }
                    }
                }
                if (mCallBack != null)
                    mCallBack.onFailure(meg);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };


        DefaultRetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        req.setRetryPolicy(policy);
        // add the request object to the queue to be executed
        SubApplication.getInstance().addToRequestQueue(req, tag);
    }

    public static void cancelAll() {
        SubApplication.getInstance().cancelAllPendingRequests();
    }

    public static void cancelAllByTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            SubApplication.getInstance().cancelPendingRequests(tag);
        }
    }
}
