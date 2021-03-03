package com.example.andriodconcept;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.andriodconcept.Services.MyService;
import com.example.andriodconcept.Services.Notifications;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;
import static com.example.andriodconcept.ForegroundService.notificationManager;
import static com.example.andriodconcept.MyApplication.CHANNEL_ID;

//import static com.example.andriodconcept.MyApplication.CHANNEL_ID;

//import static com.example.andriodconcept.ForegroundService.notificationManager;

public class MyForeground extends IntentService {
    MediaPlayer mp;
    private PowerManager.WakeLock wakeLock;

    public MyForeground() {
        super(MyForeground.class.getSimpleName());
        setIntentRedelivery(true);
    }

    //  @Override
  /*  public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "ExampleApp:Wakelock");
        wakeLock.acquire();
        Log.d(TAG, "Wakelock acquired");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Example IntentService")
                    .setContentText("Running...")
                    .setSmallIcon(R.drawable.google_icon)
                    .build();
            startForeground(1, notification);
        }
    }*/
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //do heavy work on a background thread
        startForeground(1, getNotification());
        mp = MediaPlayer.create(getApplicationContext(), R.raw.song);
        mp.setLooping(true);
        mp.start();
        //stopSelf();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //        wakeLock.release();
    }

    protected Notification getNotification() {
        int notificationIdentity = 1;
        Intent intent = new Intent(this, getApplicationContext().getClass());
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
        NotificationCompat.Builder n = new NotificationCompat.Builder(this);
        n.setSmallIcon(R.drawable.google_icon);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.google_icon);
        n.setLargeIcon(bitmap);
        n.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        n.setContentTitle("GOOGLE Android Program");
        n.setContentIntent(pendingIntent);
        //content text is inner most one.
        n.setSubText("GOOGLE ANDROID PROGRAM  starting from Oct'1, 2020. Register Now");
        n.setContentText("ADVANCED ANDRIOD @ 170000 RS" + "\n" + "BEGINNER ANDRIOD @ 6000 RS");
        n.setAutoCancel(true);
        //   n.setDefaults(NotificationCompat.DEFAULT_ALL);

        Uri ringtonepath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        n.setSound(ringtonepath);


        //if app is installed in Oreo device version 8and 8.1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "ANY_CHANNEL_NAME";
            NotificationChannel channel = new NotificationChannel(channelId, "GOOGLE Promotions", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            n.setChannelId(channelId);
        }
        return n.build();
    }
}
