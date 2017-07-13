package com.apps.seanbrais.layoutmanager;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apps.seanbrais.layoutbuilder.views.GraphFragment;
import com.apps.seanbrais.layoutbuilder.views.PerformanceGraphsTabs;

import java.util.ArrayList;

public class FragmentTabs extends PerformanceGraphsTabs {

    @Override
    public void addTabs() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SampleGraph());
        fragments.add(new SampleGraph());
        fragments.add(new SampleGraph());

        ArrayList<String> fragmentsName = new ArrayList<>();
        fragmentsName.add("Graph1");
        fragmentsName.add("Graph2");
        fragmentsName.add("Graph3");

        this.fragments = fragments;
        this.fragmentNames = fragmentsName;
    }
}
