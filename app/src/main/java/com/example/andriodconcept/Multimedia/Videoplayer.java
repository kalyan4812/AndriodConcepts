package com.example.andriodconcept.Multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.andriodconcept.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Videoplayer extends AppCompatActivity {
VideoView video;
    ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(
            "https://cdn.videvo.net/videvo_files/video/premium/video0225/small_watermarked/MR_Stock%20Footage%20MR%20(2338)_preview.webm"));
    int indexing=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        video=findViewById(R.id.video);
        final MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(video);


        video.setMediaController(mediaController);
        // note first you have to set controller then path
        video.setVideoURI(Uri.parse(arrayList.get(indexing)));
        video.requestFocus();
        video.start();

       video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Video Completed", Toast.LENGTH_SHORT).show();
                if (indexing++ == arrayList.size()){
                    indexing=0;
                    mp.release();
                }
                else {
                    video.setVideoURI(Uri.parse(arrayList.get(indexing)));
                    video.start();
                    indexing++;
                }
            }
        });
        video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(Videoplayer.this, "Error: "+what+", "+extra, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
