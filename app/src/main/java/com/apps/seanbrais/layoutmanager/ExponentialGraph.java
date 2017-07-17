package com.apps.seanbrais.layoutmanager;

import com.apps.seanbrais.layoutbuilder.views.GraphFragment;

import java.util.ArrayList;

/**
 * Created by Sportus on 7/13/2017.
 */

public class ExponentialGraph extends GraphFragment {
    @Override
    public ArrayList<Integer> getXValues() {

        ArrayList<Integer> xValues = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            xValues.add(i);
        }
        return xValues;
    }

    @Override
    public ArrayList<Integer> getYValues() {

        ArrayList<Integer> yValues = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            yValues.add((int) Math.pow(i,2));
        }
        return yValues;
    }

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
        return getYValues().get(getYValues().size()-1 );
    }
}
