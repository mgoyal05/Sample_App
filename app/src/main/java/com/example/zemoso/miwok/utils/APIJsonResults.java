package com.example.zemoso.miwok.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.zemoso.miwok.models.JsonResultSearchObject;

import java.util.List;

import io.realm.Realm;

/**
 * Created by zemoso on 15/3/18.
 */

public class APIJsonResults {

    private static APIJsonResults sInstance = null;

    public APIJsonResults() {
    }

    public synchronized static APIJsonResults getInstance() {
        if (sInstance == null) {
            sInstance = new APIJsonResults();
        }
        return sInstance;
    }

    public void fetchJsonResultRequest(final Context context, String url) {

        Response.Listener<List<JsonResultSearchObject>> resultsListener = new Response.Listener<List<JsonResultSearchObject>>() {
            @Override
            public void onResponse(List<JsonResultSearchObject> response) {
/*                if(!mResultTextView.getText().equals("")){
                    mResultTextView.setText("");
                }*/
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.insert(response);
                realm.commitTransaction();
                realm.close();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("ERROR", "Internet");
            }
        };

        JsonResultSearchObjectRequest jsonResultSearchObjectRequest = new JsonResultSearchObjectRequest(Request.Method.GET, url, null, resultsListener, errorListener);

        VolleyRequestManager.getmInstance(context).getmRequestQueue().add(jsonResultSearchObjectRequest);
    }

}
