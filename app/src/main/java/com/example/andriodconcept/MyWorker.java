package com.example.andriodconcept;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.ForegroundInfo;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;
import java.util.logging.LogRecord;

public class MyWorker extends Worker {
    MediaPlayer mp;
    //android.os.Handler h=new android.os.Handler();

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    //ForegroundInfo foregroundInfo
    @NonNull
    @Override
    public Result doWork() {
        Log.i("THREAD", "THREAD IS :" + Thread.currentThread().getName());

        myworK();
        return Result.success();
    }

    private void myworK() {

        mp = MediaPlayer.create(getApplicationContext(), R.raw.song);

        mp.start();

       // WorkMangerDemo.workMangerDemo.myservice();




    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.i("THREAD", "WORK STOPPED");
    }


}
