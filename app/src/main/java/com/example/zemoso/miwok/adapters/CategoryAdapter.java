package com.example.zemoso.miwok.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zemoso.miwok.fragments.ColorsFragment;
import com.example.zemoso.miwok.fragments.NumbersFragment;
import com.example.zemoso.miwok.fragments.SearchFragment;

/**
 * Created by zemoso on 28/2/18.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NumbersFragment();
            case 1:
                return new ColorsFragment();
            case 2:
                return new SearchFragment();/*
            case 3:
                return new GMapFragment();*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Numbers";
            case 1:
                return "Colors";
            case 2:
                return "GitQuery";/*
            case 3:
                return "Map";*/
        }

        return super.getPageTitle(position);
    }
}
