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
 * wants to retrieve a stored KMiner instance on server.
 * When user click on the button a new
 * {@link it.kminer.activities.main.asyncTasks}
 * is started to send ui values to the server.
 *
 */
public class ReadFragment extends Fragment {
    // UI
    private View view;
    private TextView tableTxt;
    private TextView clustersTxt;
    private Button readBtn;

    public ReadFragment() {
        // Required empty public constructor
    }

    public static ReadFragment newInstance(String param1) {
       ReadFragment fragment = new ReadFragment();
       return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_read, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readBtn = view.findViewById(R.id.fragment_read_btn_read);
        tableTxt = view.findViewById(R.id.fragment_read_txt_table);
        clustersTxt = view.findViewById(R.id.fragment_read_txt_clusters);

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if( fieldsAreEmpty()){
                Snackbar.make(ReadFragment.this.view,"Please provide table name and clusters count", Snackbar.LENGTH_LONG).show();
            } else {
                FetchDataAsyncTask task = new FetchDataAsyncTask(getActivity(), view);
                task.execute("READ", tableTxt.getText().toString(), clustersTxt.getText().toString());
            }
            }
        });
    }

    private boolean fieldsAreEmpty(){
        return tableTxt.getText().toString().isEmpty() ||
                clustersTxt.getText().toString().isEmpty();

    }
}
