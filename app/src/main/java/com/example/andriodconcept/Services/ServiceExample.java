package com.example.andriodconcept.Services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.andriodconcept.R;
import com.example.andriodconcept.Services.MyService;

public class ServiceExample extends AppCompatActivity {
    ImageView im;
    Button play,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_example);
        im=findViewById(R.id.medialogo);
        play=findViewById(R.id.play);
        stop=findViewById(R.id.stop);

    }
    public void playservice(View view) {

        Intent i=new Intent(getApplicationContext(), MyService.class);
        startService(i);
    //    ContextCompat.startForegroundService(this,i);

    }
    public void stopservice(View view) {
        Intent i=new Intent(getApplicationContext(),MyService.class);
        stopService(i);

    }
    // BY USING SERVICE CONCEPT EVEN IF WE REMOVE APP FROM BACKGROUND STILL SONG WILL PLAY .
    // THAT IS WE ARE DOING SERVICE IN ABSENCE OF USER INTERACTION.


}
