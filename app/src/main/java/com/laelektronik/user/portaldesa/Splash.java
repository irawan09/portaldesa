package com.laelektronik.user.portaldesa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        ConnectivityManager cm = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            Toast.makeText(getApplication(), "Anda terhubung ke "+netInfo.getTypeName()+" "+netInfo.getSubtypeName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplication(), "Cek Koneksi Internetmu !", Toast.LENGTH_SHORT).show();
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Login_activity.class));
                finish();
            }
        }, 3000L);

    }
}
