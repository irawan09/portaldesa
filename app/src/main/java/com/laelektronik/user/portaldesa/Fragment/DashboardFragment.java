package com.laelektronik.user.portaldesa.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laelektronik.user.portaldesa.Activity.LaporanActivity;
import com.laelektronik.user.portaldesa.Activity.LoginActivity;
import com.laelektronik.user.portaldesa.Activity.MapsLaporanActivity;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Kegiatan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    TextView penyelenggara, kegiatant, desa, kec, kab, prov, pagu,
            pemberi_tugas, subdit, penanggung_jawab, kontak_pj, alamat_pj,
            nilai_kontrak, pelaksana, pimpinan_pelaksana, kontak_pelaksana, kontrak,
            tgl_kontrak, mulai_kerja, selesai_kerja;

    Button peta;

    private String url = "http://sarpras.laelektronik.com/api/kegiatan_by_id/";

    int idkegiatan;
    //String pelaksana_kegiatan;

    //SharedPreferences preferences;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ///preferences = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        idkegiatan = ((LaporanActivity) getActivity()).idKegiatan;
        Log.d("idkegiatan", idkegiatan+"");
        //pelaksana = ((LaporanActivity) getActivity()).pelaksana;

        penyelenggara = (TextView) rootview.findViewById(R.id.penyelenggara);
        kegiatant = (TextView) rootview.findViewById(R.id.kegiatan);
        desa = (TextView) rootview.findViewById(R.id.desa);
        kec =(TextView) rootview.findViewById(R.id.kec);
        kab = (TextView) rootview.findViewById(R.id.kab);
        prov = (TextView) rootview.findViewById(R.id.prov);
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

        fetchData();

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

    private void fetchData() {
        Log.d("fetchdata", "oke");
        JsonArrayRequest request = new JsonArrayRequest(url+idkegiatan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("responses", response.toString());

                try {
                    JSONObject objectKegiatan = response.getJSONObject(0);
                    JSONArray arrayKegiatan = objectKegiatan.getJSONArray("kegiatan");

                    //for (int i = 0; i < arrayKegiatan.length(); i++) {
                        JSONObject object = arrayKegiatan.getJSONObject(0);
                        Kegiatan kegiatan = new Kegiatan();

                        kegiatan.setId(object.getInt("id_kegiatan"));
                        kegiatan.setNamaKegiatan(object.getString("nama_kegiatan"));
                        kegiatan.setDesa(object.getString("desa"));
                        kegiatan.setKecamatan(object.getString("kecamatan"));
                        kegiatan.setKabupaten(object.getString("kabupaten"));
                        kegiatan.setProvinsi(object.getString("provinsi"));
                        kegiatan.setNilaiPagu(object.getString("nilai_pagu"));
                        kegiatan.setPemberiTugas(object.getString("pemberi_tugas"));
                        kegiatan.setSubdit(object.getString("subdit"));
                        kegiatan.setPenanggungJawab(object.getString("penanggung_jawab"));
                        kegiatan.setNoHpPj(object.getString("no_hp_pj"));
                        kegiatan.setAlamatPj(object.getString("alamat_pj"));
                        kegiatan.setNilaiKontrak(object.getString("nilai_kontrak"));
                        kegiatan.setPelaksana(object.getString("pelaksana"));
                        kegiatan.setPimpinanPelaksana(object.getString("pimpinan_pelaksana"));
                        kegiatan.setNoHpPelaksana(object.getString("no_hp_pelaksana"));
                        kegiatan.setNoKontrak(object.getString("no_kontrak"));
                        kegiatan.setTglKontrak(object.getString("tgl_kontrak"));
                        kegiatan.setMulaiKerja(object.getString("mulai_kerja"));
                        kegiatan.setSelesaiKerja(object.getString("selesai_kerja"));
                        kegiatan.setLat(object.getDouble("lat"));
                        kegiatan.setLng(object.getDouble("lng"));
                    //}

                    penyelenggara.setText(kegiatan.getPelaksana());
                    kegiatant.setText(" : "+kegiatan.getNamaKegiatan());
                    desa.setText(" : "+kegiatan.getDesa());
                    kec.setText(" : "+kegiatan.getKecamatan());
                    kab.setText(" : "+kegiatan.getKabupaten());
                    prov.setText(" : "+kegiatan.getProvinsi());
                    pagu.setText(" : "+kegiatan.getNilaiPagu());
                    pemberi_tugas.setText(" : "+kegiatan.getPemberiTugas());
                    subdit.setText(" : "+kegiatan.getSubdit());
                    penanggung_jawab.setText(" : "+kegiatan.getPenanggungJawab());
                    kontak_pj.setText(" : "+kegiatan.getNoHpPj());
                    alamat_pj.setText(" : "+kegiatan.getAlamatPj());
                    nilai_kontrak.setText(" : "+kegiatan.getNilaiKontrak());
                    pelaksana.setText(" : "+kegiatan.getPelaksana());
                    pimpinan_pelaksana.setText(" : "+kegiatan.getPimpinanPelaksana());
                    kontak_pelaksana.setText(" : "+kegiatan.getNoHpPelaksana());
                    kontrak.setText(" : "+kegiatan.getNoKontrak());
                    tgl_kontrak.setText(" : "+kegiatan.getTglKontrak());
                    mulai_kerja.setText(" : "+kegiatan.getMulaiKerja());
                    selesai_kerja.setText(" : "+kegiatan.getSelesaiKerja());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Message", "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }

}
