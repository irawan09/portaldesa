package com.laelektronik.user.portaldesa.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laelektronik.user.portaldesa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailBeritaFragment extends Fragment {


    public DetailBeritaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail__berita, container, false);

        // Inflate the layout for this fragment
        return rootView;
    }

}