package com.laelektronik.user.portaldesa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.laelektronik.user.portaldesa.Activity.PDFDownloaderActivity;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.MyPDF;

import java.util.List;

/**
 * Created by user on 12/14/2017.
 */

public class PDFReaderAdapter extends RecyclerView.Adapter<PDFReaderAdapter.MyViewHolder>{

    private List<MyPDF> PDFList;
    Context context;
    TextView tv_loading;

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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
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
                String url = pdf.getUrl();
                Intent intent = new Intent(context, PDFDownloaderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", PDFList.get(position).getUrl());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PDFList.size();
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
