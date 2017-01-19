package com.example.amk.myfirstapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.configure;
import static com.example.amk.myfirstapplication.R.id.url;
import static com.example.amk.myfirstapplication.R.id.urlToImage;
import static java.lang.System.load;

/**
 * Created by AdilMateenKhan1 on 12-01-2017.
 */

public class CustomAdapter extends ArrayAdapter<com.example.amk.myfirstapplication.ItemList>{

    public CustomAdapter(Context context, List<ItemList> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final com.example.amk.myfirstapplication.ItemList item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item, parent, false);
        }
        // Lookup view for data population
        //TextView authorName = (TextView) convertView.findViewById(R.id.author);
        TextView titleName = (TextView) convertView.findViewById(R.id.title);
        TextView descriptionName = (TextView) convertView.findViewById(R.id.description);
        TextView urlName = (TextView) convertView.findViewById(url);
        //final WebView urlName = (WebView) convertView.findViewById(url);
        //TextView urlToImageName = (TextView) convertView.findViewById(R.id.urlToImage);
        ImageView urlToImageName = (ImageView) convertView.findViewById(urlToImage);
        //TextView publishedAtName = (TextView) convertView.findViewById(R.id.publishedAt);

        // Populate the data into the template view using the data object
        //authorName.setText(item.author);
        titleName.setText(item.title);
        descriptionName.setText(item.description);
        urlName.setText(item.url);

        /// Use a CustomTabsIntent.Builder to configure CustomTabsIntent.

        /*CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(
                getResources(), R.drawable.ic_arrow_back));
        CustomTabsIntent customTabsIntent = builder.build();

        customTabsIntent.launchUrl(convertView.getContext(), Uri.parse(item.url));*/


        //Webview Custom client and settings

        /*urlName.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });*/

       /* urlName.setWebViewClient(new MyWebViewClient());
        urlName.getSettings().setJavaScriptEnabled(true);
        urlName.getSettings().setBuiltInZoomControls(true);
        urlName.getSettings().setLoadWithOverviewMode(true);
        urlName.getSettings().setUseWideViewPort(true);

        urlName.loadUrl(item.url);
*/

        Glide.with(convertView.getContext())
                .load(item.urlToImage)
                .thumbnail(0.5f)
                .crossFade()
                .into(urlToImageName);


        /*Picasso.with(convertView.getContext())
                .load(item.urlToImage)
                .into(urlToImageName);*/


        //new DownloadImageTask(urlToImageName).execute(item.urlToImage);
        //publishedAtName.setText(item.publishedAt);
        // Return the completed view to render on screen
        return convertView;

    }

}
