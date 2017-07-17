package com.apps.seanbrais.layoutmanager;

import com.apps.seanbrais.layoutbuilder.views.GraphFragment;

import java.util.ArrayList;

/**
 * Created by Sportus on 7/12/2017.
 */

public class LinearGraph extends GraphFragment {

    @Override
    public ArrayList<Integer> getXValues() {

        ArrayList<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        xValues.add(1);
        xValues.add(2);
        xValues.add(3);
        xValues.add(4);
        xValues.add(5);
        return xValues;
    }

    @Override
    public ArrayList<Integer> getYValues() {

        ArrayList<Integer> yValues = new ArrayList<>();
        yValues.add(0);
        yValues.add(1);
        yValues.add(2);
        yValues.add(3);
        yValues.add(4);
        yValues.add(5);
        return yValues;    }

    @Override
    public int getXMinimum() {
        return 0;
    }

    @Override
    public int getXMaximum() {
        return getXValues().size();
    }

    @Override
    public int getYMinimum() {
        return 0;
    }

    @Override
    public int getYMaximum() {
        return 5;
    }
}
