package com.teamsatisfaction.crystaldragon;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

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

public class GraphScreen extends AppCompatActivity {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen2);

        //References chart of name chart
        chart = findViewById(R.id.chart);

        LineData data = new LineData();
        chart.setData(data);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMaximum(200);
        yAxis.setAxisMinimum(0);

        float dataBased = (float)Math.random()*150;
        while (dataBased > 0) {
            readData(dataBased);
            dataBased = (float)Math.random()*150;
        }
    }

    //Threading may not be required if data is being passed from database
    //one point at a time
    Thread th;
    private void readData(float point) {
        //run method to be called by thread that creates data point
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                createDataPoint(point);
            }
        };

        th = new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(runnable);
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }

    private void createDataPoint(float point) {
        LineData dataPoint = chart.getData();
        if (dataPoint != null) {
            ILineDataSet set = dataPoint.getDataSetByIndex(0);
            if (set == null) {
                set = new LineDataSet(null,"Data Point");
                dataPoint.addDataSet(set);
            }
        }
        //create entry with value of point and place it at 0 position in set
        dataPoint.addEntry(new Entry(dataPoint.getEntryCount(), point), 0);
        dataPoint.notifyDataChanged();

        chart.notifyDataSetChanged();

        //testing 200
        chart.setVisibleXRangeMaximum(50);

        chart.moveViewToX(dataPoint.getEntryCount());
    }
}