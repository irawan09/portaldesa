package com.laelektronik.user.portaldesa.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.laelektronik.user.portaldesa.WebViewClient.MyWebViewClient;
import com.laelektronik.user.portaldesa.Activity.MainActivity;
import com.laelektronik.user.portaldesa.R;

import java.lang.reflect.InvocationTargetException;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouTubeFragment extends Fragment {

    WebView youtube;
    ProgressBar pb;
    String url = "https://www.youtube.com/user/JonasBlueVEVO/videos";


    public YouTubeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        String pesan = getArguments().getString("pesan");
        final int id = getArguments().getInt("id");

        ((MainActivity) getActivity()).setTitleActionBar(pesan);
        ((MainActivity) getActivity()).setSelectedItem(id);

        youtube = (WebView) rootView.findViewById(R.id.YouTubeWeb);

        youtube.loadUrl(url);

        final ProgressDialog progress = ProgressDialog.show(getActivity(), "", "Tunggu sebentar...", true);

        WebSettings webSettings = youtube.getSettings();
        webSettings.setJavaScriptEnabled(true);
        youtube.setWebViewClient(new MyWebViewClient());

        youtube.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                progress.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                progress.dismiss();

                String webUrl = youtube.getUrl();

            }

        });

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
    public void onPause() {
        super.onPause();

        try {
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                    .invoke(youtube, (Object[]) null);

        } catch(ClassNotFoundException cnfe) {

        } catch(NoSuchMethodException nsme) {

        } catch(InvocationTargetException ite) {

        } catch (IllegalAccessException iae) {

        }
    }

    private boolean isInstalled(String uri){
        PackageManager pm = getActivity().getPackageManager();
        boolean isIn;
        try{
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
            isIn=true;
        }catch(PackageManager.NameNotFoundException e){
            isIn=false;
        }
        return isIn;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //membentuk menu dari package menu
        inflater.inflate(R.menu.fullscreen, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.fullscreen) {
            boolean isAppExists = false;
            isAppExists = isInstalled("com.google.android.youtube");
            if(isAppExists=true) {
                Intent i = new Intent("android.intent.action.VIEW", Uri.parse(url));
                startActivity(i);
            }
            else {
                Toast.makeText(getContext(), "Segara install aplikasi YouTube", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return onOptionsItemSelected(item);
    }
}