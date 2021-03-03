package com.example.andriodconcept.Basics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andriodconcept.R;

import java.util.ArrayList;

public class Dlv3 extends AppCompatActivity {
    EditText ed;
    Button btn;
    ListView lv;
    ArrayList<String> al = new ArrayList<String>();
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlv3);
        ed = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.btn);
        lv = (ListView) findViewById(R.id.dlv);
        ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
        lv.setAdapter(ad);

    }

    public void add(View view) {
        String str = ed.getText().toString();
        if (!str.equals("")) {
            al.add(ed.getText().toString());
            ad.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "ENTER SOME THING", Toast.LENGTH_LONG).show();
        }
    }
}
