package com.example.zemoso.miwok.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.models.RepoObject;
import com.example.zemoso.miwok.utils.APIRepoResults;
import com.example.zemoso.miwok.utils.NetworkUtils;

import java.net.URL;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    final static String SAMPLE_URL = "https://api.androidhive.info/volley/person_array.json";

    private TextView mResultTextView;

    private EditText mEditSearchTextView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment,container, false );
        //fetchRepoObject(getContext());
        mResultTextView = rootView.findViewById(R.id.tv_url_display1);
        mEditSearchTextView = rootView.findViewById(R.id.edit_search);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        if(itemSelected == R.id.action_search){
            String mSearchQuery = mEditSearchTextView.getText().toString();
            String toastMessage = "clicked Search";
            Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG).show();

            URL url = NetworkUtils.buildUrl(mSearchQuery);
            /*RepoObjectRequest repoObjectRequest = fetchRepoObject(getContext(), url.toString());
            JsonObjectRequest jsonObjectRequest = fetchJSONObject(getContext(), url.toString());
*/            //initialising volley

            APIRepoResults.getInstance().fetchRepoObject(getContext(), url.toString());
            Realm realm = Realm.getDefaultInstance();
            RealmResults<RepoObject> s = realm.where(RepoObject.class).findAll();
            Log.v("RESPONSEOB", String.valueOf(s.size()));
            mResultTextView.setText(s.size() + s.get(s.size() - 1).getHtmlUrl());
        }
        return true;
    }
}
