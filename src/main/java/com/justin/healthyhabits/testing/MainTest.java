package com.justin.healthyhabits.testing;

import java.util.ArrayList;

public class MainTest {

    public static void main(String[] args) {
        ArrayList<Integer> graphData = new ArrayList<>();
        graphData.add(10);
        graphData.add(5);
        graphData.add(1);
        ArrayList<Integer> graphData2 = new ArrayList<>();
        graphData2.add(5);
        graphData2.add(3);
        graphData2.add(10);

        TestDataObject testDataObject = new TestDataObject("TV", graphData);
        TestDataObject testDataObject2 = new TestDataObject("YouTube", graphData2);

        ArrayList<TestDataObject> dataObjectList = new ArrayList<>();
        dataObjectList.add(testDataObject);
        dataObjectList.add(testDataObject2);
        dataObjectList.add(testDataObject);
        dataObjectList.add(testDataObject2);


        DataToGson dataToGson = new DataToGson();
        dataToGson.dataToGson(dataObjectList);
    }
}
