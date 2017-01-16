package com.example.amk.myfirstapplication;


import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import static android.R.attr.button;
import static android.R.attr.data;
import static android.R.attr.path;
import static android.R.id.input;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.media.CamcorderProfile.get;
import static com.example.amk.myfirstapplication.R.id.url;
import static com.example.amk.myfirstapplication.R.id.urlToImage;


public class MainActivity extends AppCompatActivity{

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private RetainedFragment dataFragment;

    private GoogleApiClient client;
    AssetManager assetManager;
    InputStream inputStream = null;

    InputStreamReader isr = null;
    BufferedReader input = null;
    String line=null;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // find the retained fragment on activity restarts
       /* FragmentManager fm = getFragmentManager();
        dataFragment = (DataFragment) fm.findFragmentByTag(“data”);

        // create the fragment and data the first time
        if (dataFragment == null) {
            // add the fragment
            dataFragment = new DataFragment();
            fm.beginTransaction().add(dataFragment, “data”).commit();
            // load the data from the web
            dataFragment.setData(loadMyData());
        }*/

        // the data is available in dataFragment.getData()



        // final String url = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apiKey=965663237b734de284aba3914d33b69d";

        //swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        //swipeRefreshLayout.setOnRefreshListener(this);


        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
       /* swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        new MyAsyncTask().execute(String.valueOf(url));

                                    }
                                }*/


        Button button = (Button) findViewById(R.id.button_click);
        //Button buttonLoadMore = (Button) findViewById(R.id.button_loadMore);
        //button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                //Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(this, AnotherActivity.class);
               // startActivity(intent);
               // button.setVisibility(View.GONE);


                //Read Rest End Points URL from text file

                try {
                    assetManager = getAssets();
                    inputStream = assetManager.open("RestEndPoints.txt");
                    isr = new InputStreamReader(inputStream);
                    input= new BufferedReader(isr);

                    while ((line = input.readLine()) != null) {
                        list.add(line);
                    }
                        Collections.shuffle(list);
                        for (String url : list) {
                            new MyAsyncTask().execute(url);
                        }
                    }catch (Exception e)
                {
                    e.getMessage();
                }
                finally {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        /*buttonLoadMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something

            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction0() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }*/


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }


   /*@Override
    public void onRefresh() {
        final String url = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apiKey=965663237b734de284aba3914d33b69d";
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                new MyAsyncTask().execute(url);
            }
        }, 5000);
    }
*/

    class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override

        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //Start Progress Dialog (Message)

           // ProgressDialog.show(MainActivity.this, "","Loading");

        }

        @Override
        protected String doInBackground(String... urls) {

            return RestService.doGet(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            ArrayList<HashMap<String, String>> newsList = new ArrayList<>();



            if(RetainData.reuseArray == null)
                RetainData.reuseArray = new ArrayList<>();

            try {

                JSONObject json = new JSONObject(result);
                JSONArray articles = (JSONArray) json.get("articles");

                for (int i=0;i< articles.length();i++)
                {
                    JSONObject c = (JSONObject) articles.get(i);
                    String author = c.getString("author");
                    String title = c.getString("title");
                    String description = c.getString("description");
                    String url = c.getString("url");
                    String urlToImage = c.getString("urlToImage");
                    String publishedAt = c.getString("publishedAt");

                    HashMap<String, String> hm = new HashMap<>();
                    hm.put("author",author);
                    hm.put("title",title);
                    hm.put("description",description);
                    hm.put("url",url);
                    hm.put("urlToImage",urlToImage);
                    hm.put("publishedAt",publishedAt);

                    newsList.add(hm);
                    RetainData.reuseArray.add(new com.example.amk.myfirstapplication.ItemList(author, title, description, url, urlToImage, publishedAt));
                    // Toast.makeText(MainActivity.this, "Size:"+newsList.size() + "\nArticles"+ articles.length(), Toast.LENGTH_LONG).show();
                }

                // Construct the data source
                // Create the adapter to convert the array to views
                com.example.amk.myfirstapplication.CustomAdapter adapter = new com.example.amk.myfirstapplication.CustomAdapter(MainActivity.this,RetainData.reuseArray);
                // Attach the adapter to a ListView
                ListView listView = (ListView) findViewById(R.id.text_json);

             /*   // Creating a button - Load More
                Button btnLoadMore = new Button(MainActivity.this);
                btnLoadMore.setText("Load More");

                // Adding button to listview at footer
                listView.addFooterView(btnLoadMore);*/

                listView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();

                //Listening to Load More button click event
               /* btnLoadMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        // Starting a new async task
                        new loadMoreListView().execute();
                    }
                });*/


            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*/
        //searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Will add soon", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.action_about:
                Intent intent = new Intent(this, com.example.amk.myfirstapplication.Disclaimer.class);
                startActivity(intent);
                return true;


            case R.id.action_exit:

                finish();
                System.exit(0);

            default:

                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction0());
    }

    @Override
    public void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction0());
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // store the data in the fragment
        //dataFragment.setData(collectMyLoadedData());
    }
}
