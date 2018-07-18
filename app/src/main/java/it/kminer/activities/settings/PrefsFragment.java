package it.kminer.activities.settings;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import android.support.v4.app.Fragment;

import it.kminer.R;

/**
 * This fragment extends PreferenceFragment to
 * automatically handle app preferences.
 */
public class PrefsFragment extends PreferenceFragment {


    public PrefsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
