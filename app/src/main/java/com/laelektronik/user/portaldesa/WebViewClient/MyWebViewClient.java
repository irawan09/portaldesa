package com.laelektronik.user.portaldesa.WebViewClient;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by user on 12/9/2017.
 */

public class MyWebViewClient extends WebViewClient {

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}
