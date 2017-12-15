package com.laelektronik.user.portaldesa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        Post post = postList.get(position);
        Glide.with(context)
                .load(post.getImgUrl())
                .thumbnail(0.5f)
                .crossFade()
                .into(holder.thumb);

        holder.title.setText(post.getTitlePost());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView thumb;
        TextView title;
        LinearLayout homeLl;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumb = (ImageView) itemView.findViewById(R.id.home_post_list_image);
            title = (TextView) itemView.findViewById(R.id.home_post_list_title);
            homeLl = (LinearLayout) itemView.findViewById(R.id.home_post_list_ll);
        }
    }

}
