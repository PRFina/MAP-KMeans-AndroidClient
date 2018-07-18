package it.kminer.activities.main;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import it.kminer.R;
import it.kminer.activities.main.fragments.DiscoverFragment;
import it.kminer.activities.main.fragments.ReadFragment;
import it.kminer.activities.settings.SettingsActivity;


/**
 * Activity for showing the main UI.
 * <p>
 * This activity delegates the working logic to the fragments contained in
 * {@link it.kminer.activities.main.fragments}.
 * The viewPager and tab layout component has been used to
 * display fragments like "swipeable-pages".
 * </p>
 * <p>
 *    This activity use a BroadcastReceiver component to check network connection
 *    status before to begin any client-server communication.
 * </p>
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ActivityMain";
    private ViewPager pager;
    private BroadcastReceiver connectivityReceiver;

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

        // Start settings activity
        Button settingsBtn = findViewById(R.id.activity_main_btn_settings);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
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
