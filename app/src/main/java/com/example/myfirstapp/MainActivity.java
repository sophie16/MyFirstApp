package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONObject;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private EditText editText = null;
    private Context mConetxt = null;
    private TextView mTextView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //editText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView)findViewById(R.id.m_text);
        mConetxt = getApplicationContext();



        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://quotesondesign.com/wp-json/posts?&filter[posts_per_page]=10";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //JSONParser parser = new JSONParser();
                        //JSONObject json = (JSONObject) parser.parse(stringToParse);
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,100));
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


    public String getQuotes()
    {
        HttpURLConnection connection = HttpURLConnection
        return "";
    }
}
