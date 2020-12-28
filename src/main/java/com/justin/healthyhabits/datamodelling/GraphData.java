package com.justin.healthyhabits.datamodelling;

import java.util.ArrayList;

public class GraphData {
    private String name;
    private ArrayList<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }

    public GraphData(String name, ArrayList<Integer> data) {
        this.name = name;
        this.data = data;
    }

/*    @Override
    public String toString() {
        return
                "{name: " + "\"" + name  + "\"" +
                ", " + "data: " + data +
                '}';
    }*/
}
