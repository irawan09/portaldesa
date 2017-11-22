package com.laelektronik.user.portaldesa.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.laelektronik.user.portaldesa.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramKerjaFragment extends Fragment {


    public ProgramKerjaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_program_kerja, container, false);

        BarChart chart1 = (BarChart) rootView.findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart1.setData(data);
        chart1.setDescription("");
        chart1.animateXY(2000, 2000);
        chart1.invalidate();

        //Maembuat Tabel dinamis
        TableLayout tablelayoutid = (TableLayout) rootView.findViewById(R.id.tablelayoutid);
        for(int i=0;i<3;i++) {
            TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.layout_row, null);
            ((TextView) row.findViewById(R.id.desc_program)).setText("cawet");
            ((TextView) row.findViewById(R.id.progres)).setText("Dede Yusuf");
            ((TextView) row.findViewById(R.id.subdit)).setText("Celengan");
            ((TextView) row.findViewById(R.id.apamungkin)).setText("apamungkin");
            tablelayoutid.addView(row);
        }

        // Inflate the layout for this fragment
        return rootView;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // 2011
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // 2012
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // 2013
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // 2014
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // 2015
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // 2016
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // 2011
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // 2012
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // 2013
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // 2014
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // 2015
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // 2016
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Data 1");
        barDataSet1.setColor(Color.BLUE);
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Data 2");
        barDataSet2.setColor(Color.RED);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }
    //membuat array list untuk keterangan tabel pada sumbu X
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("2011");
        xAxis.add("2012");
        xAxis.add("2013");
        xAxis.add("2014");
        xAxis.add("2015");
        xAxis.add("2016");
        return xAxis;
    }

}
