package com.example.prf.Kminer.activities.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prf.Kminer.R;

public class DiscoverFragment extends Fragment {
    // TODO: clean useless attributes
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private View view;

    // UI
    private TextView tableTxt;
    private TextView clustersTxt;
    private Button mineBtn;





    public DiscoverFragment() {
        // Required empty public constructor
    }


    public static DiscoverFragment newInstance(String param1) {
        DiscoverFragment fragment = new DiscoverFragment();
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

    private boolean fieldsAreEmpty(){
        return tableTxt.getText().toString().isEmpty() ||
                clustersTxt.getText().toString().isEmpty();

    }
}
