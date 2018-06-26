package com.example.prf.Kminer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class model a cluster of data examples.
 * This POJO class has the main responsabilty of model (in MVC point of view)
 * <p>
 * The "major work" is done in the constructor, which parse a cluster encoded
 * in json string and build the associated object in memory.
 */
public class Cluster implements Serializable {
    private int nExamples;
    private List<String> centroid;
    private List<Example> examples;
    private double avgDistance;

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
            nExamples = examples.size();

            avgDistance = jCluster.getDouble("avg_distance");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return nExamples;
    }

    public String getJoinedCentroid() {
        return android.text.TextUtils.join("  ", centroid);
    }

    //TODO return list of string
    public List<String> getCentroid() {
        return centroid;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public double getAvgDistance() {
        return avgDistance;
    }

}
