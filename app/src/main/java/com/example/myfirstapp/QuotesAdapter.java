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
    private ArrayList<String> items = null;
    private Context mContext = null;
    private LayoutInflater mLayoutInflator = null;

    public QuotesAdapter(ArrayList<String> a, Context b)
    {
        this.items = a;
        this.mContext = b;
        this.mLayoutInflator = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView =  mLayoutInflator.inflate(R.layout.list_item, null);

        TextView quotes = (TextView)convertView.findViewById(R.id.quotes);
        TextView author = (TextView)convertView.findViewById(R.id.author);


        quotes.setText(items.get(position));
        author.setText("position : "+position);

        return convertView;
    }
}
