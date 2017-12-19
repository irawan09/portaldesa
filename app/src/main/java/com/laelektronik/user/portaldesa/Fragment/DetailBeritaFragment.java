package com.laelektronik.user.portaldesa.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;
import com.laelektronik.user.portaldesa.Util.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailBeritaFragment extends Fragment {

    Post post;
    ImageView featured_iamge;
    TextView titel, published, content;

    public DetailBeritaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail__berita, container, false);

        featured_iamge = (ImageView) rootView.findViewById(R.id.featured_image_detail);
        titel = (TextView) rootView.findViewById(R.id.title_detail);
        published = (TextView) rootView.findViewById(R.id.published_detail);
        content = (TextView) rootView.findViewById(R.id.content_detail);

        //String pesan = getArguments().getString("pesan");
        //final int id = getArguments().getInt("id");
        post = (Post) getArguments().getSerializable("post");
        Log.e("post", post.getTitlePost());

        //((MainActivity) getActivity()).setTitleActionBar(pesan);
        //((MainActivity) getActivity()).setSelectedItem(id);

        Glide.with(getContext())
                .load(post.getImgUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(featured_iamge);

        titel.setText(post.getTitlePost());
        published.setText("Berita Desa, "+post.getDate());

        Spanned spanned = Html.fromHtml(post.getPost(), null, null);
        content.setText(spanned);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //membentuk menu dari package menu
        inflater.inflate(R.menu.share, menu);
        return;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            //untuk menambahkan menu share
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBodyText = "http://sarpras.laelektronik.com/berita";
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(intent, "Pilih cara pembagian tautan"));
            return true;
        }

        return onOptionsItemSelected(item);
    }

}
