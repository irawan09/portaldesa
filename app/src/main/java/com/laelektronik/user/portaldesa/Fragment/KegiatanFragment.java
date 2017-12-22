package com.laelektronik.user.portaldesa.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.Adapter.KegiatanAdapter;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Kegiatan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KegiatanFragment extends Fragment {

    private FragmentManager fragmentManager;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;

    private String postUrl = "http://sarpras.laelektronik.com/guide/kegiatan_m";
    private String url = "http://sarpras.laelektronik.com/api/kegiatan";
    private WebView webView;

    ProgressDialog progressDialog;

    List<Kegiatan> kegiatanList = new ArrayList<>();
    RecyclerView recyclerViewKegiatan;

    KegiatanAdapter adapter;

    DividerItemDecoration dividerItemDecoration;

    public KegiatanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_kegiatan, container, false);

        String pesan = getArguments().getString("pesan");
        final int id = getArguments().getInt("id");

        ((MainActivity) getActivity()).setTitleActionBar(pesan);
        ((MainActivity) getActivity()).setSelectedItem(id);

        progressDialog = new ProgressDialog(getContext());

        recyclerViewKegiatan = (RecyclerView) rootView.findViewById(R.id.rc_kegiatan);
        adapter = new KegiatanAdapter(kegiatanList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewKegiatan.setLayoutManager(layoutManager);

        dividerItemDecoration = new DividerItemDecoration(recyclerViewKegiatan.getContext(), LinearLayoutManager.VERTICAL);
        recyclerViewKegiatan.addItemDecoration(dividerItemDecoration);

        recyclerViewKegiatan.setItemAnimator(new DefaultItemAnimator());
        recyclerViewKegiatan.setAdapter(adapter);


        fetchContent();
        // Inflate the layout for this fragment
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
        //      inflater.inflate(R.menu.search, menu);
        inflater.inflate(R.menu.barchart, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==R.id.barchart){

            fragment = new BarchartFragment();
            callFragment(fragment, item.getTitle().toString(), id, 3);
            return true;
        }

        return onOptionsItemSelected(item);
    }

    public void callFragment(Fragment fragment, String s, int id, int i) {
        //Menyisipkan bundle untuk mengeset tiap activity fragment yang dipanggil
        //Bundle bundle = new Bundle();
        //bundle.putString("pesan", s);
        //bundle.putInt("id", id);
        //bundle.putInt("category", i);
        //fragment.setArguments(bundle);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void fetchContent() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressDialog.hide();
                kegiatanList.clear();

                //Log.d("response kegiatan", response.toString());

                try {
                    JSONObject objectKegiatan = response.getJSONObject(0);
                    JSONArray arrayKegiatan = objectKegiatan.getJSONArray("kegiatan");

                    for (int i = 0; i < arrayKegiatan.length(); i++) {
                        JSONObject object = arrayKegiatan.getJSONObject(i);

                        Kegiatan kegiatan = new Kegiatan();

                        kegiatan.setId(object.getInt("id_kegiatan"));
                        kegiatan.setNamaKegiatan(object.getString("nama_kegiatan"));
                        kegiatan.setDesa(object.getString("desa"));
                        kegiatan.setKecamatan(object.getString("kecamatan"));
                        kegiatan.setKabupaten(object.getString("kabupaten"));
                        kegiatan.setProvinsi(object.getString("provinsi"));
                        kegiatan.setPenanggungJawab(object.getString("penanggung_jawab"));
                        kegiatan.setNilaiKontrak(object.getString("nilai_kontrak"));
                        kegiatan.setPelaksana(object.getString("pelaksana"));
                        kegiatan.setSubdit(object.getString("subdit"));

                        kegiatanList.add(kegiatan);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

}