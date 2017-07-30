package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


      private String TAG = "myapp :";
      private Context mContext = null;
      private TextView mTextView = null;
        private String data = "";

        ArrayList<String> quotes = null;
        ArrayList<String> authors = null;
        JSONObject jObj = new JSONObject();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_card_view);
        Log.v(TAG, "Step1");
        mTextView = (TextView) findViewById(R.id.text1);
        mContext = getApplicationContext();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        Log.v(TAG, "Step2");
        mRecyclerView.setHasFixedSize(true);
        Log.v(TAG, "Step3");
        mLayoutManager = new LinearLayoutManager(this);
        Log.v(TAG, "Step4");
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.v(TAG, "Step5");


        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);



        quotes = new ArrayList<String>();
        authors = new ArrayList<String>();

       // final QuotesAdapter quotesAdapter = new QuotesAdapter(quotes,authors, mContext);
       // ListView listView = (ListView) findViewById(R.id.m_ListView);
       // listView.setAdapter(quotesAdapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=10";
        //http://quotesondesign.com/wp-json/posts?filter[s]=life&filter[posts_per_page]=10  -->use this for searching a particular keyword oriented quote

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        data = response;
                        Log.v(TAG, "jobj  :" + data);
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            int i = jsonArray.length();
                            for (int x = 0; x <= i; x++) {

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

                        mAdapter = new MyRecyclerViewAdapter(getDataSet(quotes,authors));
                        Log.v(TAG, "Step6");
                        mRecyclerView.setAdapter(mAdapter);
                        Log.v(TAG, "Step7");

//                        quotesAdapter.notifyDataSetChanged();


//                        Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
//                        Matcher m = p.matcher(data);
//                        StringBuffer buf = new StringBuffer(data.length());
//                        while (m.find()) {
//                            String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
//                            m.appendReplacement(buf, Matcher.quoteReplacement(ch));
//                        }
//                        int x = buf.indexOf("<p>");
//                        int y = buf.indexOf("/p>");
//                        mTextView.setText("Daily Quotes");
//                        Log.v(TAG, "size  :"+buf.toString());
//                        quotes.add(buf.substring(x+3,y-2));
//                        Log.v(TAG, "size  :"+quotes.size());
//                        mTextViewFGGGText("Response is: "+ response.substring(0,100));


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work! " +error.toString());
            }
        });
//
//
//// Add the request to the RequestQueue.
        queue.add(stringRequest);



                    }




    @Override
    protected void onResume() {
        super.onResume();
//        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
//                .MyClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                Log.i(TAG , " Clicked on Item " + position);
//            }
//        });
    }


    private ArrayList<DataObject> getDataSet(ArrayList<String> q, ArrayList<String> a) {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < q.size(); index++) {
            Log.v(TAG, "getDataSet index: " + index);
            DataObject obj = new DataObject(q.get(index),
                    a.get(index));
            results.add(index, obj);
        }
        return results;
    }




}



