package com.example.zemoso.miwok;

import android.content.Context;

import com.android.volley.RequestQueue;

/**
 * Created by zemoso on 28/2/18.
 */

public class VolleyRequestManager {

    final static String sortBy = "stars";

    private static final String TAG = VolleyRequestManager.class.getSimpleName();

    private static VolleyRequestManager mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;



}
