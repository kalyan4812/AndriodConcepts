package com.example.andriodconcept.Multimedia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andriodconcept.R;

import java.io.File;

public class Audiorecord extends AppCompatActivity {
Button b1,b2;
MediaRecorder mr;
String audioFilePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiorecord);
        b1=findViewById(R.id.record);
        b2=findViewById(R.id.stop);
        mr=new MediaRecorder();
        audioFilePath =
                Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/myaudio.3gp";
        //setting source mic or phone
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1==PackageManager.PERMISSION_GRANTED) {
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            //setting output format
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //setting quality
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //setting storage location
            mr.setOutputFile(audioFilePath);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 120);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 120:
                int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1==PackageManager.PERMISSION_GRANTED) {
                    mr.setAudioSource(MediaRecorder.AudioSource.MIC);
                    //setting output format
                    mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    //setting quality
                    mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    //setting storage location
                    mr.setOutputFile(audioFilePath);
                }
                //super.onActivityResult(requestCode, resultCode, data);

        }
    }
    public void record(View view){
        try{
            mr.prepare();
            mr.start();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }
    public void stop(View view){
        mr.stop();
        mr.release();

    }

}
