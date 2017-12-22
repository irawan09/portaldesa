package com.laelektronik.user.portaldesa.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Kegiatan;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailKegiatanFragment extends Fragment {

    TextView namaKegiatan, provinsi, subdit, penanggungJawab, nilaiKontrak, pelaksana;
    Kegiatan kegiatan;

    public DetailKegiatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_kegiatan, container, false);

        kegiatan = (Kegiatan) getArguments().getSerializable("kegiatan");

        ((MainActivity) getActivity()).setTitleActionBar("Kegiatan");

        namaKegiatan = (TextView) rootView.findViewById(R.id.nama_kegiatan);
        provinsi = (TextView) rootView.findViewById(R.id.provinsi);
        subdit = (TextView) rootView.findViewById(R.id.subdit);
        penanggungJawab = (TextView) rootView.findViewById(R.id.penanggung_jawab);
        nilaiKontrak = (TextView) rootView.findViewById(R.id.nilai_kontrak);
        pelaksana = (TextView) rootView.findViewById(R.id.pelaksana);

        namaKegiatan.setText(kegiatan.getNamaKegiatan());
        provinsi.setText(kegiatan.getProvinsi());
        subdit.setText(kegiatan.getSubdit());
        penanggungJawab.setText(kegiatan.getPenanggungJawab());
        nilaiKontrak.setText(kegiatan.getNilaiKontrak());
        pelaksana.setText(kegiatan.getPelaksana());

        return rootView;
    }

}