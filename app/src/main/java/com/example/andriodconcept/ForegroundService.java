package com.example.andriodconcept;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.andriodconcept.Services.MyService;


public class ForegroundService extends AppCompatActivity {
    ImageView im;
    Button play, stop;
    Intent serviceIntent;
    public static NotificationManager notificationManager;
    private NotificationManagerCompat notificationManagerCompat;
    public static ForegroundService foregroundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgeround_service);
        foregroundService = this;
        im = findViewById(R.id.medialogo);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());

        serviceIntent = new Intent(getApplicationContext(), MyForeground.class);

    }

    public void playservice(View view) {

        ContextCompat.startForegroundService(getApplicationContext(), serviceIntent);

    }

    public void stopservice(View view) {
        // Intent i=new Intent(getApplicationContext(),MyService.class);
        stopService(serviceIntent);

    }

    // BY USING SERVICE CONCEPT EVEN IF WE REMOVE APP FROM BACKGROUND STILL SONG WILL PLAY .
    // THAT IS WE ARE DOING SERVICE IN ABSENCE OF USER INTERACTION.


}
