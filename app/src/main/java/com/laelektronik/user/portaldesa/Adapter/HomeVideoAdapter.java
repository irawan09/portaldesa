package com.laelektronik.user.portaldesa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.MyVideo;

import java.util.List;

/**
 * Created by doell on 16/12/17.
 */

public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.MyViewHolder> {

    private List<MyVideo> videoList;
    private Context context;
    private static final String url = "https://www.youtube.com/watch?v=";

    public HomeVideoAdapter(List<MyVideo> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_video_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyVideo video = videoList.get(position);

        Glide.with(context)
                .load(video.getThumbnails())
                .thumbnail(0.5f)
                .crossFade()
                .into(holder.thumb);

        holder.title.setText(video.getTittle());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        return videoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView thumb;
        TextView title;
        RelativeLayout rl;

        public MyViewHolder(View itemView) {
            super(itemView);

            thumb = (ImageView) itemView.findViewById(R.id.home_video_list_img);
            title = (TextView) itemView.findViewById(R.id.home_video_list_title);
            rl = (RelativeLayout) itemView.findViewById(R.id.home_vide_list_rl);
        }
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

}
