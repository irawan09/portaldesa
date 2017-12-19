package com.laelektronik.user.portaldesa.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.Adapter.HomePostAdapter;
import com.laelektronik.user.portaldesa.Adapter.HomeVideoAdapter;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.MyVideo;
import com.laelektronik.user.portaldesa.Util.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private String TAG = HomeFragment.class.getSimpleName();
    String url = "http://sarpras.laelektronik.com/api/home";

    List<Post> beritaList = new ArrayList<>();
    List<MyVideo> videoList = new ArrayList<>();

    RecyclerView recyclerViewBerita;
    HomePostAdapter adapter;

    RecyclerView recyclerViewVideo;
    HomeVideoAdapter videoAdapter;

    TextView semuaBerita, semuaVideo;

    ScrollView scrollView;
    RelativeLayout rl;

    ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        String pesan = getArguments().getString("pesan");
        final int id = getArguments().getInt("id");

        ((MainActivity) getActivity()).setTitleActionBar("Home");
        ((MainActivity) getActivity()).setSelectedItem(id);

        progressDialog = new ProgressDialog(getContext());

        semuaBerita = (TextView) rootView.findViewById(R.id.semua_brita);
        semuaVideo = (TextView) rootView.findViewById(R.id.semua_video);
        //progressBar = (ProgressBar) rootView.findViewById(R.id.indeterminateBar);
        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView2);
        rl = (RelativeLayout) rootView.findViewById(R.id.indeterminateBar);

        recyclerViewBerita = (RecyclerView) rootView.findViewById(R.id.berita_home);
        recyclerViewVideo = (RecyclerView) rootView.findViewById(R.id.video_home);

        adapter = new HomePostAdapter(beritaList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBerita.setLayoutManager(layoutManager);
        recyclerViewBerita.setAdapter(adapter);

        videoAdapter = new HomeVideoAdapter(videoList, getContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewVideo.setLayoutManager(layoutManager1);
        recyclerViewVideo.setAdapter(videoAdapter);

        fetchContent();


        semuaBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BeritaDesaFragment();
                ((MainActivity) getActivity()).callFragment(fragment, "", id, 0);
            }
        });

        semuaVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new YouTubeFragment();
                ((MainActivity) getActivity()).callFragment(fragment, "", id, 0);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void fetchContent() {

        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.hide();
                beritaList.clear();

                videoList.clear();
                //loading.dismiss();
                //progressBar.setVisibility(View.GONE);
                rl.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);


                try {

                    JSONObject objectAll = response.getJSONObject(0);
                    JSONArray arrayBerita = objectAll.getJSONArray("berita");
                    for (int i = 0; i < arrayBerita.length(); i++) {
                        JSONObject objectBerita = arrayBerita.getJSONObject(i);
                        Post postberita = new Post();
                        postberita.setId(objectBerita.getInt("id_post"));
                        postberita.setImgUrl(objectBerita.getString("feature_image"));
                        postberita.setTitlePost(objectBerita.getString("title_post"));
                        postberita.setDate(objectBerita.getString("tgl_post"));
                        postberita.setPost(objectBerita.getString("content"));

                        beritaList.add(postberita);
                        adapter.notifyDataSetChanged();
                    }

                    JSONArray arrayVideo = objectAll.getJSONArray("videos");
                    for (int i = 0; i < arrayVideo.length(); i++) {
                        JSONObject objectVideo = arrayVideo.getJSONObject(i);
                        MyVideo video = new MyVideo();
                        video.setVideoId(objectVideo.getString("videoId"));
                        video.setThumbnails(objectVideo.getString("thumbnails"));
                        video.setTittle(objectVideo.getString("title"));
                        video.setTanggal(objectVideo.getString("publishedAt"));

                        videoList.add(video);
                        videoAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

}
