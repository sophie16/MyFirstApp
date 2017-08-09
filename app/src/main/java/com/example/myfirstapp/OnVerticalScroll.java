package com.example.myfirstapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Asus on 07-08-2017.
 */

public class OnVerticalScroll extends RecyclerView.OnScrollListener {

    private String TAG = "myapp :";
    private MainActivity mainActivity;

    public OnVerticalScroll (MainActivity mainActivity){

        this.mainActivity=mainActivity;
    }

    @Override
    public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop(dy,recyclerView);
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom(dy,recyclerView);
        }
//        if (dy < 0) {
//            onScrolledUp(dy);
//        } else if (dy > 0) {
//            onScrolledDown(dy);
//        }
    }

    public void onScrolledUp(int dy) {
        Log.v(TAG, "onScrolledUp("+dy+")");
        //onScrolledUp();
    }

    public void onScrolledDown(int dy) {
        Log.v(TAG, "onScrolledDown("+dy+")");
        //onScrolledDown();
    }

    public void onScrolledUp() {
        Log.v(TAG, "onScrolledUp()");
    }

    public void onScrolledDown() {
        Log.v(TAG, "onScrolledDown()");
    }

    public void onScrolledToTop(int dy,RecyclerView recyclerView) {
        Log.v(TAG, "onScrolledToTop("+dy+")");
    }

    public void onScrolledToBottom(int dy,RecyclerView recyclerView) {
        int x = dy;
        x = recyclerView.getAdapter().getItemCount();

        Log.v(TAG, "onScrolledToBottom("+x+")");

        if (x<=100)
        mainActivity.loaddata();
        else
        {
            mainActivity.emptydata();
            mainActivity.loaddata();
        }

    }

}
