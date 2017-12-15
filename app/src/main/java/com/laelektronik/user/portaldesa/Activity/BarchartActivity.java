package com.laelektronik.user.portaldesa.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.laelektronik.user.portaldesa.R;

import java.util.ArrayList;

public class BarchartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription(null);
        chart.animateXY(2000, 2000);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setScaleXEnabled(true);
        chart.getXAxis().setTextSize(7);
        chart.setVisibleXRange(10);
        chart.invalidate();

    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        //data
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(2.1f, 0); // 2011
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(5.380f, 1); // 2012
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(4.5f, 2); // 2013
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(4.4f, 3); // 2014
        valueSet1.add(v1e4);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Data Subdit Desa (satuan dalam Milyar)");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);

        return dataSets;
    }
    //membuat array list untuk keterangan tabel pada sumbu X
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Elektrifikasi");
        xAxis.add("Pemukiman");
        xAxis.add("Pendukung Ekonomi");
        xAxis.add("Transportasi");

        return xAxis;
    }
}
