package com.example.prf.Kminer.models;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

        distance = String.format("%.2f", jExample.getDouble("distance"));
    }

    public String getDistance() {
        return distance;
    }

    public List<String> getValues() {
        return values;
    }

    public String getJoinedValues() {
        return android.text.TextUtils.join(", ", values);
    }

    @Override
    public int compareTo(@NonNull Example example) {
        return distance.compareTo(example.distance);
    }
}
