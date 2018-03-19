package com.example.zemoso.miwok.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.models.RepoObject;

import java.util.List;

/**
 * Created by zemoso on 9/3/18.
 */

public class SearchResultAdapter extends BaseAdapter {

    List<RepoObject> repoObjectList;
    private Context mContext;

    public SearchResultAdapter(Context mContext, List<RepoObject> repoObjectList) {
        this.mContext = mContext;
        this.repoObjectList = repoObjectList;
    }

    @Override
    public int getCount() {
        return repoObjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
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

        if (repoObjectList.size() < position) {
            RepoObject currRepoObject = repoObjectList.get(position);

            mRepoNameTextView = listItemView.findViewById(R.id.repo_name_text_view);
            mRepoUrlTextView = listItemView.findViewById(R.id.repo_url_text_view);

            mRepoNameTextView.setText(currRepoObject.getFullName());
            mRepoUrlTextView.setText(currRepoObject.getHtmlUrl());
        }
        return listItemView;
    }
}
