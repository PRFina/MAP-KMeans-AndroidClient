package it.kminer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a collection of {@link it.kminer.models.Cluster}.
 * A cluster-set contains:
 * <ul>
 *     <li> A list of cluster <li>
 *     <li> The centroid and cluster data examples attributes name</li>
 *     <li> Total number of examples</li>
 * </ul>
 *
 * From a MVC stand point, this class has the Model role.
 *
 */
public class ClusterSet implements Serializable {
    private List<Cluster> clusters;
    private List<String> attributes;
    private int totalExamples;


    /**
     * Constructs a new cluster instance from a json string.
     * The json string must contains the <b>top-level</b> json object
     * in the hierarchy retrieved from server'
     * responseMessage to be parsed correctly.
     *
     * @param jsonString top-level object in the json string
     */
    public ClusterSet(String jsonString) {
        clusters = new ArrayList<>();
        attributes = new ArrayList<>();
        totalExamples = 0;
        try {

            JSONObject root = (JSONObject) new JSONTokener(jsonString).nextValue();

            //Deserialize Attributes
            JSONArray jAttributes = root.getJSONArray("table_header");
            for (int i = 0; i < jAttributes.length(); i++) {
                attributes.add(jAttributes.getString(i));
            }

            //Deserialize Clusters
            JSONArray jClusters = root.getJSONArray("clusters");
            for (int i = 0; i < jClusters.length(); i++) {
                //get each json cluster as string because will be parsed in the constructor
                Cluster c = new Cluster(jClusters.getJSONObject(i).toString());
                clusters.add(c);
                totalExamples += c.getSize();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param i the index of the i-th cluster
     * @return the i-th cluster instance.
     */
    public Cluster getCluster(int i) {
        return clusters.get(i);
    }

    /**
     *
     * @return the number of clusters in the cluster-set
     */
    public int getSize() {
        return clusters.size();
    }

    /**
     *
     * @return the sum of the examples in each cluster
     */
    public int getExamplesNumber() {
        return totalExamples;
    }

    /**
     *
     * @return the list of the attributes' name
     */
    public List<String> getAttributes(){return attributes; }


}
