package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private EditText editText = null;
    private Context mContext = null;
    private TextView mTextView = null;
    private String data = "";
    private String TAG = "myapp";
    ArrayList<String> items = null;
    //JSONObject jObj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.m_text);
        mContext = getApplicationContext();
        items = new ArrayList<String> ();

        final QuotesAdapter quotesAdapter = new QuotesAdapter(items, mContext);
        ListView listView = (ListView) findViewById(R.id.m_ListView);
        listView.setAdapter(quotesAdapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://quotesondesign.com/wp-json/posts?&filter[posts_per_page]=10";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        data = response;
                        Log.v(TAG, "jobj  :"+data);
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            int i = jsonArray.length();
                            for(int x=0;x<=i;x++){

                                JSONObject jObj = jsonArray.getJSONObject(x);
                                try {
                                    items.add(jObj.getString("content"));
                                    Log.v(TAG, "jobj  :"+jObj.getString("content"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        quotesAdapter.notifyDataSetChanged();


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
//                        items.add(buf.substring(x+3,y-2));
//                        Log.v(TAG, "size  :"+items.size());
//                        mTextViewFGGGText("Response is: "+ response.substring(0,100));


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work! " +error.toString());
            }
        });


// Add the request to the RequestQueue.
        queue.add(stringRequest);




    }


    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


//    public String getQuotes()
//    {
//        HttpURLConnection connection = HttpURLConnection
//        return "";
//    }
}
