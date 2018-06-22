package com.example.prf.Kminer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * This class model a set of clusters of data examples.
 * This POJO class has the main responsabilty of model (in MVC point of view)
 *
 * The "major work" is done in the constructor, which parse an array of cluster encoded
 * in json string and build the associated object in memory.
 */
public class ClusterSet implements Serializable{
    private List<Cluster> clusters;
    private int totalExamples;


    public ClusterSet(String jsonString){
        clusters = new ArrayList<>();
        totalExamples = 0;
        try {
            JSONArray jClusters = (JSONArray) new JSONTokener(jsonString).nextValue();

            for (int i = 0; i < jClusters.length(); i++) {
                Cluster c = new Cluster(jClusters.getJSONObject(i).toString());
                clusters.add(c);
                totalExamples += c.getSize();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Cluster getCluster(int i) {return clusters.get(i);}

    public int getSize() {return clusters.size();}

    public int getExamplesNumber() { return totalExamples;}


}
