package com.laelektronik.user.portaldesa.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarchartFragment extends Fragment {

    private FragmentManager fragmentManager;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;

    public BarchartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_barchart, container, false);

        ((MainActivity) getActivity()).setTitleActionBar("Barchart");

        BarChart chart = (BarChart) rootView.findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription(null);
        chart.animateXY(2000, 2000);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setScaleXEnabled(true);
        chart.getXAxis().setTextSize(7);
        chart.setVisibleXRange(10);
        chart.invalidate();

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //membentuk menu dari package menu
        //      inflater.inflate(R.menu.search, menu);
        inflater.inflate(R.menu.list, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==R.id.list){

            getActivity().onBackPressed();
            //fragment = new KegiatanFragment();
            //((MainActivity) getActivity()).callFragment(fragment, item.getTitle().toString(), id, 3);
            return true;
        }

        //return onOptionsItemSelected(item);
        return true;
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
