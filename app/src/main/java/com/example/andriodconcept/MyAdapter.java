package com.example.andriodconcept;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.andriodconcept.RetrofitLibrary.hr;
import static com.example.andriodconcept.RetrofitLibrary.hr2;
import static com.example.andriodconcept.RetrofitLibrary.ref;

public class MyAdapter extends BaseAdapter {
String [] imgurl=ref.hr2;
String [] txt=ref.hr;
    @Override
    public int getCount() {
        return hr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(ref);
        View v=inflater.inflate(R.layout.adapterlayout,null);
        ImageView himg=v.findViewById(R.id.img);
        TextView tv1=v.findViewById(R.id.tv1);
        TextView tv2=v.findViewById(R.id.tv2);
       // Button del=v.findViewById(R.id.del);
        Picasso.get().load(hr2[position]).into(himg);
        tv1.setText(hr[position]);
     // tv2.setText(hr3[position]);



        return v;
    }
}
