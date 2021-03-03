package com.example.andriodconcept.Basics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.andriodconcept.MyAdapter;
import com.example.andriodconcept.R;

public class Dynamiclistview2 extends AppCompatActivity {
ListView dlv;

    static Dynamiclistview2 dlv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dlv2=this;
        setContentView(R.layout.activity_dynamiclistview2);
        dlv=(ListView) findViewById(R.id.dlv);
        dlv.setAdapter(new MyAdapter());
    }
}
