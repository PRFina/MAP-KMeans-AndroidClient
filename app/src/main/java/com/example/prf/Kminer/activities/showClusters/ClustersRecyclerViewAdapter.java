package com.example.prf.Kminer.activities.showClusters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prf.Kminer.R;
import com.example.prf.Kminer.models.Cluster;
import com.example.prf.Kminer.models.ClusterSet;

public class ClustersRecyclerViewAdapter extends RecyclerView.Adapter<ClustersRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "ClustersRecyclerViewAdapter";

    private ClusterSet clusters;
    private Resources resources;
    private Context context;


    /*
    TODO the json parsed data should be injected here
     */
    public ClustersRecyclerViewAdapter(ClusterSet clusters, Context ctx) {
        this.clusters = clusters;
        context = ctx;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_showclusters_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cluster cluster = clusters.getCluster(position);

        holder.clusterName.setText("Cluster #" + (position +1));

        int size = cluster.getSize();
        holder.examples.setText(context.getResources()
                .getQuantityString( R.plurals.number_of_examples, size, size));

        holder.centroid.setText(cluster.getJoinedCentroid());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "More info soon...", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return clusters.getSize();
    }


    /*
    The ui field declared in the layout, should be retrieved here,
    only the ones that should be modified dynamically( ui view that
    should be accessed in onBindViewHolder method)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout itemLayout;
        TextView clusterName;
        TextView examples;
        TextView centroid;

        public ViewHolder(View itemView) {
            super(itemView);

            itemLayout = itemView.findViewById(R.id.item_layout);
            clusterName = itemView.findViewById(R.id.cluster_name);
            examples = itemView.findViewById(R.id.example_number);
            centroid = itemView.findViewById(R.id.centroid);
        }
    }
}