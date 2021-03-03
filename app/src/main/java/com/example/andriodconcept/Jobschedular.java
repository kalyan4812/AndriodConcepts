package com.example.andriodconcept;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.andriodconcept.BroadcastReciever.Broadcast;

public class Jobschedular extends AppCompatActivity {
    JobScheduler jobScheduler;
    JobInfo jobInfo;
BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobschedular);
        br=new MyReciever();
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }

    public void startservice(View view) {
        Log.i("way","entered");
        Toast.makeText(getApplicationContext(), "entered", Toast.LENGTH_SHORT).show();
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        jobInfo = new JobInfo.Builder(123, componentName)
        .setPeriodic(15*60*1000)
       // jobInfo.setMinimumLatency(1 * 10 * 1000);
        .setPersisted(false).build();

      // jobInfo.build();
        //jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

    public void stopservice(View view) {
        jobScheduler.cancel(123);
    }
 //   @SuppressLint("SpecifyJobSchedulerIdRange")

    public class MyJobService extends JobService {
        JobParameters jobParameters;

        @Override
        public boolean onStartJob(JobParameters params) {
            Log.i("way","onstart");
            Toast.makeText(getApplicationContext(), "succ", Toast.LENGTH_SHORT).show();
            jobParameters = params;
            mywork();
            return true;
        }

        private void mywork() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "successs", Toast.LENGTH_SHORT).show();
                }
            }).start();
            //  this.jobFinished(jobParameters, true);
        }

        @Override
        public boolean onStopJob(JobParameters params) {
            Log.i("way","onstop");
            /*jobScheduler.cancel(123);
            // jobScheduler.schedule(jobInfo.build());
            Intent i=new Intent();
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setAction("my.custom.action");
            Jobschedular.this.sendBroadcast(i);*/
            return true;
        }
    }

    public class MyReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("way","broadcast");
            final Handler h=new Handler();
            final PendingResult pr=goAsync();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                           jobScheduler.schedule(jobInfo);
                        }
                    });
                    pr.finish();
                }
            }).start();
        }
    }
}
