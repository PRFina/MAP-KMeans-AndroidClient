package it.kminer.activities.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPageAdapter extends FragmentPagerAdapter {
    private final String[] pagesTitles;
    private final Fragment[] fragments;

    TabPageAdapter(FragmentManager fragmentManager, Fragment[] fragments, String[] pagesTitles) {
        super(fragmentManager);
        this.pagesTitles = pagesTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return pagesTitles.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pagesTitles[position];
    }
}
