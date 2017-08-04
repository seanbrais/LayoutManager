package com.apps.seanbrais.layoutbuilder.views;

/**
 * Created by Sportus on 1/17/2016.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.apps.seanbrais.layoutbuilder.R;
import com.apps.seanbrais.layoutbuilder.utilities.MyMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public abstract class GraphFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart chart;
    private SeekBar seekBar;
    CheckBox textDisplayCheckBox;

    private ArrayList<String> xList;
    private ArrayList<Float> yList;

    TextView tvX;

    private int xMinimum;

    private int xMaximum;

    private int yMinimum;

    private int yMaximum;

    public GraphFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setXList(ArrayList<String> xList){
        this.xList = xList;
    }

    public void setYList(ArrayList<Float> yList){
        this.yList = yList;
    }

    public void setXMinimum(int xMinimum){
        this.xMinimum = xMinimum;
    }

    public void setXMaximum(int xMaximum){
        this.xMaximum = xMaximum;
    }

    public void setYMinimum(int yMinimum){
        this.yMinimum = yMinimum;
    }

    public void setYMaximum(int yMaximum){
        this.yMaximum = yMaximum;
    }

    public ArrayList<String> getXList(){
        return this.xList;
    }

    public ArrayList<Float> getYList(){
        return this.yList;
    }

    public int getxMinimum(){
        return this.xMinimum;
    }

    public int getxMaximum(){
        return this.xMaximum;
    }

    public int getYMinimum(){
        return this.yMinimum;
    }
    public int getYMaximum(){
        return this.yMaximum;
    }

    @Override

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        tvX = (TextView) getView().findViewById(R.id.tvXMax);

        textDisplayCheckBox = (CheckBox) getView().findViewById(R.id.showTextCheckBox);

        seekBar = (SeekBar) getView().findViewById(R.id.seekBar1);
        //mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
        seekBar.setMax(this.xMaximum);
        seekBar.setProgress(this.xMaximum);
        //mSeekBarY.setProgress(100);
        //mSeekBarY.setOnSeekBarChangeListener(this);
        seekBar.setOnSeekBarChangeListener(this);

        this.chart = (LineChart) getView().findViewById(R.id.chart1);
        this.chart.setOnChartGestureListener(this);
        this.chart.setOnChartValueSelectedListener(this);
        this.chart.setDrawGridBackground(false);

        // no description text
        this.chart.setDescription("");
        this.chart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        this.chart.setTouchEnabled(true);

        // enable scaling and dragging
        this.chart.setDragEnabled(true);
        this.chart.setScaleEnabled(true);
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        this.chart.setPinchZoom(true);

        // set an alternative background color
        // chart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this.getContext(), R.layout.custom_marker_view);

        // set the marker to the chart
        this.chart.setMarkerView(mv);

        // x-axis limit line
//        LimitLine llXAxis = new LimitLine(10f, "Index 10");
//        llXAxis.setLineWidth(4f);
//        llXAxis.enableDashedLine(10f, 10f, 0f);
//        llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//        llXAxis.setTextSize(10f);

        XAxis xAxis = chart.getXAxis();
        //xAxis.addLimitLine(llXAxis); // add x-axis limit line

        // Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(), "OpenSans-Regular.ttf");

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaxValue((float)this.xMaximum + 0.01f);
        leftAxis.setAxisMinValue(this.xMinimum);
        leftAxis.setStartAtZero(false);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        this.chart.getAxisRight().setEnabled(false);

        //chart.getViewPortHandler().setMaximumScaleY(2f);
        //chart.getViewPortHandler().setMaximumScaleX(2f);

        //Was 100
        // add data
        setData(this.xMaximum);
        this.chart.getLineData().setValueTextSize(0f);
//        chart.setVisibleXRange(20);
//        chart.setVisibleYRange(20f, AxisDependency.LEFT);
//        chart.centerViewTo(20, 50, AxisDependency.LEFT);

        this.chart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
//        chart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = this.chart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // // dont forget to refresh the drawing
        this.chart.invalidate();
        textDisplayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chart.getLineData().setValueTextSize(9f);
                    chart.invalidate();
                } else {
                    chart.getLineData().setValueTextSize(0f);
                    chart.invalidate();
                }
                chart.invalidate();
            }
        });

        //Update drawing to include all plots.
        //By setting to 1 less than xMaximum and then moving to xMaximum it displays all plots
        //Leaving it default at xMaximum would not display final plot.

        seekBar.setProgress(this.xMaximum-1);
        //chart.invalidate();
        seekBar.setProgress(this.xMaximum);
        this.chart.invalidate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_entry_graph_fragment, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        //tvX.setText("" + (seekBar.getProgress() + 1));
        // tvY.setText("" + (mSeekBarY.getProgress()));

        //setData(seekBar.getProgress() + 1, mSeekBarY.getProgress());
        //setData(seekBar.getProgress() + 1);
        setData(progress);
        if(textDisplayCheckBox.isChecked()){
            this.chart.getLineData().setValueTextSize(9f);
            this.chart.invalidate();
        }
        else{
            this.chart.getLineData().setValueTextSize(0f);
            this.chart.invalidate();
        }
        // redraw
        this.chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setData(int count) {

        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            xVals.add(xList.get(i));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i<count;i++) {
            yVals.add(new Entry(yList.get(i), i));
        }

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMaxValue((float) this.yMaximum);
        leftAxis.setAxisMinValue(this.yMinimum);

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "Shot Percentage");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.parseColor("#007216"));
        set1.setCircleColor(Color.parseColor("#007216"));
        set1.setLineWidth(1f);
        set1.setCircleSize(3f);
        set1.setDrawCircleHole(false);
        set1.setFillAlpha(65);
        set1.setFillColor(Color.parseColor("#007216"));
//        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, chart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
        // set data
        chart.setData(data);
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START");
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            chart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("", "low: " + chart.getLowestVisibleXIndex() + ", high: " + chart.getHighestVisibleXIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}