package com.example.andriodconcept.Multimedia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Switch;

import com.example.andriodconcept.R;

import java.io.IOException;

import static android.Manifest.permission.READ_PHONE_NUMBERS;

public class Videorecord extends AppCompatActivity {
    SurfaceView sv;
    SurfaceHolder sh;
    MediaRecorder mr;
    String videoFilePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videorecord);
        videoFilePath =
                Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/myvideo.mp4";
        sv=findViewById(R.id.surface);
        sh=sv.getHolder();
        mr=new MediaRecorder();
        int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int permissioncheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissioncheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissioncheck1 == PackageManager.PERMISSION_GRANTED && permissioncheck2 == PackageManager.PERMISSION_GRANTED && permissioncheck3 == PackageManager.PERMISSION_GRANTED) {
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        // to set quality
            CamcorderProfile cp=CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
            mr.setProfile(cp);
        mr.setOutputFile(videoFilePath);

        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 160);
        }
        sh.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mr.setPreviewDisplay(holder.getSurface());
                try {
                    mr.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }
    public void videorecord(View view){
        mr.start();

    }
    public void stoprecord(View view){
        mr.stop();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 160:
                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
                int permissioncheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                int permissioncheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permissioncheck1 == PackageManager.PERMISSION_GRANTED && permissioncheck2 == PackageManager.PERMISSION_GRANTED && permissioncheck3 == PackageManager.PERMISSION_GRANTED) {
                    mr.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mr.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    // to set quality
                    CamcorderProfile cp=CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
                    mr.setProfile(cp);
                    mr.setOutputFile(videoFilePath);

                }
                break;
        }

    }
}
