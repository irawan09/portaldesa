package com.laelektronik.user.portaldesa.Fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.laelektronik.user.portaldesa.Activity.LaporanActivity;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanMingguanFragment extends Fragment {

    private Uri mImageCaptureUri;
    private ImageView mImageView;

    EditText rencana_perminggu, real_mingguan, dev_mingguan, minggu_ke;
    Button kirim_minggu;
    protected String rb,realb,db,bk, gambar_base64;
    Bitmap gambarUpload;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;

    private String server_url = "http://sarpras.laelektronik.com/api/add_laporan_mingguan";

    int idKegiatan;

    TextView textGambar;
    ProgressDialog progressDialog;

    public LaporanMingguanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_laporan_mingguan, container, false);

        rencana_perminggu = (EditText) rootview.findViewById(R.id.plan_every_week);
        real_mingguan = (EditText) rootview.findViewById(R.id.real_mingguan);
        dev_mingguan = (EditText) rootview.findViewById(R.id.dev_mingguan);
        minggu_ke = (EditText) rootview.findViewById(R.id.num_week);
        kirim_minggu = (Button) rootview.findViewById(R.id.kirim_mingguan);
        textGambar = (TextView) rootview.findViewById(R.id.text_gambar);

        idKegiatan = ((LaporanActivity) getActivity()).idKegiatan;
        gambar_base64 = "";

        progressDialog = new ProgressDialog(getContext());

        final String [] items           = new String [] {"From Camera", "From SD Card"};
        ArrayAdapter<String> adapter    = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item,items);
        AlertDialog.Builder builder     = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Image");
        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int item ) {
                if (item == 0) {
                    Intent intent    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    try {
                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dialog.cancel();
                } else {
                    Intent intent = new Intent();

                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
                }
            }
        } );

        final AlertDialog dialog = builder.create();

        mImageView = (ImageView) rootview.findViewById(R.id.iv_pic);

        ((ImageView) rootview.findViewById(R.id.iv_pic)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        kirim_minggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb = rencana_perminggu.getText().toString();
                realb = real_mingguan.getText().toString();
                db = dev_mingguan.getText().toString();
                bk = minggu_ke.getText().toString();

                if (rb.trim().length() > 0 && realb.trim().length() > 0)  {
                    if (db.trim().length()  > 0){
                        if(bk.trim().length() > 0){
                            if (gambar_base64.trim().length() > 0){
                                kirim();
                            } else {
                                Toast.makeText(getContext() ,"Pilih gambar dahulu", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getContext() ,"Isi baris minggu ke-", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext() ,"Isi baris deviasi mingguan", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext() ,"Isi baris rencana mingguan dan realisasi mingguan", Toast.LENGTH_LONG).show();
                }

            }
        });

        // Inflate the layout for this fragment
        return rootview;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        Bitmap bitmap   = null;
        String path     = "";

        if (requestCode == PICK_FROM_FILE) {
            mImageCaptureUri = data.getData();
            path = getRealPathFromURI(mImageCaptureUri); //from Gallery

            if (path == null)
                path = mImageCaptureUri.getPath(); //from File Manager

            if (path != null)
                bitmap  = BitmapFactory.decodeFile(path);
        } else {

            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            
        }

        mImageView.setImageBitmap(bitmap);
        gambarUpload=bitmap;
        textGambar.setVisibility(View.GONE);
        gambar_base64 = base64(gambarUpload);
    }

    public String getRealPathFromURI(Uri contentUri) {
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor       = getActivity().managedQuery( contentUri, proj, null, null,null);

        if (cursor == null) return null;

        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    protected String base64(Bitmap gambar){
        gambarUpload=gambar;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gambar.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return imageString;
    }

    private void kirim(){
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("login", response.toString());
                progressDialog.hide();

                try {
                    JSONObject object = new JSONObject(response);
                    Log.e("object", object.toString());

                    String status = object.getString("status");
                    Log.e("status", status);

                    if (status.equals("success")) {
                        Toast.makeText(getContext(),"Berhasil Menambahkan Data", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getContext(),"Gagal Menambahkan Data", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Gagal Menambahkan Data", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kegiatan", String.valueOf(idKegiatan));
                params.put("rencana_mingguan", rb);
                params.put("realisasi_mingguan",realb);
                params.put("defiasi_mingguan",db);
                params.put("minggu_ke",bk);
                params.put("image",  base64(gambarUpload));

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //membentuk menu dari package menu
        inflater.inflate(R.menu.logout, menu);
        return;
    }
}
