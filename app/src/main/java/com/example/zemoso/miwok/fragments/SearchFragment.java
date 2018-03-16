package com.example.zemoso.miwok.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.adapters.SearchResultAdapter;
import com.example.zemoso.miwok.models.RepoObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    final static String SAMPLE_URL = "https://api.androidhive.info/volley/person_array.json";

    private List<RepoObject> repoObjectList;

    private EditText mEditSearchTextView;

    private SearchResultAdapter searchResultAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repoObjectList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment,container, false );
        //fetchRepoObject(getContext());
        /*mRepoUrlTextView = rootView.findViewById(R.id.repo_url_text_view);
        mRepoNameTextView = rootView.findViewById(R.id.repo_name_text_view);
        */
        ListView listView = (ListView) rootView.findViewById(R.id.search_result_list_view);

        searchResultAdapter = new SearchResultAdapter(getContext(), repoObjectList);

        listView.setAdapter(searchResultAdapter);

        mEditSearchTextView = rootView.findViewById(R.id.edit_search);
        setHasOptionsMenu(true);
        return rootView;
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        Log.v("MENU", "CREATED");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        if(itemSelected == R.id.action_search){
            String mSearchQuery = mEditSearchTextView.getText().toString();
            String toastMessage = "clicked Search";
            Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG).show();

            URL url = NetworkUtils.buildUrl(mSearchQuery);

            APIRepoResults.getInstance().fetchRepoObject(getContext(), url.toString());
            Realm realm = Realm.getDefaultInstance();

            repoObjectList.addAll(realm.where(RepoObject.class).findAll());

            RealmResults<RepoObject> results = realm.where(RepoObject.class).findAll();
            results.addChangeListener(new RealmChangeListener<RealmResults<RepoObject>>() {
                @Override
                public void onChange(RealmResults<RepoObject> repoObjects) {

                    Log.v("RESPONSE_Realm", String.valueOf(repoObjects.size()));
                    repoObjectList.clear();
                    repoObjectList.addAll(repoObjects);
                    searchResultAdapter.notifyDataSetChanged();
                }
            });
        }
        return true;
    }*/
}


//QUESTION 1 getting realm result late.