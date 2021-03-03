package com.example.andriodconcept.Storages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriodconcept.R;

public class Sqlite1 extends AppCompatActivity {
EditText ed1,ed2,ed3;
Button read,update,insert,delete;
SQLiteDatabase sq;
    ContentValues cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite1);
        // creating daatabase object it will get db object if it exists already with given name or if not it creates a new object.
       sq=openOrCreateDatabase("mydb", Context.MODE_PRIVATE,null);
       // creating table,we are using if not exists because whenever u open the app ,on create () executes it 2nd time it throws table already exists exeception/sqlite duplication.
        sq.execSQL("create table if not exists employee(email varchar(100),password number,name char(100))");

        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        read=findViewById(R.id.read);
        update=findViewById(R.id.update);
        insert=findViewById(R.id.insert);
        delete=findViewById(R.id.delete);
    }

    public void delete(View view) {
        long count=sq.delete("employee","name=?",new String[]{ed3.getText().toString()});
        if(count>0){
            Toast.makeText(getApplicationContext(),"RECORD IS DELETED......",Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"FAILED TO DELETED!!",Toast.LENGTH_LONG).show();
        }
    }


    public void update(View view) {
        //upadating data using name of employee
        cv.put("email",ed1.getText().toString());
        cv.put("password",Integer.parseInt(ed2.getText().toString()));
       long count= sq.update("employee",cv,"name=?",new String[]{ed3.getText().toString()});
        if(count>0){
            Toast.makeText(getApplicationContext(),"DATA IS UPDATED......",Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"FAILED TO UPDATE!!",Toast.LENGTH_LONG).show();
        }
    }


    public void read(View view) {
        Cursor c=sq.query("employee",null,null,null,null,null,null);
        while(c.moveToNext()){
            Toast.makeText(getApplicationContext(),c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2),Toast.LENGTH_LONG).show();
        }
    }

    public void insert(View view) {
         cv=new ContentValues();
        cv.put("email",ed1.getText().toString());
        cv.put("password",Integer.parseInt(ed2.getText().toString()));
        cv.put("name",ed3.getText().toString());
       long count= sq.insert("employee",null,cv);
       if(count>0){
           Toast.makeText(getApplicationContext(),"RECORD IS INSERTED......",Toast.LENGTH_LONG).show();
           ed1.setText("");
           ed2.setText("");
           ed3.setText("");
       }
       else{
           Toast.makeText(getApplicationContext(),"FAILED TO INSERT!!",Toast.LENGTH_LONG).show();
       }
    }
}
