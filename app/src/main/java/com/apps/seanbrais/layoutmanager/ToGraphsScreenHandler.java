package com.apps.seanbrais.layoutmanager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Sportus on 7/12/2017.
 */

    public class ToGraphsScreenHandler implements View.OnClickListener {

        MainActivity mainActivity;
        FragmentTabs fragmentTabs;

        public ToGraphsScreenHandler(MainActivity mainActivity){
            this.mainActivity = mainActivity;
            fragmentTabs = new FragmentTabs();
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(this.mainActivity, fragmentTabs.getClass());
            this.mainActivity.startActivity(intent);
        }
    }
