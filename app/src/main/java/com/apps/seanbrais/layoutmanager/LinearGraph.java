package com.apps.seanbrais.layoutmanager;

import android.os.Bundle;

import com.apps.seanbrais.layoutbuilder.views.GraphFragment;

import java.util.ArrayList;

/**
 * Created by Sportus on 7/12/2017.
 */

public class LinearGraph extends GraphFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> xValues = new ArrayList<>();
        xValues.add("0");
        xValues.add("1");
        xValues.add("2");
        xValues.add("3");
        xValues.add("4");
        xValues.add("5");
        setXList(xValues);


        ArrayList<Float> yValues = new ArrayList<>();
        yValues.add(0f);
        yValues.add(1f);
        yValues.add(2f);
        yValues.add(3f);
        yValues.add(4f);
        yValues.add(5f);
        setYList(yValues);


        setXMinimum(0);
        setXMaximum(getXList().size());

        setYMinimum(0);
        setYMaximum(5);

    }
}
