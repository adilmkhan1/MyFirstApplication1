package com.example.amk.myfirstapplication;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by AdilMateenKhan1 on 23-01-2017.
 */

public class TechFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    ArrayList<HashMap<String, String>> newsList = new ArrayList<>();

    //private SwipeRefreshLayout swipeRefreshLayout;

    AssetManager assetManager;
    InputStream inputStream = null;

    InputStreamReader isr = null;
    BufferedReader input = null;
    String line=null;
    ArrayList<String> list = new ArrayList<>();

    private Context myContext;

    public TechFragment(){ }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }

    @Override
    public void onRefresh() {

        Toast.makeText(this.getActivity(), "Calling onRefresh", Toast.LENGTH_SHORT).show();
        try {
            assetManager = this.getActivity().getAssets();
            inputStream = assetManager.open("RestEndPoints.txt");
            isr = new InputStreamReader(inputStream);
            input= new BufferedReader(isr);

            while ((line = input.readLine()) != null) {
                list.add(line);
            }
            Collections.shuffle(list);
            Toast.makeText(this.getActivity(), "List"+list.size(), Toast.LENGTH_SHORT).show();
            for (String url : list) {
                new TechFragment.MyAsyncTask().execute(url);
            }
        }catch (Exception e)
        {
            Toast.makeText(this.getActivity(), "Got exception", Toast.LENGTH_SHORT).show();
            e.getMessage();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*swipeRefreshLayout = (SwipeRefreshLayout)this.getActivity().findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);*/
        onRefresh();



        /*Toast.makeText(this.getActivity(), "Entering Swipe", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        swipeRefreshLayout.setRefreshing(true);
                                        try {
                                            assetManager = myContext.getAssets();
                                            inputStream = assetManager.open("RestEndPoints.txt");
                                            isr = new InputStreamReader(inputStream);
                                            input = new BufferedReader(isr);

                                            while ((line = input.readLine()) != null) {
                                                list.add(line);
                                            }
                                            Collections.shuffle(list);
                                            for (String url : list) {
                                                new TechFragment.MyAsyncTask().execute(url);
                                            }
                                        } catch (Exception e) {
                                            e.getMessage();
                                        } finally {
                                            try {
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                    }
                                }
        );

        Toast.makeText(this.getActivity(), "Executed Swipe", Toast.LENGTH_SHORT).show();
*/
        return inflater.inflate(R.layout.activity_tech_item, container,false);
    }


    class MyAsyncTask extends AsyncTask<String, Void, String> {

       /* @Override

        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //Start Progress Dialog (Message)

            swipeRefreshLayout.setRefreshing(true);

            // ProgressDialog.show(MainActivity.this, "","Loading");

        }*/

        @Override
        protected String doInBackground(String... urls) {
            //swipeRefreshLayout.setRefreshing(true);
            return RestService.doGet(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            if(RetainData.reuseArray == null)
                RetainData.reuseArray = new ArrayList<>();
            Toast.makeText(getContext(), "Entering Json loop", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(getContext(), "Size:"+newsList.size() , Toast.LENGTH_LONG).show();
                }

                // Construct the data source
                // Create the adapter to convert the array to views
                com.example.amk.myfirstapplication.CustomAdapter adapter = new com.example.amk.myfirstapplication.CustomAdapter(myContext,RetainData.reuseArray);
                // Attach the adapter to a ListView
                ListView listView = (ListView) getView().findViewById(R.id.text_json);

                Toast.makeText(getContext(), ""+RetainData.reuseArray.size(), Toast.LENGTH_SHORT).show();
                listView.setAdapter(adapter);

                //swipeRefreshLayout.setRefreshing(false);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


        }
    }
}
