package com.example.andriodconcept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.andriodconcept.AsyncTask.Asynctask;
import com.example.andriodconcept.Basics.Actv;
import com.example.andriodconcept.Basics.Dialogs;
import com.example.andriodconcept.Basics.Dlv;
import com.example.andriodconcept.Basics.Dlv3;
import com.example.andriodconcept.Basics.Dynamiclistview2;
import com.example.andriodconcept.Basics.Gallery;
import com.example.andriodconcept.Basics.MenuAndActionBar;
import com.example.andriodconcept.BroadcastReciever.Broadcast;
import com.example.andriodconcept.ContentProvider.Content;
import com.example.andriodconcept.Fragments.MyFragment;
import com.example.andriodconcept.Multimedia.Audiorecord;
import com.example.andriodconcept.Multimedia.Takephoto;
import com.example.andriodconcept.Services.Bluetooth;
import com.example.andriodconcept.Multimedia.Mediaplayer;
import com.example.andriodconcept.Multimedia.Videoplayer;
import com.example.andriodconcept.Multimedia.Videorecord;
import com.example.andriodconcept.Services.Notifications;
import com.example.andriodconcept.Services.Sensors;
import com.example.andriodconcept.Services.ServiceExample;
import com.example.andriodconcept.Services.Telephone;
import com.example.andriodconcept.Services.Wifi;
import com.example.andriodconcept.Storages.Shraredpreferances;
import com.example.andriodconcept.Storages.Sqlite1;
import com.example.andriodconcept.WebServices.Json;
import com.example.andriodconcept.WebServices.XmlParsing;
import com.example.andriodconcept.WebServices.XmlParsing2;
import com.example.andriodconcept.WebServices.gson;
import com.example.andriodconcept.WebView.Webview;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void autocompletetextview(View view){
        Intent i=new Intent(getApplicationContext(), Actv.class);
        startActivity(i);
    }

    public void spinner(View view) {
        Intent i=new Intent(getApplicationContext(),Actv.class);
        startActivity(i);
    }

    public void listview(View view) {
        Intent i=new Intent(getApplicationContext(),Actv.class);
        startActivity(i);
    }

    public void dynamiclistview(View view) {
        Intent i=new Intent(getApplicationContext(), Dlv.class);
        startActivity(i);
    }

    public void dynamiclistview2(View view) {
        Intent i=new Intent(getApplicationContext(), Dynamiclistview2.class);
        startActivity(i);
    }

    public void dynamiclistview3(View view) {
        Intent i=new Intent(getApplicationContext(), Dlv3.class);
        startActivity(i);
    }

    public void gallery(View view) {
        Intent i=new Intent(getApplicationContext(), Gallery.class);
        startActivity(i);
    }

    public void webview(View view) {

        Intent i=new Intent(getApplicationContext(), Webview.class);
        startActivity(i);
    }

    public void fragment(View view) {
        Intent i=new Intent(getApplicationContext(), MyFragment.class);
        startActivity(i);
    }
    public void sharedpreference(View view) {
        Intent i=new Intent(getApplicationContext(), Shraredpreferances.class);
        startActivity(i);
    }

    public void sqlite1(View view) {
        Intent i=new Intent(getApplicationContext(), Sqlite1.class);
        startActivity(i);
    }

    public void telephone(View view) {
        Intent i=new Intent(getApplicationContext(), Telephone.class);
        startActivity(i);
    }

    public void asynctask(View view) {
        Intent i=new Intent(getApplicationContext(), Asynctask.class);
        startActivity(i);
    }

    public void mediaplayer(View view) {
        Intent i=new Intent(getApplicationContext(), Mediaplayer.class);
        startActivity(i);
    }

    public void videoplayer(View view) {
        Intent i=new Intent(getApplicationContext(), Videoplayer.class);
        startActivity(i);
    }

    public void audiorecording(View view) {
        Intent i=new Intent(getApplicationContext(), Audiorecord.class);
        startActivity(i);
    }

    public void videorecording(View view) {
        Intent i=new Intent(getApplicationContext(), Videorecord.class);
        startActivity(i);
    }

    public void camandgallery(View view) {
        Intent i=new Intent(getApplicationContext(), Takephoto.class);
        startActivity(i);
    }

    public void services(View view) {
        Intent i=new Intent(getApplicationContext(), ServiceExample.class);
        startActivity(i);
    }

    public void broadcastreciever(View view) {
        Intent i=new Intent(getApplicationContext(), Broadcast.class);
        startActivity(i);
    }

    public void contentprovider(View view) {
        Intent i=new Intent(getApplicationContext(), Content.class);
        startActivity(i);
    }

    public void sensor(View view) {
        Intent i=new Intent(getApplicationContext(), Sensors.class);
        startActivity(i);
    }

    public void wifi(View view) {
        Intent i=new Intent(getApplicationContext(), Wifi.class);
        startActivity(i);
    }

    public void bluetooth(View view) {
        Intent i=new Intent(getApplicationContext(), Bluetooth.class);
        startActivity(i);
    }

    public void notification(View view) {
        Intent i=new Intent(getApplicationContext(), Notifications.class);
        startActivity(i);
    }

    public void dialogs(View view) {
        Intent i=new Intent(getApplicationContext(), Dialogs.class);
        startActivity(i);
    }

    public void menuandaction(View view) {
        Intent i=new Intent(getApplicationContext(), MenuAndActionBar.class);
        startActivity(i);
    }

    public void xmlparsing(View view) {
        Intent i=new Intent(getApplicationContext(), XmlParsing.class);
        startActivity(i);
    }

    public void xmlparsing2(View view) {
        Intent i=new Intent(getApplicationContext(), XmlParsing2.class);
        startActivity(i);
    }

    public void soap(View view) {
        Intent i=new Intent(getApplicationContext(), WeatherApp.class);
        startActivity(i);

    }

    public void json(View view) {
        Intent i=new Intent(getApplicationContext(), Json.class);
        startActivity(i);

    }

    public void gson(View view) {
        Intent i=new Intent(getApplicationContext(), gson.class);
        startActivity(i);

    }

    public void retrofit(View view) {
        Intent i=new Intent(getApplicationContext(), RetrofitLibrary.class);
        startActivity(i);
    }

    public void forgroundservice(View view) {
        Intent i=new Intent(getApplicationContext(), ForegroundService.class);
        startActivity(i);
    }

    public void workmanager(View view) {
        Intent i=new Intent(getApplicationContext(), WorkMangerDemo.class);
        startActivity(i);
    }

    public void jobschedular(View view) {
        Intent i=new Intent(getApplicationContext(), Check.class);
        startActivity(i);
    }
}
