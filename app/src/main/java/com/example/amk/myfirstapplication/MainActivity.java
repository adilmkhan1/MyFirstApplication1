package com.example.amk.myfirstapplication;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import static android.R.attr.data;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.amk.myfirstapplication.R.id.url;
import static com.example.amk.myfirstapplication.R.id.urlToImage;


public class MainActivity extends AppCompatActivity{

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //private ProgressDialog Dialog = new ProgressDialog(this);
   // private SwipeRefreshLayout swipeRefreshLayout;

    //final String url = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apiKey=965663237b734de284aba3914d33b69d";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                //Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(this, AnotherActivity.class);
               // startActivity(intent);
               // button.setVisibility(View.GONE);
                String url = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apiKey=965663237b734de284aba3914d33b69d";
                new MyAsyncTask().execute(url);

            }
        });
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

            //ProgressDialog.show(MainActivity.this, "","Loading");

        }

        @Override
        protected String doInBackground(String... urls) {

            return RestService.doGet(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            ArrayList<HashMap<String, String>> newsList = new ArrayList<>();;

            ArrayList<ItemList> arrayOfItems = new ArrayList<>();

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
                    arrayOfItems.add(new ItemList(author, title, description, url, urlToImage, publishedAt));
                    // Toast.makeText(MainActivity.this, "Size:"+newsList.size() + "\nArticles"+ articles.length(), Toast.LENGTH_LONG).show();
                }

                // Construct the data source
                // Create the adapter to convert the array to views
                CustomAdapter adapter = new CustomAdapter(MainActivity.this, arrayOfItems);
                // Attach the adapter to a ListView
                ListView listView = (ListView) findViewById(R.id.text_json);
                listView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();

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
                Intent intent = new Intent(this, Disclaimer.class);
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
}
