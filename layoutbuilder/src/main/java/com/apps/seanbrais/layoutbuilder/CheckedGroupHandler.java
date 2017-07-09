package com.apps.seanbrais.layoutbuilder;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

/**
 * Created by Sportus on 7/9/2017.
 */

class CheckedGroupHandler implements CompoundButton.OnCheckedChangeListener{

    private ArrayList<CheckBox> checkBoxes;

    public CheckedGroupHandler(ArrayList<CheckBox> checkBoxes){
        this.checkBoxes = checkBoxes;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked){
            for (CheckBox checkBox: this.checkBoxes){
                checkBox.setChecked(false);
            }
            compoundButton.setChecked(true);
        }
    }
}
