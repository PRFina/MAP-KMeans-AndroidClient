package it.kminer.activities.main.asyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import it.kminer.activities.clusters.ClustersActivity;
import it.kminer.models.ClusterSet;


public final class ParseJsonAsyncTask extends AsyncTask<String, Void, ClusterSet> {

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
