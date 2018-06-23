package com.example.prf.Kminer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * This class model a set of clusters of data examples.
 * This POJO class has the main responsabilty of model (in MVC point of view)
 * <p>
 * The "major work" is done in the constructor, which parse an array of cluster encoded
 * in json string and build the associated object in memory.
 */
public class ClusterSet implements Serializable {
    private List<Cluster> clusters;
    private List<String> attributes;
    private int totalExamples;


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

    public Cluster getCluster(int i) {
        return clusters.get(i);
    }

    public int getSize() {
        return clusters.size();
    }

    public int getExamplesNumber() {
        return totalExamples;
    }

    public List<String> getAttributes(){return attributes; }


}
