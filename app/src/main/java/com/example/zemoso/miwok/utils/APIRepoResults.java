package com.example.zemoso.miwok.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.zemoso.miwok.VolleyRequestManager;
import com.example.zemoso.miwok.models.RepoObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;

/**
 * Created by zemoso on 7/3/18.
 */

public class APIRepoResults {

    private static APIRepoResults sInstance = null;

    public APIRepoResults() {
    }

    public synchronized static APIRepoResults getInstance() {
        if(sInstance == null){
            sInstance = new APIRepoResults();
        }
        return sInstance;
    }

    public void fetchJSONObject(final Context context, String url){

        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
/*                    if(!mResultTextView.getText().equals("")){
                        mResultTextView.setText("");
                    }*/
                    String queryResults =response.getString("items");

                    JSONArray results = response.getJSONArray("items");

                    for(int i = 0; i < results.length(); i++){
                        JSONObject item = results.getJSONObject(i);
//                        mResultTextView.append(item.getString("full_name")+ "\n");
                    }

//                    mResultTextView.setText(queryResults);
                    Log.v("THE FINAL ANSWER", queryResults);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("ERROR", "Internet");
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseListner, errorListener);

        VolleyRequestManager.getmInstance(context).getmRequestQueue().add(jsonObjectRequest);
    }

    public void fetchRepoObject(final Context context, String url){

        Response.Listener<RepoObject> resultsListener = new Response.Listener<RepoObject>() {
            @Override
            public void onResponse(RepoObject response) {
/*                if(!mResultTextView.getText().equals("")){
                    mResultTextView.setText("");
                }*/
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                RepoObject repoObject = response;
                realm.insertOrUpdate(repoObject);
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

        RepoObjectRequest repoObjectRequest = new RepoObjectRequest(Request.Method.GET, url, null, resultsListener, errorListener);

        VolleyRequestManager.getmInstance(context).getmRequestQueue().add(repoObjectRequest);
    }

}
