package com.laelektronik.user.portaldesa.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.laelektronik.user.portaldesa.Activity.LaporanActivity;
import com.laelektronik.user.portaldesa.Activity.LoginActivity;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanBulananFragment extends Fragment {

    EditText prog_terakhir, rencana_perbulan, real_bulanan, dev_bulanan, bulan_ke;
    Button kirim_bulanan;
    String pt, rb,realb,db,bk;

    private String server_url = "http://sarpras.laelektronik.com/api/add_laporan_bulanan";

    int idKegiatan;
    ProgressDialog progressDialog;

    public LaporanBulananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_laporan_bulanan, container, false);

        //prog_terakhir = (EditText) rootview.findViewById(R.id.prog_lastmonth);
        rencana_perbulan =(EditText) rootview.findViewById(R.id.plan_every_month);
        real_bulanan = (EditText) rootview.findViewById(R.id.real_bulanan);
        dev_bulanan = (EditText) rootview.findViewById(R.id.dev_bulanan);
        bulan_ke = (EditText) rootview.findViewById(R.id.num_month);
        kirim_bulanan = (Button) rootview.findViewById(R.id.kirim_bulanan);

        progressDialog = new ProgressDialog(getContext());

        idKegiatan = ((LaporanActivity) getActivity()).idKegiatan;

        kirim_bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb = rencana_perbulan.getText().toString();
                realb = real_bulanan.getText().toString();
                db = dev_bulanan.getText().toString();
                bk = bulan_ke.getText().toString();

                if (rb.trim().length() > 0 && realb.trim().length() > 0)  {
                    if (db.trim().length()  > 0){
                        if(bk.trim().length() > 0){
                            kirim();
                        } else {
                            Toast.makeText(getContext() ,"Isi kolom bulan ke-", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext() ,"Isi kolom deviasi bulanan", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getContext() ,"Isi kolom rencana perbulan dan realisasi bulanan", Toast.LENGTH_LONG).show();
                }

            }
        });


        // Inflate the layout for this fragment
        return rootview;
    }

    private void kirim(){
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Log.i("sending", "send data");

        StringRequest request = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response.toString());
                progressDialog.hide();

                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");

                    if (status.equals("success")) {
                        Toast.makeText(getContext(),"Data berhasil ditambah", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getContext(),"Data gagal ditambah", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error: " + error.getMessage());
                Toast.makeText(getContext(),"Data gagal ditambah", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("prog_terakhir", pt);
                params.put("id_kegiatan", String.valueOf(idKegiatan));
                params.put("rencana_bulanan", rb);
                params.put("realisasi_bulanan",realb);
                params.put("defiasi_bulanan",db);
                params.put("bulan_ke",bk);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(request);
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

}
