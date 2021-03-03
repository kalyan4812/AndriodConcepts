package com.example.andriodconcept.WebServices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andriodconcept.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Json extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button read,update,insert,delete;
    String   storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/myemp.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        read=findViewById(R.id.read);
        update=findViewById(R.id.update);
        insert=findViewById(R.id.insert);
        delete=findViewById(R.id.delete);
    }

    public void insert(View view) {
        try {
            File f=new File(storagelocation);
            JSONObject jsonObject;
            JSONArray jsonArray;
            if(f.exists()){
                FileReader fr=new FileReader(storagelocation);
                // convert file to string
                String msg="";
                int i=fr.read();
                while (i!=-1){
                    msg=msg+(char)i;
                    i=fr.read();
                }
                jsonObject=new JSONObject(msg);
                jsonArray =jsonObject.getJSONArray("employees");

            }else {
                 jsonObject = new JSONObject();
                 jsonArray = new JSONArray();
            }
            JSONObject empobject = new JSONObject();
            empobject.put("email", ed1.getText().toString());
            empobject.put("password", ed2.getText().toString());
            empobject.put("name", ed3.getText().toString());
            jsonArray.put(empobject);
            jsonObject.put("employees",jsonArray);
            Log.i("mystring",jsonObject.toString());
            FileWriter  fw = new FileWriter(storagelocation);
            fw.write(jsonObject.toString());
            fw.flush();
            fw.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void read(View view) {
        try{
            FileReader fr=new FileReader(storagelocation);
            // convert file to string
            String msg="";
            int i=fr.read();
            while (i!=-1){
                msg=msg+(char)i;
                i=fr.read();
            }
            JSONObject js=new JSONObject(msg);
            JSONArray jr=js.getJSONArray("employees");
            for(int k=0;k<jr.length();k++) {
                JSONObject individualobject = jr.getJSONObject(k);
                if (individualobject.getString("email").equalsIgnoreCase(ed1.getText().toString())) {
                    ed1.setText(individualobject.getString("email"));
                    ed2.setText(individualobject.getString("password"));
                    ed3.setText(individualobject.getString("name"));
                }
            }
        }
        catch (Exception e){

        }
    }

    public void update(View view) {
    }

    public void delete(View view) {
    }
}
