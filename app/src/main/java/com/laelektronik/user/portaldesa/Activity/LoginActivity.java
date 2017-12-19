package com.laelektronik.user.portaldesa.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    String server_url = "http://sarpras.laelektronik.com/api/login_pelaksana";

    private EditText nama, pass;
    private Button masuk;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);

        nama = (EditText) findViewById(R.id.namauser);
        pass = (EditText) findViewById(R.id.password);
        masuk = (Button) findViewById(R.id.submit1);

        //setting sharedpreferences untuk autologin
        preferences = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        boolean otoritas = preferences.getBoolean("otoritas", false);
        if (otoritas){
            Intent intent = new Intent(LoginActivity.this, LaporanActivity.class);
            startActivity(intent);
            finish();
        }

        masuk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = nama.getText().toString();
                String password = pass.getText().toString();

                // mengecek kolom yang kosong
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                 /*   final RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                    final String Username, Password;

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.equals("1")) {
                                        Toast.makeText(MainActivity.this, "Login success ...", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, response + "Invalid username or password ...", Toast.LENGTH_LONG).show();
                                    }
                                    requestQueue.stop();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Error ...", Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                            requestQueue.stop();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError{
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            return params;
                        }

                    };
                    requestQueue.add(stringRequest); */
                    //setting data shared preferences

                    //editor = preferences.edit();
                    //editor.putString("username", username);
                    //editor.putBoolean("otoritas", true);
                    //editor.commit();

                    login();

                    //Intent intent = new Intent(LoginActivity.this, LaporanActivity.class);
                    //startActivity(intent);
                    //finish();
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void login() {
        final String pelaksana = nama.getText().toString().trim();
        final String password = pass.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("login", response.toString());

                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    String idKegiatan = object.getString("id_kegiatan");

                    if (status.equals("success")) {
                        editor = preferences.edit();
                        editor.putString("username", pelaksana);
                        editor.putInt("idKegiatan", Integer.parseInt(idKegiatan));
                        editor.putBoolean("otoritas", true);
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, LaporanActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pelaksana", pelaksana);
                params.put("password", password);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(request);
        //oke
    }

}
