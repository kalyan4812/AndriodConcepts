package com.example.andriodconcept.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.andriodconcept.R;

import java.util.ArrayList;
import java.util.List;

public class Wifi extends AppCompatActivity {
ListView lv;
Switch s1;
WifiManager wm;
ArrayList<String> al1=new ArrayList<>();
    ArrayAdapter<String> ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        lv=findViewById(R.id.wlv);
        s1=findViewById(R.id.switch2);
        wm=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int state=wm.getWifiState();
        if(state==1 || state==0){ // means 0-disabled 1-disabling
            s1.setChecked(false);
        }
        else if(state==2 || state==3){ // 2-enabling,3-enabled
            s1.setChecked(true);
        }
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    wm.setWifiEnabled(true);
                }
                else{
                    wm.setWifiEnabled(false);
                }
            }
        });

    }

    public void availabledevices(View view) {
        List<ScanResult> l1=wm.getScanResults();
        al1.clear();
        for(ScanResult sr:l1){
            al1.add(sr.SSID +" "+ sr.frequency);
        }
        ad=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,al1);
        lv.setAdapter(ad);
        //lv.setVisibility(View.VISIBLE);

    }
    public void paireddevices(View view) {
        List<WifiConfiguration> l2=wm.getConfiguredNetworks();
        al1.clear();
        for(WifiConfiguration wc:l2){
            al1.add(wc.SSID +" "+ wc.status);
        }
        ad=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,al1);
        lv.setAdapter(ad);
     //   lv.setVisibility(View.VISIBLE);
    }
}
