package com.apps.seanbrais.layoutmanager;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;


import com.apps.seanbrais.layoutbuilder.CenterLayout;

import java.util.ArrayList;

public class MainActivity extends CenterLayout {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildLayout();
    }

    public void buildLayout(){
        setDefaultMargin(25);
        addTextView("TextView1", "Enter sample1 value", 25);
        addTextView("TextView2", "Enter sample2 value", 10);
        addLine();
        addEditText("EditText2", "Sample2", InputType.TYPE_CLASS_TEXT);
        addEditText("EditText1", "Sample1", InputType.TYPE_CLASS_TEXT);
        addLine();
        addButton("Button1", "Button1");
        addLine();
        addTextView("TextView3", "Enter values", 25);
        addTextView("TextView4", "Is negative", 10, 50);
        CheckBox checkBox = addCheckBox("CheckBox1");
        addLine();
        addTextView("TextView5", "Is number", 10);
        CheckBox checkBox1 = addCheckBox("CheckBox2");
        addLine();
        addTextView("TextView6", "Is character", 10);
        CheckBox checkBox2 = addCheckBox("CheckBox3");
        addLine();
        addButton("Button2", "Go!");
        Button button = getButton("Button2");
        addLine();

        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(checkBox);
        checkBoxes.add(checkBox1);
        checkBoxes.add(checkBox2);
        createCheckBoxGroup(checkBoxes);
    }
}
