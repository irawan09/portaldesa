package com.laelektronik.user.portaldesa.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.laelektronik.user.portaldesa.Activity.BarchartActivity;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KegiatanFragment extends Fragment {


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

        //Maembuat Tabel dinamis
        TableLayout tablelayoutid = (TableLayout) rootView.findViewById(R.id.tablelayoutid);
        for(int i=0;i<3;i++) {
            TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.layout_row, null);
            ((TextView) row.findViewById(R.id.desc_kegiatan)).setText(" "+"Pendukung Ekonomi");
            ((TextView) row.findViewById(R.id.lokasi)).setText(" "+"NTB");
            ((TextView) row.findViewById(R.id.penyedia)).setText(" "+"PT. Nusa Tenggara Barat");
            ((TextView) row.findViewById(R.id.nilai_kontrak)).setText(" "+"100.000.000");
            tablelayoutid.addView(row);
        }

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
        inflater.inflate(R.menu.barchart, menu);
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

            alert.setTitle("Cari Kegiatan");

            // Set an EditText view to get user input
            final EditText txtcari = new EditText(getContext());
            alert.setView(txtcari);
            txtcari.isFocusable();
            alert.setPositiveButton("Cari",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {


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

        if (id==R.id.barchart){

            Intent intent = new Intent(getActivity(), BarchartActivity.class);
            startActivity(intent);
            ((Activity) getActivity()).overridePendingTransition(0,0);
            return true;
        }
        return onOptionsItemSelected(item);
    }
}
