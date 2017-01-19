package com.example.amk.myfirstapplication;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by AdilMateenKhan1 on 19-01-2017.
 */

public class CustomWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {


        return super.shouldOverrideUrlLoading(view, url);
    }
}
