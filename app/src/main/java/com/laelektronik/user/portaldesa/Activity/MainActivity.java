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

import com.laelektronik.user.portaldesa.Fragment.HomeFragment;
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
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
           fragment = new HomeFragment();

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_berita) {
            // Handle the camera action
            fragment = new HomeFragment();
            callFragment(fragment, item.getTitle().toString(),id,0);
        } else if (id == R.id.nav_video) {
            fragment = new VideoFragment();
            callFragment(fragment, item.getTitle().toString(), id, 1);
        } else if (id == R.id.nav_pustaka) {
            fragment = new PustakaDesaFragment();
            callFragment(fragment, item.getTitle().toString(), id, 2);
        } else if (id == R.id.nav_kegiatan) {
            fragment = new KegiatanFragment();
            callFragment(fragment, item.getTitle().toString(), id, 3);
        } else if (id == R.id.nav_lokasi) {
            fragment = new LokasiKegiatanFragment();
            callFragment(fragment, item.getTitle().toString(), id, 4);
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callFragment(Fragment fragment, String s, int id, int i) {
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

    public void setTitleActionBar(String title) {

        getSupportActionBar().setTitle("Ary");
    }

    public void setSelectedItem(int id) {
        navigationView.getMenu().findItem(id).setChecked(true);
    }


}