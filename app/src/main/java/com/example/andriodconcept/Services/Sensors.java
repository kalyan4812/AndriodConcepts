package com.example.andriodconcept.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.andriodconcept.R;

public class Sensors extends AppCompatActivity {
    TextView t1,t2,t3;
    SensorManager sm,sm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
         sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(new SensorListener() {
            @Override
            public void onSensorChanged(int sensor, float[] values) {
                t1.setText("x:"+values[0]);
                t2.setText("Y:"+values[1]);

            }

            @Override
            public void onAccuracyChanged(int sensor, int accuracy) {

            }
        },SensorManager.SENSOR_ACCELEROMETER);
        sm1=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm1.registerListener(new SensorListener() {
            @Override
            public void onSensorChanged(int sensor, float[] values) {

                t3.setText("TEMPERATUE:"+values[0]+"C");

            }

            @Override
            public void onAccuracyChanged(int sensor, int accuracy) {


            }
        },SensorManager.SENSOR_TEMPERATURE);
    }
}
