package com.example.andriodconcept.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.andriodconcept.ForegroundService;
import com.example.andriodconcept.R;

//import static com.example.andriodconcept.MyApplication.CHANNEL_ID;

public class MyService extends Service {
    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mp = MediaPlayer.create(getApplicationContext(), R.raw.song);
        mp.setLooping(true);
        mp.start();
        stopSelf();

        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent i = new Intent(getApplicationContext(), MyService.class);
        startService(i);
        //    startIntentSender(rootIntent);
        super.onTaskRemoved(rootIntent);
    }


    @Override
    public void onCreate() {

        super.onCreate();
        // mp= MediaPlayer.create(getApplicationContext(),R.raw.song);
    }


  /*  @Override
    public void onDestroy() {

        super.onDestroy();
        //mp.stop();
    }*/
}
