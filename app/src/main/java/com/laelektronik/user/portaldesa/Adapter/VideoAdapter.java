package com.laelektronik.user.portaldesa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.laelektronik.user.portaldesa.Util.MyVideo;

import java.util.List;

/**
 * Created by user on 12/13/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<MyVideo> VideoList;
    Context context;
    private static final String url = "https://www.youtube.com/watch?v=";

    public VideoAdapter(List<MyVideo> postList, Context context) {
        this.VideoList = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyVideo video = VideoList.get(position);
        Log.d("Dari Adapter", VideoList.toString());
        Glide.with(context)
                .load(video.getThumbnails())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

        holder.title.setText(video.getTittle());
        holder.date.setText(video.getTanggal());

        holder.posts_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAppExists = false;
                isAppExists = isInstalled("com.google.android.youtube");
                if(isAppExists=true) {
                    Intent i = new Intent("android.intent.action.VIEW", Uri.parse(url+video.getVideoId()));
                    context.startActivity(i);
                }
                else {
                    Toast.makeText(context, "Segera install aplikasi YouTube", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return VideoList.size();
    }

    private boolean isInstalled(String uri){
        PackageManager pm = context.getPackageManager();
        boolean isIn;
        try{
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
            isIn=true;
        }catch(PackageManager.NameNotFoundException e){
            isIn=false;
        }
        return isIn;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, date;
        LinearLayout posts_list;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.pic_list);
            title = (TextView) itemView.findViewById(R.id.judul_list);
            date = (TextView) itemView.findViewById(R.id.date_list);
            posts_list = (LinearLayout) itemView.findViewById(R.id.video_layout);
        }
    }
}
