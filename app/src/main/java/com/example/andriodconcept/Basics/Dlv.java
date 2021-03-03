package com.example.andriodconcept.Basics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andriodconcept.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Dlv extends AppCompatActivity {
    ListView dlv;
    List<String> list = new ArrayList<String>();
  //  private File ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlv);
        //DYNAMIC LIST VIEW
        dlv = (ListView) findViewById(R.id.dlv);

       File f = new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()));
        my(f);
    }

     void my(File f) {
        File[] fi = f.listFiles();
        list.clear();
        for (File file : fi) {
            list.add(file.getPath());
        }
        //  list.toArray();*/

      // String path= "/system/";
      // File f=new File(String.valueOf(Environment.getRootDirectory()));
      //  String path=Environment.getRootDirectory();
       // File f=new File(Environment.getExternalStorageDirectory(),"f");
     //   f.mkdir();
      //  String path=f.getAbsolutePath();
        //File fil=new File("/storage/");
      //  String [] values=fil.list();
        //String[] values = (String[]) list.toArray();
        //String[] values=getResources().getStringArray(R.array.countrynames);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        dlv.setAdapter(ad);


    }
}
