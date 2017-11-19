package com.laelektronik.user.portaldesa;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Signup extends AppCompatActivity {

    private EditText nama, lahir, alamat, instansi, telepon, email, password;
    private Button kirim;
    private Calendar calendar;
    private int year, month, day;
    private String namax, lahirx, alamatx, instansix, teleponx, emailx, passwordx;
    private RadioGroup radioGroupNb;
    private RadioButton radioButtonNb;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.signup_activity);

        nama = (EditText) findViewById(R.id.namauser1);
        lahir = (EditText) findViewById(R.id.lahir1);
        alamat = (EditText) findViewById(R.id.alamat);
        instansi = (EditText) findViewById(R.id.instansi);
        telepon = (EditText) findViewById(R.id.telepon);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        kirim = (Button) findViewById(R.id.submit2);
        radioGroupNb = (RadioGroup) findViewById(R.id.kelamin);

        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        lahir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDateDialog();
            }
        });

        namax = nama.getText().toString();
        lahirx = lahir.getText().toString();
        alamatx = alamat.getText().toString();
        instansix = instansi.getText().toString();
        teleponx = telepon.getText().toString();
        emailx = email.getText().toString();
        passwordx = password.getText().toString();
    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                lahir.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
