package com.example.andriodconcept.Services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;

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
import android.view.View;
import android.widget.Button;

import com.example.andriodconcept.ForegroundService;
import com.example.andriodconcept.R;

public class Notifications extends AppCompatActivity {

    Button simple_n, bigtext_n,message_n, picture_n, action_s;
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        //set
        simple_n=findViewById(R.id.simple_notification);
        bigtext_n=findViewById(R.id.bigtext_notification);
        message_n=findViewById(R.id.message_notification);
        picture_n=findViewById(R.id.picture_notification);
        action_s=findViewById(R.id.action_notification);
         notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        simple_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationIdentity=1;

                NotificationCompat.Builder n=new NotificationCompat.Builder(Notifications.this);
                n.setSmallIcon(R.drawable.google_icon);
                Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.google_icon);
                n.setLargeIcon(bitmap);
                n.setContentTitle("GOOGLE Android Program");
                //content text is inner most one.
                n.setSubText("GOOGLE ANDROID PROGRAM  starting from Oct'1, 2020. Register Now");
                n.setContentText("ADVANCED ANDRIOD @ 170000 RS"+"\n"+"BEGINNER ANDRIOD @ 6000 RS");
                n.setAutoCancel(true);
                n.setDefaults(NotificationCompat.DEFAULT_ALL);

                Uri ringtonepath= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                n.setSound(ringtonepath);


                //if app is installed in Oreo device version 8and 8.1

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    String channelId="ANY_CHANNEL_NAME";
                    NotificationChannel channel=new NotificationChannel(channelId, "GOOGLE Promotions", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    n.setChannelId(channelId);
                }
                // for every notification there willbe a id ,if there is anotification with given id in notification bar,
                // then no new notification will appear ,just it will update.means for every unique id unique notification.
                //to get  same notification every time when we press the button ,use count variable count=0; and inplace of id use ++count.
                notificationManager.notify(notificationIdentity, n.build());
                // and also second parameter in above line is object of Notification  class,n is object of Builder class,
                // to get object of NOtification class use n.build(),build method returns object of Notification class.
            }
        });

        bigtext_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationIdentity=2;

                NotificationCompat.Builder n=new NotificationCompat.Builder(Notifications.this);
                        n.setSmallIcon(R.drawable.google_icon);
                        n.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.google_icon));
                        n.setContentTitle("Promotions");
                        n.setStyle(new NotificationCompat.BigTextStyle().bigText(getResources().getString(R.string.promo)));
                        n.setAutoCancel(true);
                        n.setDefaults(NotificationCompat.DEFAULT_ALL);

                Uri ringtonepath= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                n.setSound(ringtonepath);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                //if app is installed in Oreo device version 8and 8.1

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    String channelId="ANY_CHANNEL_NAME";
                    NotificationChannel channel=new NotificationChannel(channelId, "GOOGLE Promotions", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    n.setChannelId(channelId);
                }
                notificationManager.notify(notificationIdentity, n.build());

            }
        });

        message_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationIdentity=3;

                NotificationCompat.Builder n=new NotificationCompat.Builder(Notifications.this);
                        n.setSmallIcon(R.drawable.google_icon);
                        n.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.google_icon));
                        n.setContentTitle("Promotions");
                        n.setStyle(new NotificationCompat.MessagingStyle("KALYAN: ").setConversationTitle("Android Discussion")
                                .addMessage("Android Current Version  ?",0, "Raj: ")
                                .addMessage("Android 10", 0, "Akhil: ")
                                .addMessage("Next Security Patch ?", 0, (Person) null));
                        n.setAutoCancel(true);
                        n.setDefaults(NotificationCompat.DEFAULT_ALL);

                Uri ringtonepath= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                n.setSound(ringtonepath);


                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                //if app is installed in Oreo device version 8and 8.1

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    String channelId="ANY_CHANNEL_NAME";
                    NotificationChannel channel=new NotificationChannel(channelId, "GOOGLE Promotions", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    n.setChannelId(channelId);
                }
                notificationManager.notify(notificationIdentity, n.build());

            }
        });


        picture_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int notificationIdentity=4;

                Bitmap offerimage=BitmapFactory.decodeResource(getResources(), R.drawable.download);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(Notifications.this)
                        .setSmallIcon(R.drawable.google_icon)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.google_icon))
                        .setContentTitle("Indias Biggest Sale Offer")
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(offerimage))
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

                Uri ringtonepath= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(ringtonepath);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                //if app is installed in Oreo device version 8and 8.1

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    String channelId="ANY_CHANNEL_NAME";
                    NotificationChannel channel=new NotificationChannel(channelId, "NareshIT Promotions", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    builder.setChannelId(channelId);
                }
                notificationManager.notify(notificationIdentity, builder.build());


            }
        });

        action_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notificationIdentity=5;

                NotificationCompat.Builder builder=new NotificationCompat.Builder(Notifications.this)
                        .setSmallIcon(R.drawable.google_icon)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.google_icon))
                        .setContentTitle("New Rajamouli Movie Trailer")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Click here to watch"))
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

                //pending intent means intent which we can open later,or on the spot,means its waiting for us to open new activity
                // when we click on notification.
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com"));
                PendingIntent pendingIntent=PendingIntent.getActivity(Notifications.this, 0, intent, 0);

                builder.addAction(R.drawable.instagram, "Watch Now", pendingIntent);


                Uri ringtonepath= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(ringtonepath);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                //if app is installed in Oreo device version 8and 8.1

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    String channelId="ANY_CHANNEL_NAME";
                    NotificationChannel channel=new NotificationChannel(channelId, "NareshIT Promotions", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    builder.setChannelId(channelId);
                }
                notificationManager.notify(notificationIdentity, builder.build());

            }
        });




    }
    public  Notification getNotification(){
        int notificationIdentity=5;
        Intent intent=new Intent(getApplicationContext(), ForegroundService.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(Notifications.this, 0, intent, 0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(Notifications.this)
                .setSmallIcon(R.drawable.google_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.google_icon))
                .setContentTitle("Background Service")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setChannelId("123")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //pending intent means intent which we can open later,or on the spot,means its waiting for us to open new activity
        // when we click on notification.


        builder.addAction(R.drawable.instagram, "Watch Now", pendingIntent);


        Uri ringtonepath= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtonepath);

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //if app is installed in Oreo device version 8and 8.1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId="ANY_CHANNEL_NAME";
            NotificationChannel channel=new NotificationChannel(channelId, "NareshIT Promotions", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        notificationManager.notify(notificationIdentity, builder.build());
        return builder.build();
    }
}
