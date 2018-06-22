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
 *
 * The "major work" is done in the constructor, which parse a cluster encoded
 * in json string and build the associated object in memory.
 */
public class Cluster implements Serializable{
    private int nExamples;
    private List<String> centroid;
    private List<String> examples;
    private List<Double> examplesDist;
    private double avgDistance;

    Cluster(String jsonString){
        try {
            JSONObject cluster = (JSONObject) new JSONTokener(jsonString).nextValue();

            centroid = new ArrayList<>();
            JSONArray jCentroid = cluster.getJSONArray("centroid");
            for (int i = 0; i <  jCentroid.length(); i++) {
                centroid.add(jCentroid.getString(i));
            }



            avgDistance = cluster.getDouble("avg_distance");

            JSONArray jExamples = cluster.getJSONArray("examples");
            nExamples = jExamples.length();

            examples = new ArrayList<>();
            examplesDist = new ArrayList<>();
            for (int i = 0; i <  nExamples; i++) {

                examples.add(jExamples.getJSONObject(i)
                        .getJSONArray("values")
                        .join(", ")
                        .replace("\"",""));

                examplesDist.add(jExamples.getJSONObject(i)
                        .getDouble("distance"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return nExamples;
    }

    public String getJoinedCentroid() {
        return android.text.TextUtils.join(", ", centroid);
    }
    //TODO return list of string
    public List<String> getCentroid() {
        return centroid;
    }

    public List<String> getExamples() {
        return examples;
    }

    public List<Double> getExamplesDist() { return examplesDist; }



    public double getAvgDistance() {
        return avgDistance;
    }

}
