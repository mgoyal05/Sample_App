package com.example.zemoso.miwok.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.models.JsonResultSearchObject;

import java.util.List;

/**
 * Created by zemoso on 15/3/18.
 */

public class JsonResultSearchAdapter extends ArrayAdapter<JsonResultSearchObject> {

    List<JsonResultSearchObject> jsonResultSearchObjectList;
    private Context mContext;

    public JsonResultSearchAdapter(@NonNull Context context, int resource, @NonNull List<JsonResultSearchObject> objects) {
        super(context, resource, objects);
        mContext = context;
        jsonResultSearchObjectList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        TextView mRepoNameTextView;
        TextView mRepoUrlTextView;
        if (convertView == null) {
            // If the view is not recycled, this creates a new ImageView to hold an image
            listItemView = LayoutInflater.from(mContext).inflate(
                    R.layout.search_result_list_item, parent, false);

        }

        Log.v("ADAPTEROUTSIDE" + jsonResultSearchObjectList.size(), String.valueOf(position));

        JsonResultSearchObject jsonResultSearchObject = jsonResultSearchObjectList.get(position);

        Log.v("ADAPTER" + jsonResultSearchObject.getEmail(), String.valueOf(position));

        mRepoNameTextView = listItemView.findViewById(R.id.repo_name_text_view);
        mRepoUrlTextView = listItemView.findViewById(R.id.repo_url_text_view);

        mRepoNameTextView.setText(jsonResultSearchObject.getName());
        mRepoUrlTextView.setText(jsonResultSearchObject.getEmail());

        return listItemView;
    }
}
