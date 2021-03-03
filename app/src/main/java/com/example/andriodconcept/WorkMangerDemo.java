package com.example.andriodconcept;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.andriodconcept.Services.MyService;

import java.util.concurrent.TimeUnit;

public class WorkMangerDemo extends AppCompatActivity {

    ImageView im;
    Button play,stop;
    OneTimeWorkRequest oneTimeWorkRequest;
    PeriodicWorkRequest periodicWorkRequest;
    Constraints constraints;
    public static WorkMangerDemo workMangerDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manger_demo);
        im=findViewById(R.id.medialogo);
        play=findViewById(R.id.play);
        stop=findViewById(R.id.stop);
       // constraints=new Constraints.Builder().setRequiredNetworkType();
    // oneTimeWorkRequest=new OneTimeWorkRequest.Builder(MyWorker.class).build();
       periodicWorkRequest=new PeriodicWorkRequest.Builder(MyWorker.class,15,TimeUnit.MINUTES).build();
    }

    public void playservice(View view) {

        WorkManager.getInstance(getApplicationContext()).enqueue(periodicWorkRequest);

    }

    public void stopservice(View view) {
       WorkManager.getInstance(getApplicationContext()).cancelAllWork();
        Log.i("THREAD","WORK STOPPED");
    }


}
