package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    private String TAG = "myapp :";

    private String imageurl = "https://pixabay.com/api/?key=6072887-ff96fbe049fe5ddaf47bf86b1&q=forest+sunlight+sea+sky&image_type=photo&orientation=horizontal&min_width=2500&min_height=1800&order=popular&category=nature";
    private ImageView bgimage;

    private ArrayList<DataObject> mArray = new ArrayList<DataObject>();
    private ArrayList<String> images = new ArrayList<String>();
    private DownloadQuotes downloadQuotes;
    private  ImageLoadTask imageLoadTask;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static Context mContext;
    public static Typeface tf1;
    public static Typeface tf2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        //setContentView(R.layout.card_view);

        bgimage = (ImageView) findViewById(R.id.bgimageView) ;

        tf1 = Typeface.createFromAsset(getAssets(),
                "fonts/champagne&limousines.ttf");
        tf2 = Typeface.createFromAsset(getAssets(),
                "fonts/chasingcars.ttf");



        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.v(TAG, "LayoutManager set");

        OnVerticalScroll onVerticalScroll =new OnVerticalScroll();


//        ArrayList<String> q = new ArrayList<String>();
//        ArrayList<String> a = new ArrayList<String>();
//        q.add("Don't raise your voice, improve your argument");
//        a.add("Harvey Spector");
        mArray= new ArrayList<>();//getDataSet(q,a);
        Log.v(TAG, "Array initialized");

        mAdapter = new MyRecyclerViewAdapter(mArray);
        Log.v(TAG, "Adapter initialized");

        mRecyclerView.setAdapter(mAdapter);
        Log.v(TAG, "Adapter plugged in- Here we go");

        mRecyclerView.addOnScrollListener(onVerticalScroll);

        Log.v(TAG, "image view in main thread =" + bgimage);

        imageLoadTask= new ImageLoadTask(imageurl,bgimage,mContext,images);
        //imageLoadTask.execute();

        downloadQuotes = new DownloadQuotes(mContext,images,mArray,mAdapter);
       // downloadQuotes.execute();

        loaddata();

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


    public ArrayList<DataObject> getDataSet(ArrayList<String> q, ArrayList<String> a, ArrayList<String> images) {
        ArrayList results = new ArrayList<DataObject>();

        for (int index = mArray.size(); index < mArray.size()+ q.size(); index++) {
            Log.v(TAG, "getDataSet index: " + index);
            DataObject obj = new DataObject(q.get(index), a.get(index), images.get(index));
            results.add(index, obj);
        }
        return results;
    }

    public void loaddata()
    {
        imageLoadTask.execute();
        downloadQuotes.execute();

    }



}



