package com.laelektronik.user.portaldesa.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Kegiatan;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailLokasiFragment extends Fragment {

    Kegiatan kegiatan;
    ImageView imageView;
    TextView nama_kegiatan, desa, kecamatan, kabupaten, provinsi, pelaksana, nilaiKontrak;

    public DetailLokasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_lokasi, container, false);

        kegiatan = (Kegiatan) getArguments().getSerializable("kegiatan");

        ((MainActivity) getActivity()).setTitleActionBar("Detail Kegiatan");

        imageView = (ImageView) rootView.findViewById(R.id.image_lokasi_detail);
        nama_kegiatan = (TextView) rootView.findViewById(R.id.detail_nama_kegiatan);
        desa = (TextView) rootView.findViewById(R.id.detail_lokasi_desa);
        kecamatan = (TextView) rootView.findViewById(R.id.detail_lokasi_kecamtan);
        kabupaten = (TextView) rootView.findViewById(R.id.detail_lokasi_kabupaten);
        provinsi = (TextView) rootView.findViewById(R.id.detail_lokasi_provinsi);
        pelaksana = (TextView) rootView.findViewById(R.id.detail_lokasi_pelaksana);
        nilaiKontrak = (TextView) rootView.findViewById(R.id.detail_lokasi_nilai_kontrak);


        Glide.with(getContext())
                .load(kegiatan.getImgUrl())
                .thumbnail(0.5f)
                .crossFade()
                .into(imageView);

        nama_kegiatan.setText(kegiatan.getNamaKegiatan());
        desa.setText("Lokasi Desa " + kegiatan.getDesa());
        kecamatan.setText("Kecamatan "+kegiatan.getKecamatan());
        kabupaten.setText("Kabupaten "+kegiatan.getKabupaten());
        provinsi.setText("Provinsi "+kegiatan.getProvinsi());
        pelaksana.setText("Pelaksana "+kegiatan.getPelaksana());
        nilaiKontrak.setText("Nilai Kontrak "+kegiatan.getNilaiKontrak());

        return rootView;
    }

}
