package com.apps.seanbrais.layoutbuilder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sean Brais on 7/7/2017.
 */
public class CenterLayout extends Activity {

    //Fields
    private RelativeLayout relativeLayout;
    private TextView headerText;

    private HashMap<String, EditText> editTextHashMap;
    private ArrayList<EditText> editTexts;


    private HashMap<String, TextView> textViewHashMap;
    private ArrayList<TextView> textViews;


    private HashMap<String, Button> buttonsHashMap;
    private ArrayList<Button> buttons;


    private HashMap<String, CheckBox> checkBoxHashMap;
    private ArrayList<CheckBox> checkBoxes;

    private ArrayList<View> viewsList;

    private ArrayList<TextView> secondaryTexts;
    private ArrayList<View> endGroupViews;

    private int numGroups = 0;

    public final int INPUT_NUM = InputType.TYPE_CLASS_NUMBER;

    private int defaultMargin = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_screen);

        //Initialization

        this.editTexts = new ArrayList<>();
        this.editTextHashMap = new HashMap<>();

        this.textViewHashMap = new HashMap<>();
        this.textViews = new ArrayList<>();

        this.buttonsHashMap = new HashMap<>();
        this.buttons = new ArrayList<>();

        this.checkBoxHashMap = new HashMap<>();
        this.checkBoxes = new ArrayList<>();

        this.viewsList = new ArrayList<>();
        this.endGroupViews = new ArrayList<>();
        this.headerText = (TextView) this.findViewById(R.id.headerText);
        this.viewsList.add(headerText);
        this.secondaryTexts = new ArrayList<>();

        this.relativeLayout = (RelativeLayout) this.findViewById(R.id.relativeLayout);

        addLine();
    }


    //Edit Texts
    public ArrayList<EditText> getEditTexts(){
        return this.editTexts;
    }

    public EditText getEditText(String key){
        if(!this.editTextHashMap.containsKey(key)){
            return null;
        }
        return this.editTextHashMap.get(key);
    }

    /**
     *
     * @param name Name of the Edit Text
     * @param text Text to be displayed in Edit Text
     * @param inputType Type of input of the Edit Text
     * @return Edit Text object that was instantiated
     */
    public EditText addEditText(String name, String text, int inputType){
        EditText editText = createEditText(text, inputType, this.defaultMargin);
        this.viewsList.add(editText);
        this.editTextHashMap.put(name, editText);
        this.editTexts.add(editText);
        return editText;
    }

    /**
     *
     * @param name Name of the Edit Text
     * @param text Text to be displayed in Edit Text
     * @param inputType Type of input of the Edit Text
     * @param margin Margin from element above
     * @return Edit Text object that was instantiated
     */
    public EditText addEditText(String name, String text, int inputType, int margin){
        EditText editText = createEditText(text, inputType, margin);
        this.viewsList.add(editText);
        this.editTextHashMap.put(name, editText);
        this.editTexts.add(editText);
        return editText;
    }

    private EditText createEditText(String text, int inputType, int margin){
        EditText editText = new EditText(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, getLastView().getId());
        params.topMargin = margin;
        editText.setInputType(inputType);
        editText.setEms(5);
        editText.setText(text);
        editText.setFocusable(true);
        editText.setGravity(Gravity.CENTER_HORIZONTAL);
        editText.setBackgroundResource(R.drawable.green_edit_text_background);
        int editTextID = editText.generateViewId();
        editText.setId(editTextID);
        //editText.setTextColor(Color.parseColor("#007216"));
        relativeLayout.addView(editText, params);
        return editText;
    }


    //Text Views
    public ArrayList<TextView> getTextViews(){
        return this.textViews;
    }

    public TextView getTextView(String name){
        if(!this.textViewHashMap.containsKey(name)){
            return null;
        }
        return this.textViewHashMap.get(name);
    }

    public TextView addTextView(String name, String text, int textSize){
        TextView textView = createTextView(text, textSize, this.defaultMargin);
        this.viewsList.add(textView);
        this.textViewHashMap.put(name, textView);
        this.textViews.add(textView);
        return textView;
    }

    public TextView addTextView(String name, String text, int textSize, int margin){
        TextView textView = createTextView(text, textSize, margin);
        this.viewsList.add(textView);
        this.textViewHashMap.put(name, textView);
        this.textViews.add(textView);
        return textView;
    }

    private TextView createTextView(String text, int textSize, int margin) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, this.getLastView().getId());
        params.topMargin = margin;
        int textViewID = tv.generateViewId();
        tv.setId(textViewID);
        relativeLayout.addView(tv, params);
        return tv;
    }

    //Buttons
    public ArrayList<Button> getButtonsList(){
        return this.buttons;
    }

    public Button getButton(String name){
        if(!this.buttonsHashMap.containsKey(name)){
            return null;
        }
        return this.buttonsHashMap.get(name);
    }

    public Button addButton(String name, String text){
        Button button = createButtonGroup(text, this.defaultMargin);
        this.viewsList.add(button);
        this.buttonsHashMap.put(name, button);
        this.buttons.add(button);
        return button;
    }

    public Button addButton(String name, String text, int margin){
        Button button = createButtonGroup(text, margin);
        this.viewsList.add(button);
        this.buttonsHashMap.put(name, button);
        this.buttons.add(button);
        return button;
    }

    private Button createButtonGroup(String text, int margin){
        Button button = new Button(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, getLastView().getId());
        params.topMargin = margin;
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_START);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        getRelativeLayout().addView(button, params);
        button.setText(text);
        button.setTextColor(Color.BLACK);
        int id = button.generateViewId();
        button.setId(id);
        button.setBackgroundResource(R.drawable.green_edit_text_background);
        return button;
    }

    //CheckBoxes
    public ArrayList<CheckBox> getCheckBoxes(){
        return this.checkBoxes;
    }

    public Button getCheckBox(String name){
        if(!this.checkBoxHashMap.containsKey(name)){
            return null;
        }
        return this.checkBoxHashMap.get(name);
    }

    public CheckBox addCheckBox(String name){
        CheckBox checkBox = createCheckBox(this.defaultMargin);
        this.viewsList.add(checkBox);
        this.checkBoxHashMap.put(name, checkBox);
        checkBoxes.add(checkBox);
        return checkBox;
    }

    public CheckBox addCheckBox(String name, int margin){
        CheckBox checkBox = createCheckBox(margin);
        this.viewsList.add(checkBox);
        this.checkBoxHashMap.put(name, checkBox);
        checkBoxes.add(checkBox);
        return checkBox;
    }

    private CheckBox createCheckBox(int margin){
        CheckBox checkBox = new CheckBox(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, getLastView().getId());
        params.topMargin = margin;
        checkBox.setGravity(Gravity.CENTER_HORIZONTAL);
        int editTextID = checkBox.generateViewId();
        checkBox.setBackgroundResource(R.drawable.green_edit_text_background);
        checkBox.setId(editTextID);
        checkBox.setChecked(true);
        checkBox.setHighlightColor(Color.parseColor("#007216"));
        relativeLayout.addView(checkBox, params);
        checkBox.setButtonDrawable(R.drawable.custom_checkbox);
        return checkBox;
    }

    public View addLine(){
        View line = drawLine(this.defaultMargin);
        this.viewsList.add(line);
        return line;
    }

    public View addLine(int margin){
        View line = drawLine(margin);
        this.viewsList.add(line);
        return line;
    }

    private View drawLine(int margin){
        View line = new View(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 5);
        line.setBackgroundColor(Color.parseColor("#007216"));
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, getLastView().getId());
        params.topMargin = margin;
        int editTextID = line.generateViewId();
        line.setId(editTextID);
        relativeLayout.addView(line, params);
        return line;
    }

    public void setDefaultMargin(int margin){
        this.defaultMargin = margin;
    }

    public View getLastView(){
        return this.viewsList.get(this.viewsList.size() -1);
    }

    public RelativeLayout getRelativeLayout(){
        return this.relativeLayout;
    }
        /*
    public RadioGroup radioGroup (ArrayList<RadioButton> radioButtons){
        RadioGroup radioGroup = new RadioGroup(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, getLastView().getId());
        params.topMargin = 25;
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        for(int i = 0; i < radioButtons.size(); i++){
            radioGroup.addView(radioButtons.get(i));
        }
        relativeLayout.addView(radioGroup, params);
        return radioGroup;
    }

    public RadioButton addRadioButton(){

        RadioButton radioButton = new RadioButton(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, getLastView().getId());
        params.topMargin = 25;
        radioButton.setGravity(Gravity.CENTER_HORIZONTAL);
        int editTextID = radioButton.generateViewId();
        radioButton.setBackgroundResource(R.drawable.green_edit_text_background);
        radioButton.setCompoundDrawablePadding(20);
        radioButton.setId(editTextID);
        relativeLayout.addView(radioButton, params);
        this.viewsList.add(radioButton);
        return radioButton;
    }

    */


}
