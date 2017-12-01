package com.laelektronik.user.portaldesa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Post;

import java.util.List;

/**
 * Created by user on 12/1/2017.
 */

public class CariAdapter extends RecyclerView.Adapter<CariAdapter.MyViewHolder>{
    private List<Post> postList;
    Context context;

    public CariAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cari_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        LinearLayout posts_list;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image_list);
            title = (TextView) itemView.findViewById(R.id.title_list);
            posts_list = (LinearLayout) itemView.findViewById(R.id.posts_list);
        }
    }
}
