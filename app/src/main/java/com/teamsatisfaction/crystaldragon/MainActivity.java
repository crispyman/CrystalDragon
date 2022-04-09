package com.teamsatisfaction.crystaldragon;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.List;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private LineChart chart;
    //private static ListIterator<Integer> iterator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //References chart of name chart
        chart = findViewById(R.id.chart);
        LineData data = new LineData();
        chart.setData(data);
        formatChart();

        dataUpdate();
        readData();
    }

    //Threading may not be required if data is being passed from database
    //one point at a time
    Thread th;
    private void readData() {
        //run method to be called by thread that creates data point
        if (th != null) {
            th.interrupt();
        }

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                createDataPoint();
            }
        };

        th = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    runOnUiThread(runnable);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        th.start();
    }

    private void createDataPoint() {
        LineData dataPoint = chart.getData();
        if (dataPoint != null) {
            ILineDataSet set = dataPoint.getDataSetByIndex(0);
            if (set == null) {
                LineDataSet setTemp = new LineDataSet(null,"Dynamic Data");
                dataPoint.addDataSet(setTemp);

            }
        }
        //create entry with value of point and place it at 0 position in set
        //dataPoint.addEntry(new Entry(dataPoint.getEntryCount(), iterator.next()), 0);
        dataPoint.addEntry(new Entry(dataPoint.getEntryCount(), getDataValue()), 0);
        dataPoint.setDrawValues(false);

        chart.notifyDataSetChanged();

        //How many points show up at a time
        chart.setVisibleXRangeMaximum(20);

        chart.moveViewToX(dataPoint.getEntryCount());

    }

    private void formatChart() {
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMaximum(200);
        yAxis.setAxisMinimum(0);
        yAxis.setDrawLabels(false);
        //yAxis.setEnabled(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setEnabled(false);

        Legend l = chart.getLegend();
        l.setEnabled(false);

    }

    private int field;
    public int getDataValue() {
        return field;
    }
    public void setDataValue() {
        field = (int)(Math.random() * 75)+100;
    }

    //Constantly updates data field
    Thread dataThread;
    private void dataUpdate() {
        if (dataThread != null) {
            dataThread.interrupt();
        }

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setDataValue();
            }
        };

        dataThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    runOnUiThread(runnable);
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        dataThread.start();
    }

}