package com.laelektronik.user.portaldesa.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.laelektronik.user.portaldesa.Fragment.BeritaDesaFragment;
import com.laelektronik.user.portaldesa.Fragment.BiodataFragment;
import com.laelektronik.user.portaldesa.Fragment.KegiatanFragment;
import com.laelektronik.user.portaldesa.Fragment.LokasiKegiatanFragment;
import com.laelektronik.user.portaldesa.Fragment.PustakaDesaFragment;
import com.laelektronik.user.portaldesa.Fragment.VideoFragment;
import com.laelektronik.user.portaldesa.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String username;

    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    private View navHeader;
    private TextView name, jabatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_search_white_24dp);
        setSupportActionBar(toolbar);

        //Mengeset nama User yang sedang aktif (login)
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        name = (TextView) navHeader.findViewById(R.id.user);
        jabatan = (TextView) navHeader.findViewById(R.id.jabatan);


        Intent in = getIntent();
        username = in.getStringExtra("namauser");
        String useraktif = username.toString();
        name.setText(useraktif);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
           fragment = new BeritaDesaFragment();
           MenuItem menu = navigationView.getMenu().findItem(R.id.home);

           Bundle bundle = new Bundle();
           bundle.putString("pesan", "Kabar Desa");
//           bundle.putInt("id", menu.getItemId());

           fragment.setArguments(bundle);

           fragmentManager = getSupportFragmentManager();
           fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        //getMenuInflater().inflate(R.menu.search, menu);
        //getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

         //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Toast.makeText(getApplicationContext() ,"SEARCH", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.share) {
            Toast.makeText(getApplicationContext() ,"SHARE", Toast.LENGTH_LONG).show();
            return true;
        }

        return onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_berita) {
            fragment = new BeritaDesaFragment();
            callFragment(fragment, item.getTitle().toString(),id,1);
        } else if (id == R.id.nav_video) {
            fragment = new VideoFragment();
            callFragment(fragment, item.getTitle().toString(), id, 2);
        } else if (id == R.id.nav_pustaka) {
            fragment = new PustakaDesaFragment();
            callFragment(fragment, item.getTitle().toString(), id, 3);
        } else if (id == R.id.nav_kegiatan) {
            fragment = new KegiatanFragment();
            callFragment(fragment, item.getTitle().toString(), id, 4);
        } else if (id == R.id.nav_lokasi) {
            fragment = new LokasiKegiatanFragment();
            callFragment(fragment, item.getTitle().toString(), id, 5);
        } else if (id == R.id.nav_about) {
            fragment = new BiodataFragment();
            callFragment(fragment, item.getTitle().toString(), id, 6);
        } else if (id == R.id.nav_logout){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callFragment(Fragment fragment, String s, int id, int i) {
        //Menyisipkan bundle untuk mengeset tiap activity fragment yang dipanggil
        Bundle bundle = new Bundle();
        bundle.putString("pesan", s);
        bundle.putInt("id", id);
        bundle.putInt("category", i);
        fragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    //untuk mengeset tittle action bar agar diambil oleh fragment (beda tittle bar tiap fragment)
    public void setTitleActionBar(String title) {

        getSupportActionBar().setTitle(title);
    }

    public void setSelectedItem(int id) {

        navigationView.getMenu().findItem(id).setChecked(true);
    }


}