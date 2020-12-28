package com.justin.healthyhabits.testing;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DataToGson {
    Gson gson = new Gson();

    public void dataToGson(ArrayList<TestDataObject> object) {
        System.out.println(gson.toJson(object));
    }
}
