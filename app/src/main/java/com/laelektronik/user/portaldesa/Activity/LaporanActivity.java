package com.laelektronik.user.portaldesa.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.laelektronik.user.portaldesa.Fragment.DashboardFragment;
import com.laelektronik.user.portaldesa.Fragment.LaporanBulananFragment;
import com.laelektronik.user.portaldesa.Fragment.LaporanMingguanFragment;
import com.laelektronik.user.portaldesa.R;

import java.util.ArrayList;
import java.util.List;

public class LaporanActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private int idKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Mengeset nama User yang sedang aktif (login) menggunakan sharedpreferences
        preferences = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        //username = preferences.getString("username", null);
        idKegiatan = preferences.getInt("idKegiatan", 0);
        Log.d("id kegiatan ", idKegiatan+"");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DashboardFragment(), "Kegiatan");
        adapter.addFragment(new LaporanMingguanFragment(), "Laporan Mingguan");
        adapter.addFragment(new LaporanBulananFragment(), "Laporan Bulanan");
        viewPager.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            editor = preferences.edit();
            editor.clear().commit();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

            return true;
        }
        return onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
