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
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    final static String SAMPLE_URL = "https://api.androidhive.info/volley/person_array.json";

    private TextView mRepoUrlTextView;

    private TextView mRepoNameTextView;

    private EditText mEditSearchTextView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment,container, false );
        //fetchRepoObject(getContext());
        mRepoUrlTextView = rootView.findViewById(R.id.repo_url_text_view);
        mRepoNameTextView = rootView.findViewById(R.id.repo_name_text_view);
        mEditSearchTextView = rootView.findViewById(R.id.edit_search);
        setHasOptionsMenu(true);
        Log.v("SEARCH FRAGMENT", "main");
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        Log.v("MENU", "CREATED");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        Log.v("SEARCH FRAGMENT", item.toString());

        if(itemSelected == R.id.action_search){
            String mSearchQuery = mEditSearchTextView.getText().toString();
            String toastMessage = "clicked Search";
            Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG).show();

            URL url = NetworkUtils.buildUrl(mSearchQuery);

            APIRepoResults.getInstance().fetchRepoObject(getContext(), url.toString());
            Realm realm = Realm.getDefaultInstance();
            RealmResults<RepoObject> results = realm.where(RepoObject.class).findAll();
            results.addChangeListener(new RealmChangeListener<RealmResults<RepoObject>>() {
                @Override
                public void onChange(RealmResults<RepoObject> repoObjects) {

                    Log.v("RESPONSE_Realm", String.valueOf(repoObjects.size()));
                    if (repoObjects.size() > 0) {
                        mRepoNameTextView.setText(repoObjects.get(repoObjects.size() - 1).getFullName());
                        mRepoUrlTextView.setText(repoObjects.get(0).getHtmlUrl());
                    }
                }
            });
        }
        return true;
    }
}


//QUESTION 1 getting realm result late.