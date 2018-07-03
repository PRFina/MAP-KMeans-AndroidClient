package com.example.prf.Kminer.models;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * This class models a single data example with additional distance information.
 * An example contains:
 * <ul>
 *     <li> A list of examples values </li>
 *     <li> the distance between the example and the cluster's
 *     centroid where example is contained</li>
 * </ul>
 *
 * From a MVC stand point, this class has the Model role.
 *
 */
public class Example implements Serializable, Comparable<Example>{
    String distance;
    List<String> values;

    /**
     * Constructs a new example instance from a json string.
     * The json string must contains <b>one, and only one</b>
     * example to be parsed correctly.
     *
     * @param jsonString top-level object in the json string
     */
    Example(String jsonString) throws JSONException {

        values = new ArrayList<>();

        JSONObject jExample = (JSONObject) new JSONTokener(jsonString).nextValue();
        JSONArray jValues = jExample.getJSONArray("values");

        for (int i = 0; i < jValues.length(); i++) {
            values.add(jValues.getString(i));
        }

        distance = String.format(java.util.Locale.US,"%.2f", jExample.getDouble("distance"));
    }

    /**
     *
     * @return the distance between the current example and the cluster's centroid.
     */
    public String getDistance() {
        return distance;
    }

    /**
     *
     * @return the example values
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * Utility method to retrieve example values as unique string.
     * The example values are joined by a comma.
     * @return a comma separated value string of the centroid values
     */
    public String getJoinedValues() {
        return android.text.TextUtils.join("  ", values);
    }

    @Override
    public int compareTo(@NonNull Example example) {
        return distance.compareTo(example.distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return Objects.equals(distance, example.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance);
    }
}
