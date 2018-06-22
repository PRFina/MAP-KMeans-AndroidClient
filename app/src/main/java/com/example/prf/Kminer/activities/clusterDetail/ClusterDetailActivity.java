package com.example.prf.Kminer.activities.clusterDetail;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.example.prf.Kminer.MockJsonData;
import com.example.prf.Kminer.R;
import com.example.prf.Kminer.models.Cluster;
import com.example.prf.Kminer.models.ClusterSet;

public class ClusterDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster_detail);


        Intent intent = this.getIntent();
        Cluster cluster = (Cluster) intent.getSerializableExtra("cluster");

        //ClusterSet clusterSet = MockJsonData.getParsedClusterSet();



        //Cluster number text
        TextView clusterName = findViewById(R.id.activity_cluster_detail_txt_cname);
        clusterName.setText(getString(R.string.cluster, intent.getIntExtra("position", -1 )+1));

        // Header list
        RecyclerView tableHeaderRecyclerView = findViewById(R.id.activity_cluster_detail_recyclerView_tableHeader);
        TableHeaderRecyclerViewAdapter attrAdapter = new TableHeaderRecyclerViewAdapter(getApplicationContext(),
                MockJsonData.getParsedAttribute(),
                cluster.getCentroid());
        tableHeaderRecyclerView.setAdapter(attrAdapter);

        // Examples list
        RecyclerView tableExamplesRecyclerView = findViewById(R.id.activity_cluster_detail_recyclerView_tableExamples);
        TableExamplesRecyclerAdapter examplesAdapter = new TableExamplesRecyclerAdapter(getApplicationContext(),
                cluster);
        tableExamplesRecyclerView.setAdapter(examplesAdapter);

        // Avg distance text
        TextView avgDistance = findViewById(R.id.activity_cluster_detail_txt_avgDistance);
        avgDistance.setText( String.format("%.2f", cluster.getAvgDistance()) );

    }
}
