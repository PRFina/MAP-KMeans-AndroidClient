package it.kminer.activities.main.asyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import it.kminer.activities.clusters.ClustersActivity;
import it.kminer.models.ClusterSet;

/**
 * This class define an async task to parse data received from server
 * and build a {@link it.kminer.models.ClusterSet} instance.
 */
public final class ParseJsonAsyncTask extends AsyncTask<String, Void, ClusterSet> {

    private Context context;

    public ParseJsonAsyncTask(Context context) {
        this.context = context;
    }

    /**
     * Build a {@link it.kminer.models.ClusterSet} instance.
     * @param strings json data retrieved from server
     * @return the {@link it.kminer.models.ClusterSet} instance.
     */
    @Override
    protected ClusterSet doInBackground(String... strings) {
        return new ClusterSet(strings[0]);
    }

    /**
     * Start a {@link it.kminer.activities.clusters.ClustersActivity} activity
     * with the builded ClusterSet as intent extra
     * @param clusterSet the clusterSet instance needed to start
     * {@link it.kminer.activities.clusters.ClustersActivity} activity
     */
    @Override
    protected void onPostExecute(ClusterSet clusterSet) {
        super.onPostExecute(clusterSet);

        Intent intent = new Intent(context, ClustersActivity.class);
        intent.putExtra("clusters", clusterSet);
        context.startActivity(intent);
    }
}
