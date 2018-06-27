package com.example.prf.Kminer.activities.clusterInfo;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.prf.Kminer.R;
import com.example.prf.Kminer.models.Cluster;


import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ClusterInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_actionBar_clusterInfo);


        Intent intent = this.getIntent();
        Cluster cluster = (Cluster) intent.getSerializableExtra("cluster");
        List<String> attributes = (List<String>) intent.getSerializableExtra("attributes");


        //Cluster number text
        TextView clusterName = findViewById(R.id.activity_cluster_detail_txt_cname);
        clusterName.setText(getString(R.string.cluster, intent.getIntExtra("position", -1 )+1));

        // Header list
        RecyclerView tableHeaderRecyclerView = findViewById(R.id.activity_cluster_detail_recyclerView_tableHeader);
        TableHeaderRecyclerViewAdapter attrAdapter = new TableHeaderRecyclerViewAdapter(this,
                attributes,
                cluster.getCentroid());
        tableHeaderRecyclerView.setHasFixedSize(true);
        tableHeaderRecyclerView.setAdapter(attrAdapter);

        // Examples list
        Collections.sort(cluster.getExamples());
        RecyclerView tableExamplesRecyclerView = findViewById(R.id.activity_cluster_detail_recyclerView_tableExamples);
        TableExamplesRecyclerAdapter examplesAdapter = new TableExamplesRecyclerAdapter(this,
                cluster);
        tableExamplesRecyclerView.setHasFixedSize(true);
        tableExamplesRecyclerView.setAdapter(examplesAdapter);

        // Avg distance text
        TextView avgDistance = findViewById(R.id.activity_cluster_detail_txt_avgDistance);
        avgDistance.setText( String.format(Locale.US,"%.2f", cluster.getAvgDistance()) );

    }
}
