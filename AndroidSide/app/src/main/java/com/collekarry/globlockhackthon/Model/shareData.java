package com.collekarry.globlockhackthon.Model;

import com.collekarry.globlockhackthon.R;

import java.util.ArrayList;

public class shareData {

    public static final String SHARE_TITLE = "title";
    public static final String SHARE_DESCRIPTION = "description";
    public static final String SHARE_IMAGE = "image";

    private static ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

    public static ArrayList<ArrayList<String>> getData(int i) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        stringArrayList.add("Visiting Temple");
        stringArrayList.add(Integer.toString(R.drawable.place));
        stringArrayList.add("Visited mahapalipuram for recreation");
        arrayLists.add(stringArrayList);

        stringArrayList = new ArrayList<>();
        stringArrayList.add("A Great Meal");
        stringArrayList.add(Integer.toString(R.drawable.buffet));
        stringArrayList.add("Had lunch in a popular place to enjoy sunday");
        arrayLists.add(stringArrayList);

        stringArrayList = new ArrayList<>();
        stringArrayList.add("A Day in the Museum ");
        stringArrayList.add(Integer.toString(R.drawable.school));
        stringArrayList.add("Visited a historical museum to improve the history knowledge ");
        arrayLists.add(stringArrayList);

        stringArrayList = new ArrayList<>();
        stringArrayList.add("A Wonderful Playground");
        stringArrayList.add(Integer.toString(R.drawable.school));
        stringArrayList.add("Visited the playground ");
        arrayLists.add(stringArrayList);

        stringArrayList = new ArrayList<>();
        stringArrayList.add("New School");
        stringArrayList.add(Integer.toString(R.drawable.school));
        stringArrayList.add("Few students are moved into Newly constructed buildings");
        arrayLists.add(stringArrayList);

        return arrayLists;
    }
}
