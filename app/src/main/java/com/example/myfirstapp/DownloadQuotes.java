package com.example.myfirstapp;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Asus on 31-07-2017.
 */


public class DownloadQuotes extends AsyncTask<Void, Void, ArrayList> {

    private String TAG = "myapp async :";


    private ArrayList<DataObject> mArray;
    private MainActivity mainActivity;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<String> quotes = new ArrayList<String>();
    private ArrayList<String> authors = new ArrayList<String>();
    private ArrayList<String> images = new ArrayList<>();
    private RequestQueue queue;
    private String url = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=10";
    //http://quotesondesign.com/wp-json/posts?filter[s]=life&filter[posts_per_page]=10  -->use this for searching a particular keyword oriented quote



    public DownloadQuotes(Context mContext, ArrayList<String> images, ArrayList<DataObject> mArray, RecyclerView.Adapter mAdapter) {

        this.mainActivity = new MainActivity();
        this.mArray = mArray;
        this.images = images;
        this.mAdapter = mAdapter;
        this.queue = Volley.newRequestQueue(mContext);
    }

    @Override
    protected ArrayList<DataObject> doInBackground(Void... params) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.


                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            int i = jsonArray.length();
                            for (int x = 0; x < i; x++) {

                                JSONObject jObj = jsonArray.getJSONObject(x);

                                try {
                                    String parsedQuote = jObj.getString("content").replaceAll("</p>", "");
                                    parsedQuote = parsedQuote.replaceAll("<p>", "");
                                    quotes.add(parsedQuote);
                                    authors.add(jObj.getString("title"));
                                    Log.v(TAG, "jobj  :" + jObj.getString("content"));
                                    Log.v(TAG, "jobj  :" + jObj.getString("title"));
                                    } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        mArray.addAll(mainActivity.getDataSet(quotes,authors,images));
                        mAdapter.notifyDataSetChanged();
                        Log.v(TAG, "adapter notified");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

//// Add the request to the RequestQueue.
        queue.add(stringRequest);
        return null;
    }





    protected void onProgressUpdate(Integer... progress) {

    }


    @Override
    protected void onPostExecute(ArrayList result)
    {
        Log.v(TAG, "Quotes done");
    }



}
