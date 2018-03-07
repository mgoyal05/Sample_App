package com.example.zemoso.miwok.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.models.Word;

import java.util.ArrayList;

/**
 * Created by zemoso on 27/2/18.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    TextView mMiwokTextView;
    TextView mDefaultTextView;
    TextView mPositionTextView;

    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context,0, words);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Word currentWord = getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        mMiwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        mMiwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        mDefaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        mDefaultTextView.setText(currentWord.getDefaultTranslation());

        mPositionTextView = listItemView.findViewById(R.id.position_text_view);

        String viewNumber = String.valueOf(position);

        mPositionTextView.setText(viewNumber);

        Log.v(String.valueOf(position), "String");

        return listItemView;
    }
}
