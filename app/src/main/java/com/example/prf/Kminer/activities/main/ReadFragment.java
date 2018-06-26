package com.example.prf.Kminer.activities.main;

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

import com.example.prf.Kminer.R;



public class ReadFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

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
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
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
