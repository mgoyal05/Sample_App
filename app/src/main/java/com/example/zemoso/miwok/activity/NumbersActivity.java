package com.example.zemoso.miwok.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.adapters.WordAdapter;
import com.example.zemoso.miwok.models.Word;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {


    ListView listView;

    Button mMainActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        listView = findViewById(R.id.list);
/*
        mMainActivityButton = findViewById(R.id.mainActivity);*/

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti"));
        words.add(new Word("two", "otiiko"));
        words.add(new Word("three", "tolookosu"));
        words.add(new Word("four", "oyyisa"));
        words.add(new Word("five", "massokka"));
        words.add(new Word("six", "temmokka"));
        words.add(new Word("seven", "kenekaku"));
        words.add(new Word("eight", "kawinta"));
        words.add(new Word("nine", "wo’e"));
        words.add(new Word("ten", "na’aacha"));


        WordAdapter adapter = new WordAdapter(this, words);

        listView.setAdapter(adapter);
/*
        mMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumbersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

    }
}
