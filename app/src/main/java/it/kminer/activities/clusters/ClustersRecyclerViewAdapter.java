package it.kminer.activities.clusters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.kminer.R;
import it.kminer.activities.clusterInfo.ClusterInfoActivity;
import it.kminer.models.Cluster;
import it.kminer.models.ClusterSet;

import java.io.Serializable;

/**
 * Class that extends and implement the RecyclerView adapter.
 * This class helps to bind {@link it.kminer.models.ClusterSet}
 * model to the inflated UI list provided by RecyclerView.
 *<p>
 * For each item in the clusters' list, an OnClickListener is attached to it, so
 * when user click on it new
 * {@link it.kminer.activities.clusterInfo.ClusterInfoActivity}
 * will be started with an intent that contains as bundle a single
 * {@link it.kminer.models.Cluster} instance.
 *</p>
 */
public final class ClustersRecyclerViewAdapter extends RecyclerView.Adapter<ClustersRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "ClustersRecyclerViewAdapter";

    private ClusterSet clusters;
    private Context context;

    public ClustersRecyclerViewAdapter(ClusterSet clusters, Context ctx) {
        this.clusters = clusters;
        context = ctx;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_clusters_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Cluster cluster = clusters.getCluster(position);

        holder.clusterName.setText("Cluster #" + (position +1));

        int size = cluster.getSize();
        holder.examples.setText(context.getResources()
                .getQuantityString( R.plurals.number_of_examples, size, size));
        holder.centroid.setText(cluster.getJoinedCentroid());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClusterInfoActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("cluster", cluster);
                intent.putExtra("attributes", (Serializable) clusters.getAttributes());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clusters.getSize();
    }


    /*
    The ui field declared in the layout, should be retrieved here,
    only the ones that should be modified dynamically (ui views that
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
