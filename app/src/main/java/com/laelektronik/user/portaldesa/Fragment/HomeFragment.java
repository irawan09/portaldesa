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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.Adapter.HomePostAdapter;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;
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
    List<Post> pustakaList = new ArrayList<>();

    RecyclerView recyclerViewBerita;
    HomePostAdapter adapter;

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

        recyclerViewBerita = (RecyclerView) rootView.findViewById(R.id.berita_home);

        adapter = new HomePostAdapter(beritaList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBerita.setLayoutManager(layoutManager);
        recyclerViewBerita.setAdapter(adapter);

        fetchContent();

        return rootView;
    }

    private void fetchContent() {
        final ProgressDialog loading;
        loading = ProgressDialog.show(getContext(), "Mendownload Data", "Tunggu...",false,false);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.e(TAG, response.toString());
                beritaList.clear();
                loading.dismiss();

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
