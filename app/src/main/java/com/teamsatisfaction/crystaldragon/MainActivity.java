package com.teamsatisfaction.crystaldragon;

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

public class MainActivity extends AppCompatActivity {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //References chart of name chart
        chart = findViewById(R.id.chart);


        LineData data = new LineData();
        chart.setData(data);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMaximum(50);
        yAxis.setAxisMinimum(0);





        /*for (int i = 0; i < 20; i++) {
            ILineDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = new LineDataSet(null, "Data");
                data.addDataSet(set);
            }
            data.addEntry(new Entry(i, i+5),0);
            data.notifyDataChanged();

            chart.notifyDataSetChanged();
            chart.moveViewToX(i+1);
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }*/

    }

    private Thread thread;

    private void dummyData(LineData data, int i) {
        boolean flag = true;

        ILineDataSet set = data.getDataSetByIndex(0);
        if (set == null) {
            set = new LineDataSet(null, "Data");
            data.addDataSet(set);
        }
        data.addEntry(new Entry(i, i+3), 0);
        data.notifyDataChanged();
        chart.notifyDataSetChanged();
        chart.moveViewToX(i+1);

        chart.setVisibleXRangeMaximum(30);
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

    }
}