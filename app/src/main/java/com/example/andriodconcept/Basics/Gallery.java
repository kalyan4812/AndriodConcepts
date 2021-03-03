package com.example.andriodconcept.Basics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.andriodconcept.R;

public class Gallery extends AppCompatActivity {
android.widget.Gallery gal;
//FOR GRID VIEW JUST CHANGE GALLERY TO GRIDVIEW ,USE numcolumns attribute for no.of columns.
GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
       // gal=(android.widget.Gallery) findViewById(R.id.gal);
        gv=(GridView) findViewById(R.id.gv);

        gv.setAdapter(new Mygalleryadapter(this));
    }
}
