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
import android.widget.Button;
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

    private Context mContext;

    public CustomAdapter(Context context, List<ItemList> items) {
        super(context, 0, items);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final com.example.amk.myfirstapplication.ItemList item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_main, parent, false);
        }
        // Lookup view for data population
        //TextView authorName = (TextView) convertView.findViewById(R.id.author);
        TextView titleName = (TextView) convertView.findViewById(R.id.title);
        TextView descriptionName = (TextView) convertView.findViewById(R.id.description);
        ImageView urlToImageName = (ImageView) convertView.findViewById(urlToImage);

        Button readMore = (Button) convertView.findViewById(R.id.button_loadMore);
        //TextView publishedAtName = (TextView) convertView.findViewById(R.id.publishedAt);

        // Populate the data into the template view using the data object
        //authorName.setText(item.author);
        titleName.setText(item.title);
        descriptionName.setText(item.description);
        readMore.setTag(item.url);

        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(mContext, (String)v.getTag(), Toast.LENGTH_SHORT).show();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                /*builder.setCloseButtonIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.ic_arrow_back));*/
                CustomTabsIntent customTabsIntent = builder.build();

                customTabsIntent.launchUrl(mContext, Uri.parse(item.url));
            }
        });


        Glide.with(convertView.getContext())
                .load(item.urlToImage)
                .thumbnail(0.5f)
                .override(600,200)
                .placeholder(R.drawable.android_logo)
                .crossFade()
                .into(urlToImageName);

        return convertView;

    }

}
