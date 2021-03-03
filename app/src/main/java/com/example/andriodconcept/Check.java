package com.example.andriodconcept;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class Check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
    }

    public void startservice(View view) {
        Log.i("way","entred");
        Intent i=new Intent(getApplicationContext(),CheckReciever.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,i,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Calendar calendar=Calendar.getInstance();
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+30*1000,pendingIntent);
    }

    public void stopservice(View view) {
    }
    public static class CheckService extends Service {


        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(getApplicationContext(),"IOKKKKKKKKKKK",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),CheckReciever.class);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,i,0);
            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Calendar calendar=Calendar.getInstance();
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+30*1000,pendingIntent);
            return START_STICKY;
        }

        @Override
        public void onTaskRemoved(Intent rootIntent) {
            super.onTaskRemoved(rootIntent);
        }
    }

    public static class CheckReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Intent i=new Intent(context,CheckService.class);
                context.startService(i);
            }
            catch (Exception e){
                
            }
        }
    }
}
