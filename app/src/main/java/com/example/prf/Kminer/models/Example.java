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

public class Example implements Serializable, Comparable<Example>{
    String distance;
    List<String> values;



    Example(String jsonString) throws JSONException {

        values = new ArrayList<>();

        JSONObject jExample = (JSONObject) new JSONTokener(jsonString).nextValue();
        JSONArray jValues = jExample.getJSONArray("values");

        for (int i = 0; i < jValues.length(); i++) {
            values.add(jValues.getString(i));
        }

        distance = String.format(java.util.Locale.US,"%.2f", jExample.getDouble("distance"));
    }

    public String getDistance() {
        return distance;
    }

    public List<String> getValues() {
        return values;
    }

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
