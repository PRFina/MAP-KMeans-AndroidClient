package com.example.prf.Kminer.activities.main;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.prf.Kminer.R;
import com.example.prf.Kminer.activities.main.fragments.DiscoverFragment;
import com.example.prf.Kminer.activities.main.fragments.ReadFragment;


/**
 * This is the com.prfsc.Kminer's main activity.
 *
 * The logic is delegated to the relate Fragments
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ActivityMain";
    private ViewPager pager;
    private ConnectivityReceiver connectivityReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO);
        getSupportActionBar().setIcon(R.drawable.ic_home_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Set viewPager and TabLayout
        TabPageAdapter adapter = new TabPageAdapter(getSupportFragmentManager(),
                getFragments(),
                new String[] {"Discover","Read"});

        pager = findViewById(R.id.activity_main_pager);
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(pager);

        // Register receiver to listen network state changes
        connectivityReceiver = new ConnectivityReceiver(findViewById(R.id.activity_main_rootLayout));
        registerReceiver( connectivityReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        Log.d(TAG,"onCreate() called");

    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectivityReceiver);

        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(connectivityReceiver != null){
            registerReceiver( connectivityReceiver,
                    new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    /**
     * Helper method where instantiate the fragments for the tabPageAdapter
     */
    private Fragment[] getFragments() {
        // Add fragments here
        Fragment[] fragmentsArray = new Fragment[]{
                new DiscoverFragment(),
                new ReadFragment()
        };

        return fragmentsArray;
    }


}
