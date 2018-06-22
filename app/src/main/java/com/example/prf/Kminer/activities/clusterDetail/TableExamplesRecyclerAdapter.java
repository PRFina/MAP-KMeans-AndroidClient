package com.example.prf.Kminer.activities.clusterDetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prf.Kminer.R;
import com.example.prf.Kminer.models.Cluster;

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
                .inflate(R.layout.activity_cluster_detail_table_item, parent, false);
        TableExamplesRecyclerAdapter.ViewHolder holder = new TableExamplesRecyclerAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.example.setText(cluster.getExamples().get(position));
        holder.distance.setText(String.format("%.2f", cluster.getExamplesDist().get(position)));
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