package com.example.amk.myfirstapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by AdilMateenKhan1 on 16-01-2017.
 */

public class MyWebViewClient extends WebViewClient {

    boolean loadingFinished = true;
    boolean redirect = false;

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {

        loadingFinished = false;
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (!loadingFinished) {
            redirect = true;
        }

        loadingFinished = false;
        view.loadUrl(url);
        return true;

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if(!redirect){
            loadingFinished = true;
        }

        if(loadingFinished && !redirect){
            //HIDE LOADING IT HAS FINISHED
        } else{
            redirect = false;
        }

    }

    }

