package com.example.prf.Kminer.activities.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPageAdapter extends FragmentPagerAdapter {

    TabPageAdapter(FragmentManager fragMng) {
        super(fragMng);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: {
                fragment = new DiscoverFragment();
                break;
            }
            case 1: {
                fragment = new ReadFragment();
                break;
            }
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";

        switch (position){
            case 0: {
                title = "Discover";
                break;
            }
            case 1: {
                title = "Read";
                break;
            }
        }

        return title;
    }
}
