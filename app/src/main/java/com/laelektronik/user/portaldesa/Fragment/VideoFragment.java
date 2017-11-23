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
public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_publikasi_desa, container, false);

        /*
        VideoView view = (VideoView)rootView.findViewById(R.id.video_view);
        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.howtopray;
        view.setVideoURI(Uri.parse(path));
        view.start();

        Edit

        MediaController constructor takes Context as a parameter. Added getActivity() method to provide Context to MediaController constructor.
         */


        // Inflate the layout for this fragment
        return rootView;
    }

}
