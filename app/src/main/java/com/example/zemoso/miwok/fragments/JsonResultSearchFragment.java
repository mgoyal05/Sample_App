package com.example.zemoso.miwok.fragments;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.adapters.JsonResultSearchAdapter;
import com.example.zemoso.miwok.models.JsonResultSearchObject;
import com.example.zemoso.miwok.utils.APIJsonResults;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class JsonResultSearchFragment extends Fragment {

    final static String SAMPLE_URL = "https://jsonplaceholder.typicode.com/users";

    private List<JsonResultSearchObject> jsonResultSearchObjectList;

    private JsonResultSearchAdapter jsonResultSearchAdapter;

    private Button mButton;

    private ListView listView;

    public JsonResultSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonResultSearchObjectList = new ArrayList<>();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getContext().SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        // Assumes current activity is the searchable activity
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("SEARCHTEXT", newText);
                Realm realm = Realm.getDefaultInstance();

                RealmResults<JsonResultSearchObject> results;
                results = realm.where(JsonResultSearchObject.class).beginsWith("name", newText, Case.INSENSITIVE).or().findAll();

                jsonResultSearchAdapter.notifyDataSetChanged();
                JsonResultSearchAdapter jsonResultSearchAdapter1 = new JsonResultSearchAdapter(getContext(), R.color.category_family, results);

                listView.setAdapter(jsonResultSearchAdapter1);
                Log.v(String.valueOf(results.size()), "SET ADAPTER");

                return true;
            }
        });

        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        if (itemSelected == R.id.search_bar) {
            getActivity().onSearchRequested();
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_json_result_search, container, false);
        listView = (ListView) rootView.findViewById(R.id.search_result_list_view);

        mButton = rootView.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("BUTTON", "CLICKED");
                APIJsonResults.getInstance().fetchJsonResultRequest(getContext(), SAMPLE_URL);
                Realm realm = Realm.getDefaultInstance();

                RealmResults<JsonResultSearchObject> results = realm.where(JsonResultSearchObject.class).findAll();

                results.addChangeListener(new RealmChangeListener<RealmResults<JsonResultSearchObject>>() {
                    @Override
                    public void onChange(RealmResults<JsonResultSearchObject> jsonResultSearchObjects) {
                        Log.v("RESPONSE_Realm", String.valueOf(jsonResultSearchObjects.size()));
                        jsonResultSearchObjectList.addAll(jsonResultSearchObjects);

                        jsonResultSearchAdapter = new JsonResultSearchAdapter(getContext(), R.color.category_family, jsonResultSearchObjectList);

                        listView.setAdapter(jsonResultSearchAdapter);
                        Log.v("SIZE_INSIDE", String.valueOf(jsonResultSearchObjectList.size()));
                    }
                });

                Log.v("SIZE", String.valueOf(jsonResultSearchObjectList.size()));

            }
        });


        setHasOptionsMenu(true);

        return rootView;
    }
}
