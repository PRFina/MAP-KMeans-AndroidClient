package it.kminer.activities.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.kminer.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.title_actionBar_settings);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefsFragment())
                .commit();

    }

}
