package com.example.andriodconcept.Storages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import  android.content.SharedPreferences;

import com.example.andriodconcept.R;

public class Shraredsignup extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
// use android.content.ShredPreferences not other one
SharedPreferences sp;
SharedPreferences.Editor spe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shraredsignup);
        ed1=(EditText) findViewById(R.id.ed1);
        ed2=(EditText) findViewById(R.id.ed2);
        ed3=(EditText) findViewById(R.id.ed3);
        ed4=(EditText) findViewById(R.id.ed4);

    }
    public void register (View view){
         sp = (SharedPreferences) getSharedPreferences("mypref", Context.MODE_PRIVATE);
      spe=sp.edit();
      spe.putString("name",ed1.getText().toString());
        spe.putString("email",ed2.getText().toString());
        spe.putString("password",ed3.getText().toString());
        spe.putString("confirm password",ed4.getText().toString());
        spe.commit();
        //after registration explicitly we are destroying finish()
        finish();



    }
}
