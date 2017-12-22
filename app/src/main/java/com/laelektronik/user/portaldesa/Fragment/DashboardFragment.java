package com.laelektronik.user.portaldesa.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.laelektronik.user.portaldesa.Activity.LaporanActivity;
import com.laelektronik.user.portaldesa.Activity.LoginActivity;
import com.laelektronik.user.portaldesa.Activity.MapsLaporanActivity;
import com.laelektronik.user.portaldesa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    TextView penyelenggara, kegiatan, desa, kec, kab, prov, pagu,
            pemberi_tugas, subdit, penanggung_jawab, kontak_pj, alamat_pj,
            nilai_kontrak, pelaksana, pimpinan_pelaksana, kontak_pelaksana, kontrak,
            tgl_kontrak, mulai_kerja, selesai_kerja;

    Button peta;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_dashboard, container, false);

        penyelenggara = (TextView) rootview.findViewById(R.id.penyelenggara);
        kegiatan = (TextView) rootview.findViewById(R.id.kegiatan);
        desa = (TextView) rootview.findViewById(R.id.desa);
        kec =(TextView) rootview.findViewById(R.id.kecamatan);
        kab = (TextView) rootview.findViewById(R.id.kabupaten);
        prov = (TextView) rootview.findViewById(R.id.provinsi);
        pagu = (TextView) rootview.findViewById(R.id.pagu);
        pemberi_tugas = (TextView) rootview.findViewById(R.id.pemberi_tugas);
        subdit = (TextView) rootview.findViewById(R.id.subdit);
        penanggung_jawab = (TextView) rootview.findViewById(R.id.penanggung_jawab);
        kontak_pj = (TextView) rootview.findViewById(R.id.kontak_pj);
        alamat_pj = (TextView) rootview.findViewById(R.id.alamat_pj);
        nilai_kontrak = (TextView)rootview.findViewById(R.id.nilai_kontrak);
        pelaksana = (TextView) rootview.findViewById(R.id.pelaksana);
        pimpinan_pelaksana = (TextView) rootview.findViewById(R.id.pimpinan_pelaksana);
        kontak_pelaksana = (TextView) rootview.findViewById(R.id.kontak_pelaksana);
        kontrak = (TextView) rootview.findViewById(R.id.kontrak);
        tgl_kontrak = (TextView) rootview.findViewById(R.id.tgl_kontrak);
        mulai_kerja = (TextView) rootview.findViewById(R.id.mulai_kerja);
        selesai_kerja = (TextView) rootview.findViewById(R.id.selesai_kerja);

        peta = (Button) rootview.findViewById(R.id.peta);

        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsLaporanActivity.class);
                startActivity(intent);
            }
        });

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
