package com.example.prf.Kminer.activities.showClusters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.example.prf.Kminer.R;
import com.example.prf.Kminer.models.ClusterSet;

public class ShowClustersActivity extends AppCompatActivity {

    private static final String TAG = "ShowClustersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showclusters);

        ClusterSet clusterSet = (ClusterSet) this.getIntent().getSerializableExtra("clusters");

        TextView discoveredTxt = findViewById(R.id.showclusters_txt_discovered);
        discoveredTxt.setText(Html.fromHtml(getString(R.string.discovered_text,clusterSet.getExamplesNumber(), clusterSet.getSize())));

        initRecyclerView(clusterSet);

    }


    private void initRecyclerView(ClusterSet clusters){

        ClustersRecyclerViewAdapter adapter = new ClustersRecyclerViewAdapter(clusters, getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.showclusters_recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
