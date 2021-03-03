package com.example.andriodconcept.BroadcastReciever;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.example.andriodconcept.R;

public class Broadcast extends AppCompatActivity {
TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        txt=findViewById(R.id.txt);
        IntentFilter i=new IntentFilter();
        i.addAction(Intent.ACTION_HEADSET_PLUG);
        i.addAction(Intent.ACTION_POWER_CONNECTED);
        i.addAction(Intent.ACTION_POWER_DISCONNECTED);
        i.addAction(Intent.ACTION_SCREEN_ON);
        i.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new MyBroadcast(),i);


    }
    public class MyBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction()==Intent.ACTION_HEADSET_PLUG){
                txt.setText("HEADSET PLUGGED IN");
            }
            if(intent.getAction()==Intent.ACTION_POWER_CONNECTED){
                txt.setText("CHARGING");
            }
            if(intent.getAction()==Intent.ACTION_POWER_DISCONNECTED){
                txt.setText("NO CHARGER CONNECTED");
            }
            if(intent.getAction()==Intent.ACTION_SCREEN_ON){
                txt.setText("SCREEN ON");
            }
            if(intent.getAction()==Intent.ACTION_SCREEN_OFF){
                txt.setText("SCREEN OFF");
            }
        }

    }
}
