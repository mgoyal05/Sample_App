package com.example.zemoso.miwok.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.models.RepoObject;
import com.example.zemoso.miwok.utils.RepoObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    final static String GITHUB_BASE_URL =
            "https://api.github.com/search/repositories";

    final static String PARAM_QUERY = "q";

    final static String SAMPLE_URL = "https://api.github.com/search/repositories?q=android&sort=stars";
    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT = "sort";



    TextView mResultTextView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment,container, false );
        fetchdata(getContext());
        mResultTextView = rootView.findViewById(R.id.tv_url_display1);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
    }

    public void fetchdata(final Context context){

        Response.Listener<JSONObject> responseListner = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String s =response.getString("items");
                    mResultTextView.setText(s);
                    Log.v("THE FINAL ANSWER", s);
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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, SAMPLE_URL, null, responseListner, errorListener);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonObjectRequest);
    }

    public void fetchRepoData(final Context context, String url){
        Response.Listener<RepoObject> responseListner = new Response.Listener<RepoObject>() {
            @Override
            public void onResponse(RepoObject response) {
                try {
                    String s =response.getFullName();
                    mResultTextView.setText(s);
                    Log.v("THE FINAL ANSWER", s);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("ERROR", "Internet");
            }
        };

        RepoObjectRequest repoObjectRequest = new JsonObjectRequest(Request.Method.GET, SAMPLE_URL, null, responseListner, errorListener);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(repoObjectRequest);
    }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        if(itemSelected == R.id.action_search){
            String toastMessage = "clicked Search";
            Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG).show();
            fetchdata(getContext());
        }


        return true;
    }
}
