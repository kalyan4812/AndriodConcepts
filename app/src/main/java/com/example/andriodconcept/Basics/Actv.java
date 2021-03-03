package com.example.andriodconcept.Basics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.andriodconcept.R;

public class Actv extends AppCompatActivity {
AutoCompleteTextView actv,actv2;
Spinner spinner;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actv);


        //AUTO COMPLETE TEXT VIEW
        //1 st method through xml configuration ofvalues,internalization-multi language support,static context-coountry names
        actv=(AutoCompleteTextView)findViewById(R.id.actv);
        String[] values=getResources().getStringArray(R.array.countrynames);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,values);
        actv.setAdapter(ad);
        actv.setThreshold(1);//by default 1
        // 2 nd method through java configuration,non internalization,dynamic context_movoe names
        String[] values2=new String[]{"INDIA","IRAN","ITALY","RUSSIA","AMERICA","CHINA","BRAZIL","BANGLADESH","INDONESIA"};
        actv2=(AutoCompleteTextView)findViewById(R.id.actv2);
        ArrayAdapter<String> ad2=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,values2);
        actv2.setAdapter(ad2);
        actv2.setThreshold(1);
        //------------------------------------------------------------------------------------------------------------

        //SPINNER
        // 1st spinner using "entries" attribute through xml
        spinner=(Spinner)findViewById(R.id.spinner);
        // 2nd spinner using java
        ArrayAdapter<String> ad3=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,values);
        spinner.setAdapter(ad3);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //----------------------------------------------------------------------------------------------------------------
        //LIST VIEW
        listView=(ListView)findViewById(R.id.listview);

    }


}
