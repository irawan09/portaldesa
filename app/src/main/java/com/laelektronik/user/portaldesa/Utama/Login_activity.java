package com.laelektronik.user.portaldesa.Utama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.laelektronik.user.portaldesa.R;

public class Login_activity extends AppCompatActivity {

    private EditText nama, pass;
    private TextView daftar;
    private Button masuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);

        nama = (EditText) findViewById(R.id.namauser);
        pass = (EditText) findViewById(R.id.password);
        daftar = (TextView) findViewById(R.id.daftar);
        masuk = (Button) findViewById(R.id.submit1);

        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        daftar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(Login_activity.this, Signup.class);
                startActivity(intent);
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = nama.getText().toString();
                String password = pass.getText().toString();

                // mengecek kolom yang kosong
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    Toast.makeText(getApplicationContext() ,"SELAMAT DATANG", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login_activity.this, MainActivity.class);
                    intent.putExtra("namauser", username);
                    startActivity(intent);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
