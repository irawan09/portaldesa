package com.laelektronik.user.portaldesa.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.MyPDF;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by user on 12/14/2017.
 */

public class PDFReaderAdapter extends RecyclerView.Adapter<PDFReaderAdapter.MyViewHolder>{

    private List<MyPDF> PDFList;
    Context context;
    int downloadedSize = 0, totalsize;
    TextView tv_loading;
    float per = 0;

    public PDFReaderAdapter(List<MyPDF> PDFList, Context context) {
        this.PDFList = PDFList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdfreader, parent, false);

        tv_loading = new TextView(itemView.getContext());
        tv_loading.setGravity(Gravity.CENTER);
        tv_loading.setTypeface(null, Typeface.BOLD);

        return new PDFReaderAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyPDF pdf = PDFList.get(position);
        Log.d("Dari Adapter", PDFList.toString());
        Glide.with(context)
                .load(pdf.getThumbnails())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

        holder.title.setText(pdf.getTittle());
        holder.date.setText(pdf.getTanggal());

        holder.posts_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAndOpenPDF(pdf);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PDFList.size();
    }

    void downloadAndOpenPDF(final MyPDF url) {
        new Thread(new Runnable() {
            public void run() {
                Uri path = Uri.fromFile(downloadFile(url.getUrl()));
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);

                } catch (ActivityNotFoundException e) {
                    tv_loading
                            .setError("PDF Reader application is not installed in your device");
                }
            }
        }).start();
    }

    File downloadFile(String dwnload_file_path) {
        File file = null;
        try {

            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            // connect
            urlConnection.connect();

            // set the path where we want to save the file
            File SDCardRoot = Environment.getExternalStorageDirectory();
            // create a new file, to save the downloaded file
            file = new File(String.valueOf(SDCardRoot));

            FileOutputStream fileOutput = new FileOutputStream(file);

            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            // this is the total size of the file which we are
            // downloading
            totalsize = urlConnection.getContentLength();
            Toast.makeText(context,"Starting PDF download...",Toast.LENGTH_LONG).show();

            // create a buffer...
            byte[] buffer = new byte[1024 * 1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                per = ((float) downloadedSize / totalsize) * 100;
                Toast.makeText(context,"Total PDF File size  : "+(totalsize / 1024)+" KB\n\nDownloading PDF "+(int) per+"% complete", Toast.LENGTH_LONG).show();
            }
            // close the output stream when complete //
            fileOutput.close();
            Toast.makeText(context,"Download Complete. Open PDF Application installed in the device.",Toast.LENGTH_LONG).show();

        } catch (final MalformedURLException e) {
            Toast.makeText(context,"Some error occured. Press back and try again.",Toast.LENGTH_LONG).show();
        } catch (final IOException e) {
            Toast.makeText(context,"Some error occured. Press back and try again.",Toast.LENGTH_LONG).show();
        } catch (final Exception e) {
            Toast.makeText(context, "Failed to download image. Please check your internet connection.",Toast.LENGTH_LONG).show();
        }
        return file;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, date;
        LinearLayout posts_list;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.thumbnails_list);
            title = (TextView) itemView.findViewById(R.id.judul1_list);
            date = (TextView) itemView.findViewById(R.id.date1_list);
            posts_list = (LinearLayout) itemView.findViewById(R.id.pdf_layout);
        }
    }
}
