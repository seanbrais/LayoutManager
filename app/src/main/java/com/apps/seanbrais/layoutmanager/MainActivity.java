package com.apps.seanbrais.layoutmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


import com.apps.seanbrais.layoutbuilder.views.CenterLayout;

import java.util.ArrayList;

public class MainActivity extends CenterLayout {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildLayout();
    }

    public void buildLayout(){
        setDefaultMargin(25);
        addTextView("Enter sample1 value", 25);
        addTextView("Enter sample2 value", 10);
        addLine();
        addEditText("Sample2", InputType.TYPE_CLASS_TEXT);
        addEditText("Sample1", InputType.TYPE_CLASS_TEXT);
        addLine();
        addButton("Button1");
        addLine();
        addTextView("Enter values", 25);
        addTextView("Is negative", 10, 50);
        CheckBox checkBox = addCheckBox("CheckBox1");
        addLine();
        addTextView("Is number", 10);
        CheckBox checkBox1 = addCheckBox("CheckBox2");
        addLine();
        addTextView("Is character", 10);
        CheckBox checkBox2 = addCheckBox("CheckBox3");
        addLine();
        Button goButton = addButton("Go!");
        addLine();

        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(checkBox);
        checkBoxes.add(checkBox1);
        checkBoxes.add(checkBox2);
        createCheckBoxGroup(checkBoxes);

        ToGraphsScreenHandler toGraphsScreenHandler = new ToGraphsScreenHandler(this);
        goButton.setOnClickListener(toGraphsScreenHandler);

    }
}
