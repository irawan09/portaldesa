package com.laelektronik.user.portaldesa.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BiodataFragment extends Fragment {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String username;

    ImageView imageView;
    TextView namauser, nama, lahir, alamat, kelamin, instansi, telepon, email;

    public BiodataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_biodata, container, false);

        String pesan = getArguments().getString("pesan");
        final int id = getArguments().getInt("id");

        ((MainActivity) getActivity()).setTitleActionBar(pesan);
        ((MainActivity) getActivity()).setSelectedItem(id);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        namauser = (TextView) rootView.findViewById(R.id.namauser2);
        nama = (TextView) rootView.findViewById(R.id.nama);
        lahir = (TextView) rootView.findViewById(R.id.lahir);
        alamat = (TextView) rootView.findViewById(R.id.alamat);
        kelamin = (TextView) rootView.findViewById(R.id.kelamin);
        instansi = (TextView) rootView.findViewById(R.id.instansi);
        telepon = (TextView) rootView.findViewById(R.id.telepon);
        email = (TextView) rootView.findViewById(R.id.email);

        preferences = getActivity().getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        username = preferences.getString("username", null);
        namauser.setText(username);

        // Inflate the layout for this fragment
        return rootView;
    }
}
