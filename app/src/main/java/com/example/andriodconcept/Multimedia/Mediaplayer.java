package com.example.andriodconcept.Multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.andriodconcept.R;

import java.util.concurrent.TimeUnit;

public class Mediaplayer extends AppCompatActivity {
    MediaPlayer mp;
    SeekBar sb;
  Handler h=new Handler();
  TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        mp= MediaPlayer.create(getApplicationContext(),R.raw.song);
        sb=findViewById(R.id.seek);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        // setting length of seek bar
        sb.setMax(mp.getDuration());
       double endTime =mp.getDuration();
      double startTime=mp.getCurrentPosition();


        tv1.setText(String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

        tv2.setText(String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes((long) endTime),
                TimeUnit.MILLISECONDS.toSeconds((long) endTime)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) endTime))));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp.seekTo(progress);// here id progress is point where we touch on seek bar
                updateseekbar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // now to update seekbar position for every 1 sec we should use handler
       updateseekbar();
    }
    Runnable run=new Runnable() {
        @Override
        public void run() {

            updateseekbar();
        }
    };

    private void updateseekbar() {
        sb.setProgress(mp.getCurrentPosition());
      double  startTime =mp.getCurrentPosition();

        tv1.setText(String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
        h.postDelayed(run,1000);// we have to pass runnable object
    }

    public void backward(View view) {
        mp.seekTo(mp.getCurrentPosition()-(mp.getDuration()/10));
    }

    public void play(View view) {
        mp.start();
    }

    public void pause(View view) {
        mp.pause();
    }

    public void stop(View view) {
        mp.stop();
        mp= MediaPlayer.create(getApplicationContext(),R.raw.song);
    }

    public void forward(View view) {
        mp.seekTo(mp.getCurrentPosition()+(mp.getDuration()/10));
    }
}
