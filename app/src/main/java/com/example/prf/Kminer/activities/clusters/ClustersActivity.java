package com.example.prf.Kminer.activities.clusters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.example.prf.Kminer.R;
import com.example.prf.Kminer.models.ClusterSet;

/**
 * Activity for showing the cluster-set.
 *
 * This activity show a list of clusters using a RecyclerView.
 * This activity must receive a {@link com.example.prf.Kminer.models.ClusterSet}
 * instance in the incoming intent Bundle
 */
public class ClustersActivity extends AppCompatActivity {

    private static final String TAG = "ClustersActivity";

    /**
     * Framework method called on create lifecycle state to setup the UI.
     *
     * @param savedInstanceState previous saved bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clusters);

        ClusterSet clusterSet = (ClusterSet) this.getIntent().getSerializableExtra("clusters");
        TextView discoveredTxt = findViewById(R.id.showclusters_txt_discovered);

        if(clusterSet != null) {
           discoveredTxt.setText(Html.fromHtml(getString(R.string.discovered_text, clusterSet.getExamplesNumber(), clusterSet.getSize())));
           initRecyclerView(clusterSet);
        } else {
            discoveredTxt.setText("No cluster set available!");
        }

        getSupportActionBar().setTitle(R.string.title_actionBar_clusters);
    }

    /**
     * Utility method to setup the RecyclerView and inject the clusterset into the RecyclerView adapter
     * @param clusters
     */
    private void initRecyclerView(ClusterSet clusters){

        ClustersRecyclerViewAdapter adapter = new ClustersRecyclerViewAdapter(clusters, this);

        RecyclerView recyclerView = findViewById(R.id.showclusters_recycler_view);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
