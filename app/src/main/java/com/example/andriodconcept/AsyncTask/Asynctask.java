package com.example.andriodconcept.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.andriodconcept.R;

import java.io.InputStream;
import java.net.URL;

public class Asynctask extends AppCompatActivity {
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
         im=findViewById(R.id.img);
         // to handle long tasks or large data add this like for loading images
        StrictMode.ThreadPolicy st=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(st);
    }

    public void asynctaskmethod(View view) {
        MyTask mt=new MyTask();
        mt.execute();
      //  new mkkj().execute(5);
    }


    public class MyTask extends AsyncTask {
        InputStream is;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"YOUR IMAGE IS LOADING",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try{
                URL url=new URL("http://mimgs.sulekha.com/telugu/jalsa/images/stills/jalsa02.jpg");
                is=url.openStream();

            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Bitmap bitmap= BitmapFactory.decodeStream(is);
            im.setImageBitmap(bitmap);
        }
    }
}
