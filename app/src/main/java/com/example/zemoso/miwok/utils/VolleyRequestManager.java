package com.example.zemoso.miwok.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zemoso on 28/2/18.
 */

public class VolleyRequestManager {

    final static String sortBy = "stars";

    private static final String TAG = VolleyRequestManager.class.getSimpleName();

    private static VolleyRequestManager mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    public VolleyRequestManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized VolleyRequestManager getmInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new VolleyRequestManager(mContext);
        }
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

}
