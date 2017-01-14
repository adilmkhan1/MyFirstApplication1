package com.example.amk.myfirstapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.amk.myfirstapplication.R.id.url;
import static com.example.amk.myfirstapplication.R.id.urlToImage;
import static java.lang.System.load;

/**
 * Created by AdilMateenKhan1 on 12-01-2017.
 */

public class CustomAdapter extends ArrayAdapter<ItemList>{

    DownloadImageTask imageLoader;

    public CustomAdapter(Context context, ArrayList<ItemList> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ItemList item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item, parent, false);
        }
        // Lookup view for data population
        //TextView authorName = (TextView) convertView.findViewById(R.id.author);
        TextView titleName = (TextView) convertView.findViewById(R.id.title);
        TextView descriptionName = (TextView) convertView.findViewById(R.id.description);
        TextView urlName = (TextView) convertView.findViewById(url);
        //TextView urlToImageName = (TextView) convertView.findViewById(R.id.urlToImage);
        ImageView urlToImageName = (ImageView) convertView.findViewById(urlToImage);
        TextView publishedAtName = (TextView) convertView.findViewById(R.id.publishedAt);

        // Populate the data into the template view using the data object
        //authorName.setText(item.author);
        titleName.setText(item.title);
        descriptionName.setText(item.description);
        urlName.setText(item.url);

        Picasso.with(convertView.getContext())
                .load(item.urlToImage)
                .into(urlToImageName);


        //new DownloadImageTask(urlToImageName).execute(item.urlToImage);
        publishedAtName.setText(item.publishedAt);
        // Return the completed view to render on screen
        return convertView;

    }

}
