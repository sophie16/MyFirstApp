package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

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
 * Created by Asus on 03-08-2017.
 */

public class ImageLoadTask extends AsyncTask <Void, Void, Bitmap> {
    private String url;
    private String imageURL;
    private String TAG = "myapp ImageLoadTask";
    private ImageView imageView;
    private RequestQueue queue;
   // private Bitmap myBitmap;
    private  Context mContext;
    private ArrayList<String> images = new ArrayList<>();


    public ImageLoadTask(String url, ImageView imageView, Context mContext, ArrayList<String> images) {
        this.url = url;
        this.imageView = imageView;
        this.mContext = mContext;
        this.queue= Volley.newRequestQueue(mContext);
        this.images=images;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Log.v(TAG, "Step 1");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.


                        try {

                            JSONObject jsontmp = new JSONObject(response);
                            JSONArray jsonArray = jsontmp.getJSONArray("hits");
                            Log.v(TAG, "Step 2");
                            int i = jsonArray.length();
                            Log.v(TAG, "jsonArray.length =" +i);
                            for (int x = 0; x < i; x++) {

                                JSONObject jObj = jsonArray.getJSONObject(x);
                                imageURL=jObj.getString("webformatURL");
                                Log.v(TAG, "webformatURL ="+ imageURL);
                                Log.v(TAG, "image layout ="+ imageView);

                                images.add(imageURL);
                               // Picasso.with(mContext).load(imageURL).into(imageView);



                            }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        //imageView.setImageBitmap(myBitmap);

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

    @Override
    protected void onPostExecute(Bitmap result)
    {
        Log.v(TAG, "Images done");
    }
}
