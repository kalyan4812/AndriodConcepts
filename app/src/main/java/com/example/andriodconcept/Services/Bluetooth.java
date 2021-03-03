package com.example.andriodconcept.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.andriodconcept.R;

import java.util.ArrayList;
import java.util.List;

public class Bluetooth extends AppCompatActivity {
    ListView lv;
    Switch s1;
    BluetoothAdapter bd;
    ArrayList<String> al1=new ArrayList<>();
    ArrayAdapter<String> ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        lv=findViewById(R.id.wlv);
        s1=findViewById(R.id.switch2);
        bd=BluetoothAdapter.getDefaultAdapter();
       s1.setChecked(bd.isEnabled());
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    bd.enable();
                }
                else{
                    bd.disable();
                }
            }
        });

    }

    public void bluetoothdevices(View view) {

        bd.startDiscovery();
        IntentFilter i=new IntentFilter();
        i.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(new MyBluetoothReciever(),i);

    }
    public void vibrate(View view) {
        Vibrator v=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(10000);

    }
    class MyBluetoothReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
           BluetoothDevice b= intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
           al1.add(b.getName()+ "\n"+b.getAddress());
            ad=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,al1);
            lv.setAdapter(ad);

        }
    }
}
