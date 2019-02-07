package com.nothing.lastnewsv4.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.nothing.lastnewsv4.fragment.FaveNewsFragment;
import com.nothing.lastnewsv4.fragment.LastNewsFragment;
import com.nothing.lastnewsv4.fragment.NewsFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        if (position == 0) {
            fragment = new LastNewsFragment();
        } else if (position == 1) {
            fragment = new NewsFragment();


        } else if (position == 2) {
            fragment = new FaveNewsFragment();

        } else {
            fragment = new LastNewsFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}