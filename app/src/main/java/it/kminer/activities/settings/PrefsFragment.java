package it.kminer.activities.settings;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import android.support.v4.app.Fragment;

import it.kminer.R;

/**
 * A simple {@link Fragment} subclass.
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
