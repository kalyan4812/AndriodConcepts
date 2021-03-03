package com.example.andriodconcept.WebServices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriodconcept.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class gson extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button read,update,insert,delete;
    String   storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/myemp.gson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        read=findViewById(R.id.read);
        insert=findViewById(R.id.insert);
    }

    public void read(View view) {
        try{
            FileReader fr=new FileReader(storagelocation);
            Gson g=new Gson();
            EmployeesPOJO emps=g.fromJson(fr,EmployeesPOJO.class);
            ArrayList<EmployeePOJO> list= emps.getEmployees();
            for(EmployeePOJO e:list){
                Toast.makeText(getApplicationContext(),e.getEmail()+"\n"+e.getPassword()+"\n"+e.getName(),Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
//    EmployeesPOJO emps;
    public void insert(View view) {
        File f=new File(storagelocation);
        EmployeesPOJO emps;
        ArrayList<EmployeePOJO> emplist;
        Gson g = new Gson();

            EmployeePOJO employeePOJO = new EmployeePOJO();
        employeePOJO.setEmail(ed1.getText().toString());
        employeePOJO.setPassword(ed2.getText().toString());
        employeePOJO.setName(ed3.getText().toString());

         emplist=new ArrayList<EmployeePOJO>() ;
        emplist.add(employeePOJO);

        if(f.exists()){
            try {
                FileReader fr = new FileReader(storagelocation);
              //  Gson g = new Gson();
                emps = g.fromJson(fr, EmployeesPOJO.class);
                emps.setEmployees(emplist);
             //   Gson gs=new Gson();

                String response = g.toJson(emps);

                try {
                    FileWriter fw = new FileWriter(storagelocation);
                    fw.write(response);
                    fw.flush();
                    fw.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            emps = new EmployeesPOJO();
            emps.setEmployees(emplist);

          //  Gson gs = new Gson();

            String response = g.toJson(emps);
            Log.i("mystring",response);

            try {
                FileWriter fw = new FileWriter(storagelocation);
                fw.write(response);
                fw.flush();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
