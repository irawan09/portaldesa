package com.laelektronik.user.portaldesa.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.Adapter.PostAdapter;
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
public class BeritaDesaFragment extends Fragment {

    private String TAG = BeritaDesaFragment.class.getSimpleName();
    SharedPreferences preferences;
    String username;
    String url = "http://sarpras.laelektronik.com/api/post/berita";

    List<Post> listberita = new ArrayList<>();

    RecyclerView recyclerView;
    PostAdapter adapter;


    public BeritaDesaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_berita_desa, container, false);

        String pesan = getArguments().getString("pesan");
        final int id = getArguments().getInt("id");

        ((MainActivity) getActivity()).setTitleActionBar("Berita");
        ((MainActivity) getActivity()).setSelectedItem(id);

        preferences = getActivity().getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        String username = preferences.getString("username", null);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.post);

        adapter = new PostAdapter(getContext(), listberita);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //cek
        fetchContent();

        // Inflate the layout for this fragment \
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
        inflater.inflate(R.menu.search, menu);
        return;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {

            AlertDialog.Builder alert = new AlertDialog.Builder(
                    getContext());

            alert.setTitle("Cari Berita");

            // Set an EditText view to get user input
            final EditText txtcari = new EditText(getContext());
            alert.setView(txtcari);
            txtcari.isFocusable();
            alert.setPositiveButton("Cari",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            // Write your code here to invoke YES event
                        }
                    });

            alert.setNegativeButton("Batal",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            // Canceled.
                        }
                    });
            alert.show();
            return true;
        }
        return onOptionsItemSelected(item);
    }

    private void fetchContent() {
        final ProgressDialog loading;
        loading = ProgressDialog.show(getContext(), "Mendownload Data", "Tunggu...",false,false);

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e(TAG, response.toString());
                listberita.clear();

                loading.dismiss();

                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    JSONArray jsonArray = jsonObject.getJSONArray("post");
                    Log.e("array", jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objectBerita = jsonArray.getJSONObject(i);
                        Post postBerita = new Post();
                        postBerita.setId(objectBerita.getInt("id_post"));
                        postBerita.setImgUrl(objectBerita.getString("feature_image"));
                        postBerita.setTitlePost(objectBerita.getString("title_post"));
                        postBerita.setDate(objectBerita.getString("tgl_post"));
                        postBerita.setPost(objectBerita.getString("content"));

                        listberita.add(postBerita);
                        adapter.notifyDataSetChanged();
                    }

                    Log.e("list berita", listberita.size()+"");
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
        //Catatan Kaki
    }
}
