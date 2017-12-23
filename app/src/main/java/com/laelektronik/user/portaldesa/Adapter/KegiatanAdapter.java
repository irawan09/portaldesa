package com.laelektronik.user.portaldesa.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.Fragment.DetailKegiatanFragment;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Kegiatan;

import java.util.List;

/**
 * Created by doell on 20/12/17.
 */

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.MyViewHolder> {

    List<Kegiatan> kegiatanList;
    Context context;

    public KegiatanAdapter(List<Kegiatan> kegiatanList, Context context) {
        this.kegiatanList = kegiatanList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.kegiatan_list, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Kegiatan kegiatan = kegiatanList.get(position);
        holder.tv.setText(kegiatan.getNamaKegiatan());
        holder.sub.setText(kegiatan.getProvinsi());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new DetailKegiatanFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("kegiatan", kegiatan);
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
        return kegiatanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rl;
        TextView tv;
        TextView sub;

        public MyViewHolder(View itemView) {
            super(itemView);

            rl = (LinearLayout) itemView.findViewById(R.id.rl_kegiatan);
            tv = (TextView) itemView.findViewById(R.id.text_kegiatan);
            sub = (TextView) itemView.findViewById(R.id.sub);
        }
    }

}

