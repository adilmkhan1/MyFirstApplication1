package com.example.amk.myfirstapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by AdilMateenKhan1 on 13-01-2017.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;

    //Constructor

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {

        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }

}
