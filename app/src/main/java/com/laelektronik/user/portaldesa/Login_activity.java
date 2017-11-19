package com.laelektronik.user.portaldesa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_activity extends AppCompatActivity {

    private EditText nama, password;
    private TextView daftar;
    private Button masuk;

    private String namax, passwordx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);

        nama = (EditText) findViewById(R.id.namauser);
        password = (EditText) findViewById(R.id.password);
        daftar = (TextView) findViewById(R.id.daftar);
        masuk = (Button) findViewById(R.id.submit1);

        namax= nama.getText().toString();
        passwordx = password.getText().toString();

        daftar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Login_activity.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}
