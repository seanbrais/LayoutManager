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


public class GraphFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart mChart;
    //private SeekBar mSeekBarX, mSeekBarY;
    private SeekBar mSeekBarX;
    private TextView tvX, tvY;
    CheckBox textDisplayCheckBox;

    private ArrayList<Integer> xList;
    private ArrayList<Integer> yList;

    private int maximum;

    private int minimum;

    //private int numberOfShotsTaken = 0;

    //private ArrayList<Integer> shotHistory = new ArrayList<>();

    //private int numberOfShotsPerGroup = 0;


    public GraphFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xList = new ArrayList<>();
        xList.add(0);
        xList.add(5);
        yList = new ArrayList<>();
        yList.add(5);
        yList.add(0);
        this.minimum = 0;
        this.maximum = 2;
    }
    @Override

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        tvX = (TextView) getView().findViewById(R.id.tvXMax);

        textDisplayCheckBox = (CheckBox) getView().findViewById(R.id.showTextCheckBox);

        mSeekBarX = (SeekBar) getView().findViewById(R.id.seekBar1);
        //mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
        mSeekBarX.setMax(this.maximum);
        mSeekBarX.setProgress(this.maximum);
        //mSeekBarY.setProgress(100);
        //mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);

        mChart = (LineChart) getView().findViewById(R.id.chart1);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this.getContext(), R.layout.custom_marker_view);

        // set the marker to the chart
        mChart.setMarkerView(mv);

        // x-axis limit line
//        LimitLine llXAxis = new LimitLine(10f, "Index 10");
//        llXAxis.setLineWidth(4f);
//        llXAxis.enableDashedLine(10f, 10f, 0f);
//        llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//        llXAxis.setTextSize(10f);

        XAxis xAxis = mChart.getXAxis();
        //xAxis.addLimitLine(llXAxis); // add x-axis limit line

        // Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(), "OpenSans-Regular.ttf");

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaxValue(400f);
        leftAxis.setAxisMinValue((float) this.minimum - 10f);
        leftAxis.setStartAtZero(false);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        //Was 100
        // add data
        setData(this.maximum);
        mChart.getLineData().setValueTextSize(0f);
//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
//        mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // // dont forget to refresh the drawing
        mChart.invalidate();
        textDisplayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mChart.getLineData().setValueTextSize(9f);
                    mChart.invalidate();
                } else {
                    mChart.getLineData().setValueTextSize(0f);
                    mChart.invalidate();
                }
            }
        });
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

        //tvX.setText("" + (mSeekBarX.getProgress() + 1));
        // tvY.setText("" + (mSeekBarY.getProgress()));

        //setData(mSeekBarX.getProgress() + 1, mSeekBarY.getProgress());
        setData(mSeekBarX.getProgress() + 1);
        if(textDisplayCheckBox.isChecked()){
            mChart.getLineData().setValueTextSize(9f);
            mChart.invalidate();
        }
        else{
            mChart.getLineData().setValueTextSize(0f);
            mChart.invalidate();
        }
        // redraw
        mChart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setData(int count) {

        ArrayList<String> xVals = new ArrayList<>();
        int totalShotsTaken = 0;

        for (int i = 0; i < count; i++) {
            xVals.add(Integer.toString(xList.get(i)));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        int shotsMade = 0;
        int totalShotsTakenNew = 0;
        for (int i = 0; i<count;i++) {
            yVals.add(new Entry(yList.get(i), i));
        }

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMaxValue(100f);
        leftAxis.setAxisMinValue(0f);

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
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
        // set data
        mChart.setData(data);
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
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
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
        Log.i("", "low: " + mChart.getLowestVisibleXIndex() + ", high: " + mChart.getHighestVisibleXIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}