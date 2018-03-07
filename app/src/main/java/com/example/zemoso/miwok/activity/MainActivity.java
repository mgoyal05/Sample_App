package com.example.zemoso.miwok.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zemoso.miwok.R;
import com.example.zemoso.miwok.adapters.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

    TextView mColorsTextView;
    TextView mNumbersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);

        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
    }
}
