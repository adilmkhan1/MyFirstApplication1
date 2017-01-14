package com.example.amk.myfirstapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;

/**
 * Created by AdilMateenKhan1 on 14-01-2017.
 */

public class CustomMemoryCache {

    LruCache<String, Bitmap> mMemoryCache;
    public void onCreate(Bundle b)
    {
        //.....
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
        //....
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
