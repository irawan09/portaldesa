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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.Fragment.DetailBeritaFragment;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Post;

import java.util.List;

/**
 * Created by doell on 14/12/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> postList;
    private Context context;

    Fragment fragment = null;

    public PostAdapter(Context context, List<Post> postList) {
        this.postList = postList;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Post post = postList.get(position);

        Log.e("Adapter", postList.toString());

        Glide.with(context)
                .load(post.getImgUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumb);

        holder.title.setText(post.getTitlePost());
        holder.published.setText(post.getDate());
        holder.postList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new DetailBeritaFragment();
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
        TextView title, published;
        LinearLayout postList;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumb = (ImageView) itemView.findViewById(R.id.post_img);
            title = (TextView) itemView.findViewById(R.id.post_title);
            published = (TextView) itemView.findViewById(R.id.post_published);
            postList = (LinearLayout) itemView.findViewById(R.id.post_list);
        }
    }

}
