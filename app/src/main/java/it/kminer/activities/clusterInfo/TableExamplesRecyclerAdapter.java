package it.kminer.activities.clusterInfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.kminer.R;
import it.kminer.models.Cluster;
import it.kminer.models.Example;

public class TableExamplesRecyclerAdapter extends RecyclerView.Adapter<TableExamplesRecyclerAdapter.ViewHolder> {

    private Context context;
    private Cluster cluster;

    public TableExamplesRecyclerAdapter(Context context, Cluster cluster){
        this.context = context;
        this.cluster = cluster;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cluster_table_item, parent, false);
        TableExamplesRecyclerAdapter.ViewHolder holder = new TableExamplesRecyclerAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Example example = cluster.getExamples().get(position);
        holder.example.setText(example.getJoinedValues());
        holder.distance.setText(example.getDistance());
    }

    @Override
    public int getItemCount() {
        return cluster.getExamples().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView example;
        private TextView distance;

        public ViewHolder(View itemView) {
            super(itemView);
            example = itemView.findViewById(R.id.activity_cluster_detail_table_example_item_txt_example);
            distance = itemView.findViewById(R.id.activity_cluster_detail_table_example_item_txt_distance);

        }
    }
}
