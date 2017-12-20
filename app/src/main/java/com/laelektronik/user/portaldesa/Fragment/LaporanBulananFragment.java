package com.laelektronik.user.portaldesa.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.laelektronik.user.portaldesa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanBulananFragment extends Fragment {

    EditText prog_terakhir, rencana_perbulan, real_bulanan, dev_bulanan, bulan_ke;
    Button kirim_bulanan;

    public LaporanBulananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_laporan_bulanan, container, false);

        prog_terakhir = (EditText) rootview.findViewById(R.id.prog_lastmonth);
        rencana_perbulan =(EditText) rootview.findViewById(R.id.plan_every_month);
        real_bulanan = (EditText) rootview.findViewById(R.id.real_bulanan);
        dev_bulanan = (EditText) rootview.findViewById(R.id.dev_bulanan);
        bulan_ke = (EditText) rootview.findViewById(R.id.num_month);
        kirim_bulanan = (Button) rootview.findViewById(R.id.kirim_bulanan);



        // Inflate the layout for this fragment
        return rootview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //membentuk menu dari package menu
        inflater.inflate(R.menu.logout, menu);
        return;
    }

}
