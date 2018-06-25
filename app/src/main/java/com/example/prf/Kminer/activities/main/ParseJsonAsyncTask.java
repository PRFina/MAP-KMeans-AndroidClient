package com.example.prf.Kminer.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.prf.Kminer.activities.clusters.ClustersActivity;
import com.example.prf.Kminer.models.ClusterSet;


public class ParseJsonAsyncTask extends AsyncTask<String, Void, ClusterSet> {

    private Context context;

    public ParseJsonAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected ClusterSet doInBackground(String... strings) {
        return new ClusterSet(strings[0]);
    }

    @Override
    protected void onPostExecute(ClusterSet clusterSet) {
        super.onPostExecute(clusterSet);

        Intent intent = new Intent(context, ClustersActivity.class);
        intent.putExtra("clusters", clusterSet);
        context.startActivity(intent);
    }
}
