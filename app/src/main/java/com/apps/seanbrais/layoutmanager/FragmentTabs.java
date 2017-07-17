package com.apps.seanbrais.layoutmanager;

import android.support.v4.app.Fragment;

import com.apps.seanbrais.layoutbuilder.views.PerformanceGraphsTabs;

import java.util.ArrayList;

public class FragmentTabs extends PerformanceGraphsTabs {

    @Override
    public void addTabs() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new LinearGraph());
        fragments.add(new ExponentialGraph());
        fragments.add(new LinearGraph());

        ArrayList<String> fragmentsName = new ArrayList<>();
        fragmentsName.add("Linear Graph");
        fragmentsName.add("Exponential Graph");
        fragmentsName.add("Graph3");

        this.fragments = fragments;
        this.fragmentNames = fragmentsName;
    }
}
