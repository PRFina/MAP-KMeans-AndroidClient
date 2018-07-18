package it.kminer.activities.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.kminer.R;

/**
 * This activity is used to show app settings to user.
 * The ui working logic is delegated to {
 * @link it.kminer.activities.settings.PrefsFragment}
 */
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
