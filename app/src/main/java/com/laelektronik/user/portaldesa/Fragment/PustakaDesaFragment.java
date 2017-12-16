package com.laelektronik.user.portaldesa.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.laelektronik.user.portaldesa.Adapter.PDFReaderAdapter;
import com.laelektronik.user.portaldesa.Adapter.VideoAdapter;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.MyPDF;
import com.laelektronik.user.portaldesa.Util.MyVideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.laelektronik.user.portaldesa.Controller.AppController.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class PustakaDesaFragment extends Fragment {

    private static final String url = "http://sarpras.laelektronik.com/api/post/pustaka";
    List<MyPDF> PDFList = new ArrayList<>();
    PDFReaderAdapter adapter;
    RecyclerView recyclerView;

    public PustakaDesaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pustaka_desa, container, false);

        String pesan = getArguments().getString("pesan");
        final int id = getArguments().getInt("id");

        ((MainActivity) getActivity()).setTitleActionBar(pesan);
        ((MainActivity) getActivity()).setSelectedItem(id);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.pdf_list);

        adapter = new PDFReaderAdapter(PDFList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

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
        inflater.inflate(R.menu.search, menu);
        return;
    }

    @Override
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
                PDFList.clear();
                loading.dismiss();
                Log.d(TAG, response.toString());

                    try {
                        JSONObject object = response.getJSONObject(0);
                        JSONArray jsonArray = object.getJSONArray("post");

                        for (int i=0; i<jsonArray.length(); i++ ){
                            JSONObject objectpdf = jsonArray.getJSONObject(i);
                            MyPDF pdf = new MyPDF();
                            pdf.setThumbnails(objectpdf.getString("feature_image"));
                            pdf.setTittle(objectpdf.getString("title_post"));
                            pdf.setUrl(objectpdf.getString("file"));
                            pdf.setTanggal(objectpdf.getString("tgl_post"));

                            PDFList.add(pdf);
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
