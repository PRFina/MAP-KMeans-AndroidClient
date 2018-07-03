package com.example.prf.Kminer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a collection of data {@link com.example.prf.Kminer.models.Example}.
 * In this context,a cluster contains:
 * <ul>
 *     <li> A list of data examples <li>
 *     <li> The cluster's centroid</li>
 *     <li> Some metrics about examples-centroid distance</li>
 * </ul>
 *
 * From a MVC stand point, this class has the Model role.
 *
 */
public class Cluster implements Serializable {
    private List<String> centroid;
    private List<Example> examples;
    private double avgDistance;

    /**
     * Constructs a new cluster instance from a json string.
     * The json string must contains <b>one, and only one</b>
     * cluster to be parsed correctly.
     *
     * @param jsonString cluster json encoded string
     */
    Cluster(String jsonString) {
        centroid = new ArrayList<>();
        examples = new ArrayList<>();

        try {
            JSONObject jCluster = (JSONObject) new JSONTokener(jsonString).nextValue();

            JSONArray jCentroid = jCluster.getJSONArray("centroid");
            for (int i = 0; i < jCentroid.length(); i++) {
                centroid.add(jCentroid.getString(i));
            }

            JSONArray jExamples = jCluster.getJSONArray("examples");
            for (int i = 0; i < jExamples.length(); i++) {
                examples.add(new Example(jExamples.getJSONObject(i).toString()));
            }


            avgDistance = jCluster.getDouble("avg_distance");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the cluster size (in O(1) time)
     * @return number of data examples in the current cluster
     */
    public int getSize() {
        return examples.size();
    }

    /**
     * Utility method to retrieve centroid values as unique string.
     * The centroid values are joined by a comma.
     * @return a comma separated value string of the centroid values
     */
    public String getJoinedCentroid() {
        return android.text.TextUtils.join("  ", centroid);
    }

    /**
     *
     * @return the centroid values
     */
    public List<String> getCentroid() {
        return centroid;
    }

    /**
     *
     * @return the cluster' examples
     */
    public List<Example> getExamples() {
        return examples;
    }

    /**
     *
     * @return the average distance from the centroid
     */
    public double getAvgDistance() {
        return avgDistance;
    }

}
