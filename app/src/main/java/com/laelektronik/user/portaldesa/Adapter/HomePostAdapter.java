package com.laelektronik.user.portaldesa.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.Fragment.DetailBeritaFragment;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Post;

import java.util.List;

/**
 * Created by doell on 15/12/17.
 */

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.MyViewHolder> {

    private List<Post> postList;
    private Context context;

    public HomePostAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_post_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Post post = postList.get(position);
        Glide.with(context)
                .load(post.getImgUrl())
                .thumbnail(0.5f)
                .crossFade()
                .into(holder.thumb);
        Log.e("title", post.getTitlePost());
        holder.title.setText(post.getTitlePost());
        holder.homeRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new DetailBeritaFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("post", post);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView thumb;
        TextView title;
        RelativeLayout homeRl;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumb = (ImageView) itemView.findViewById(R.id.home_post_list_image);
            title = (TextView) itemView.findViewById(R.id.home_post_list_title);
            homeRl = (RelativeLayout) itemView.findViewById(R.id.home_post_list_ll);
        }
    }

}
