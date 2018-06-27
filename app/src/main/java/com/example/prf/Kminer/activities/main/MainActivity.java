package com.example.prf.Kminer.activities.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.prf.Kminer.R;

import java.util.List;

/**
 * This is the Kminer's main activity.
 *
 * The logic is delegated to the relate Fragments
 */
public class MainActivity extends FragmentActivity {

    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabPageAdapter adapter = new TabPageAdapter(getSupportFragmentManager(),
                getFragments(),
                new String[] {"Discover","Read"});

        pager = findViewById(R.id.activity_main_pager);
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(pager);



    }

    /**
     * Helper method where instantiate the fragments for the tab layout
     */
    private Fragment[] getFragments() {
        // Add nefragments here
        Fragment[] fragmentsArray = new Fragment[]{
                new DiscoverFragment(),
                new ReadFragment()
        };

        return fragmentsArray;
    }
}
