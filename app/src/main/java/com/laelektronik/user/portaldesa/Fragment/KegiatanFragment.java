package com.laelektronik.user.portaldesa.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KegiatanFragment extends Fragment {

    private FragmentManager fragmentManager;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;

    private String postUrl = "http://sarpras.laelektronik.com/guide/kegiatan_m";
    private WebView webView;

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

        webView = (WebView) rootView.findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(postUrl);
        webView.setHorizontalScrollBarEnabled(false);

/*
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
*/
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

        //noinspection SimplifiableIfStatement
  /*      if (id == R.id.search) {
            AlertDialog.Builder alert = new AlertDialog.Builder(
                    getContext());

            alert.setTitle("Cari Kegiatan");

            // Set an EditText view to get user input
            final EditText txtcari = new EditText(getContext());
            alert.setView(txtcari);
            txtcari.isFocusable();
            alert.setPositiveButton("Cari",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {


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
*/
        if (id==R.id.barchart){

            fragment = new BarchartFragment();
            callFragment(fragment, item.getTitle().toString(), id, 3);
            return true;
        }
        return onOptionsItemSelected(item);
    }

    public void callFragment(Fragment fragment, String s, int id, int i) {
        //Menyisipkan bundle untuk mengeset tiap activity fragment yang dipanggil
        Bundle bundle = new Bundle();
        bundle.putString("pesan", s);
        bundle.putInt("id", id);
        bundle.putInt("category", i);
        fragment.setArguments(bundle);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }

}
