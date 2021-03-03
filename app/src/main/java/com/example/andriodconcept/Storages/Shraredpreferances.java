package com.example.andriodconcept.Storages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriodconcept.R;

public class Shraredpreferances extends AppCompatActivity {
EditText ed1,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shraredpreferances);
        ed1=(EditText) findViewById(R.id.ed1);
        ed2=(EditText) findViewById(R.id.ed2);
    }
    public void sharedlogin (View view){
        SharedPreferences sp=getSharedPreferences("mypref", Context.MODE_PRIVATE);
        String email=sp.getString("email","no value");
        String pass=sp.getString("password","no value");
        if(ed1.getText().toString().equals(email) && ed2.getText().toString().equals(pass)){
            Toast.makeText(getApplicationContext(),"LOGIN SUCCESS",Toast.LENGTH_LONG).show();
        }

        else{
            Toast.makeText(getApplicationContext(),"LOGIN FAILED",Toast.LENGTH_LONG).show();
        }

    }
    public void shraredsignup(View view){
        Intent i=new Intent(getApplicationContext(), Shraredsignup.class);
        startActivity(i);


    }
}
