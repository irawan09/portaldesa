package com.laelektronik.user.portaldesa.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laelektronik.user.portaldesa.Adapter.VideoAdapter;
import com.laelektronik.user.portaldesa.Controller.AppController;
import com.laelektronik.user.portaldesa.Util.MyVideo;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.laelektronik.user.portaldesa.Controller.AppController.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouTubeFragment extends Fragment {

    WebView youtube;
    ProgressBar pb;
    private static final String url = "http://sarpras.laelektronik.com/api/video/";
    List<MyVideo> VideoList = new ArrayList<>();
    VideoAdapter adapter;
    RecyclerView recyclerView;

    public YouTubeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        String pesan = getArguments().getString("pesan");
        final int id = getArguments().getInt("id");

        ((MainActivity) getActivity()).setTitleActionBar("Video");
        ((MainActivity) getActivity()).setSelectedItem(id);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.video_list);

        adapter = new VideoAdapter(VideoList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        fetchContent();

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && youtube.canGoBack()) {
            youtube.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return onKeyDown(keyCode, event);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return onOptionsItemSelected(item);
    }

    private void fetchContent() {
        final ProgressDialog loading;
        loading = ProgressDialog.show(getContext(), "Mendownload Data", "Tunggu...",false,false);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                VideoList.clear();
                loading.dismiss();
                Log.d(TAG, response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        MyVideo video = new MyVideo();
                        video.setThumbnails(object.getString("thumbnails"));
                        video.setTittle(object.getString("title"));
                        video.setVideoId(object.getString("videoId"));
                        video.setTanggal(object.getString("publishedAt"));

                        VideoList.add(video);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }
}