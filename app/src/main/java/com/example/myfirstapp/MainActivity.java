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
    private int trip = 0 ;

    private String imageurl = "https://pixabay.com/api/?key=6072887-ff96fbe049fe5ddaf47bf86b1&q=forest&per_page=200&image_type=photo&orientation=horizontal&min_width=2500&min_height=1800&order=popular&category=nature";
    private ImageView bgimage;

    private ArrayList<DataObject>       mArray          = new ArrayList<DataObject>();
    private ArrayList<String>           images          = new ArrayList<String>();
    private DownloadQuotes              downloadQuotes;
    private ImageLoadTask               imageLoadTask;
    private RecyclerView                mRecyclerView;
    private RecyclerView.Adapter        mAdapter;
    private RecyclerView.LayoutManager  mLayoutManager;
    private OnVerticalScroll            onVerticalScroll;

    public static Context   mContext;
    public static Typeface  tf1;
    public static Typeface  tf2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        bgimage = (ImageView) findViewById(R.id.bgimageView) ;

        tf1 = Typeface.createFromAsset(getAssets(),
                "fonts/champagne&limousines.ttf");
        tf2 = Typeface.createFromAsset(getAssets(),
                "fonts/chasingcars.ttf");

        mContext            = getApplicationContext();
        mRecyclerView       = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager      = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.v(TAG, "LayoutManager set");

        mAdapter            = new MyRecyclerViewAdapter(mArray);
        Log.v(TAG, "Adapter initialized");

        mRecyclerView.setAdapter(mAdapter);
        Log.v(TAG, "Adapter plugged in");

        onVerticalScroll    = new OnVerticalScroll(this);
        mRecyclerView.addOnScrollListener(onVerticalScroll);
        Log.v(TAG, "OnVerticalScroll listener set");

        imageLoadTask       = new ImageLoadTask(imageurl,bgimage,mContext,images,this);
        imageLoadTask.execute();
        Log.v(TAG, "Images Downloaded");

    }




    @Override
    protected void onResume() {
        super.onResume();

    }


    public ArrayList<DataObject> getDataSet(ArrayList<String> q, ArrayList<String> a, ArrayList<String> images) {
        ArrayList results = new ArrayList<DataObject>();
        int x = mArray.size();
        Log.v(TAG, "getmArray size: " + mArray.size());
        Log.v(TAG, "x = " + x);
        Log.v(TAG, "images size = " + images.size());


        for (int index = 0; index < q.size(); index++) {
            Log.v(TAG, "getDataSet index: " + index);
            DataObject obj = new DataObject(q.get(index), a.get(index), images.get(x + index));
            results.add(index, obj);
        }

        trip = 1;
        return results;
    }

    public void loaddata()
    {

        downloadQuotes = new DownloadQuotes(mContext,this,images,mArray,mAdapter);
        downloadQuotes.execute();

    }

    public void emptydata()
    {
        mArray.clear();
    }



}



