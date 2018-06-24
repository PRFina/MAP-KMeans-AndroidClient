package com.example.prf.Kminer.activities.clusterInfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prf.Kminer.R;

import java.util.List;

public class TableHeaderRecyclerViewAdapter extends RecyclerView.Adapter<TableHeaderRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<String> attributes;
    private List<String> centroid;


    public TableHeaderRecyclerViewAdapter(Context context, List<String> attributes, List<String> centroid){

        this.context = context;
        this.attributes = attributes;
        this.centroid = centroid;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cluster_header_item, parent, false);
        TableHeaderRecyclerViewAdapter.ViewHolder holder = new TableHeaderRecyclerViewAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.attributeName.setText(attributes.get(position));
        holder.centroid.setText(centroid.get(position));

    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    //TODO check if private or package visibility is ok!
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView attributeName;
        private TextView centroid;

        public ViewHolder(View itemView) {
            super(itemView);
            attributeName = itemView.findViewById(R.id.activity_cluster_detail_txt_attribute);
            centroid = itemView.findViewById(R.id.activity_cluster_detail_txt_centroid);


        }
    }
}
