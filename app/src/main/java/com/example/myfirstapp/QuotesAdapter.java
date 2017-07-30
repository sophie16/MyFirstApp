package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Asus on 28-07-2017.
 */

public class QuotesAdapter extends BaseAdapter {
    private ArrayList<String> quotes = null;
    private ArrayList<String> authors = null;
    private Context mContext = null;
    private LayoutInflater mLayoutInflator = null;

    public QuotesAdapter(ArrayList<String> q,ArrayList<String> a,Context b)
    {
        this.quotes = q;
        this.authors = a;
        this.mContext = b;
        this.mLayoutInflator = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Object getItem(int position) {
        return quotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView =  mLayoutInflator.inflate(R.layout.list_item, null);

        TextView quotes = (TextView)convertView.findViewById(R.id.quotes);
        TextView authors = (TextView)convertView.findViewById(R.id.authors);


        quotes.setText(this.quotes.get(position));
        authors.setText("Author : "+ this.authors.get(position));

        return convertView;
    }
}
