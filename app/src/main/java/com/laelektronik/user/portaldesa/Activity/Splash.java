package com.laelektronik.user.portaldesa.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.laelektronik.user.portaldesa.R;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        ConnectivityManager cm = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }, 3000L);
        } else {
            alertboxwifi("Koneksi Internet off", "Hidupkan Koneksi Internet");
        }
    }

    protected void alertboxwifi(String title, String mymessage) {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle("Aktifkan internet");
        localBuilder.setMessage("Aplikasi ini membutuhkan koneksi internet!");
        localBuilder.setPositiveButton("Reload",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface,
                                        int paramInt) {

                        Splash.this.finish();
                        Splash.this.startActivity(getIntent());
                    }
                });

        localBuilder.setNegativeButton("Keluar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface,
                                        int paramInt) {
                        Splash.this.finish();

                    }
                });
        localBuilder.show();
        localBuilder.setCancelable(true);
    }
}
