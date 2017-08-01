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

    private ArrayList<DataObject> mArray = new ArrayList<DataObject>();
    private DownloadQuotes downloadQuotes;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.v(TAG, "LayoutManager set");

        ArrayList<String> q = new ArrayList<String>();
        ArrayList<String> a = new ArrayList<String>();
        q.add("Don't raise your voice, improve your argument");
        a.add("Harvey Spector");
        mArray=getDataSet(q,a);
        Log.v(TAG, "Array initialized");

        mAdapter = new MyRecyclerViewAdapter(mArray);
        Log.v(TAG, "Adapter initialized");

        mRecyclerView.setAdapter(mAdapter);
        Log.v(TAG, "Adapter plugged in- Here we go");
        downloadQuotes = new DownloadQuotes(mContext,this,mArray,mAdapter);
        downloadQuotes.execute();


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


    public ArrayList<DataObject> getDataSet(ArrayList<String> q, ArrayList<String> a) {
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



