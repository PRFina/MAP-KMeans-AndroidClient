package it.kminer.activities.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import it.kminer.R;
import it.kminer.activities.main.asyncTasks.FetchDataAsyncTask;

/**
 * This fragment is used to handle the UI views when user
 * wants to run a new KMiner instance on a stored data-set.
 * When user click on the button a new
 * {@link it.kminer.activities.main.asyncTasks}
 * is started to send ui values to the server.
 *
 */
public class DiscoverFragment extends Fragment {

    // UI
    private View view;
    private TextView tableTxt;
    private TextView clustersTxt;
    private Button mineBtn;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    public static DiscoverFragment newInstance(String param1) {
        DiscoverFragment fragment = new DiscoverFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discover, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mineBtn = view.findViewById(R.id.fragment_discover_btn_mine);
        tableTxt = view.findViewById(R.id.fragment_discover_txt_table);
        clustersTxt = view.findViewById(R.id.fragment_discover_txt_clusters);

        mineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( fieldsAreEmpty()){
                    Snackbar.make(DiscoverFragment.this.view,"Please provide table name and clusters count", Snackbar.LENGTH_LONG).show();
                } else {
                    FetchDataAsyncTask task = new FetchDataAsyncTask(getActivity(), view);
                    task.execute("DISCOVER", tableTxt.getText().toString(), clustersTxt.getText().toString());
                }
            }
        });
    }

    /**
     * helper method to validate ui fields
     * @return true if one of the two fields are empty
     */
    private boolean fieldsAreEmpty(){
        return tableTxt.getText().toString().isEmpty() ||
                clustersTxt.getText().toString().isEmpty();

    }
}
