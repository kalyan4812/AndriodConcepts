package com.example.andriodconcept.Services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriodconcept.R;

import static android.Manifest.permission.READ_PHONE_NUMBERS;

public class Telephone extends AppCompatActivity {
    EditText ed, ed2, ed3, ed4, ed5, ed6;
    Button msg, call, email, attach;
    Uri uri;
    TelephonyManager tm;
    String mynum;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        ed = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        ed5 = (EditText) findViewById(R.id.ed5);
        ed6 = (EditText) findViewById(R.id.ed6);
        msg = findViewById(R.id.msg);
        call = findViewById(R.id.call);
        attach = findViewById(R.id.attach);
        email = findViewById(R.id.email);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendmsg(View view) {
     String msg=ed2.getText().toString().trim();
        int permissioncheck = ContextCompat.checkSelfPermission(Telephone.this, Manifest.permission.SEND_SMS);

        if (permissioncheck == PackageManager.PERMISSION_GRANTED) {
            if (!(TextUtils.isEmpty(ed.getText().toString().trim()))) {
                SmsManager sm = SmsManager.getDefault();
                if(msg==""){
                    msg="BLANK MESSAGE";
                }
                sm.sendTextMessage(ed.getText().toString(), null, msg, null, null);
                Toast.makeText(getApplicationContext(), "MESSAGE SENT", Toast.LENGTH_LONG).show();
                mynumber();
            } else {
                Toast.makeText(getApplicationContext(), "PLEASE ENTER PHONE NUMBER", Toast.LENGTH_LONG).show();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 114);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void mynumber() {
        int permissioncheck1 = ContextCompat.checkSelfPermission(Telephone.this, permission.READ_SMS);
        int permissioncheck2 = ContextCompat.checkSelfPermission(Telephone.this, permission.READ_PHONE_STATE);
        int permissioncheck3 = ContextCompat.checkSelfPermission(Telephone.this, READ_PHONE_NUMBERS);
        if (permissioncheck1 == PackageManager.PERMISSION_GRANTED && permissioncheck2 == PackageManager.PERMISSION_GRANTED && permissioncheck3 == PackageManager.PERMISSION_GRANTED) {
            tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            assert tm != null;
            mynum = tm.getDeviceId().toString();
            ed3.setText(mynum);
            return;
           // Toast.makeText(getApplicationContext(),"YOUR NUMBER IS SHOWN"+mynum,Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission.READ_SMS, READ_PHONE_NUMBERS, permission.READ_PHONE_STATE}, 118);
        }


    }

    public void callme(View view) {
        Toast.makeText(getApplicationContext(), "making call", Toast.LENGTH_LONG).show();
        Intent i = new Intent();
        i.setAction(Intent.ACTION_DIAL);//ITS ACTION_DIAL NOT ACTION_CALL
        i.setData(Uri.parse("tel:" + ed.getText().toString()));
        startActivity(i);

    }

    public void addattachment(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("*/*");
        startActivityForResult(i, 123);

    }

    public void sendemail(View view) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{ed3.getText().toString()});
        i.putExtra(Intent.EXTRA_SUBJECT, ed4.getText().toString());
        i.putExtra(Intent.EXTRA_TEXT, ed5.getText().toString());
        i.putExtra(Intent.EXTRA_STREAM, uri);
        i.setType("message/rfc822");//to enable multimedia type of data
        startActivity(i.createChooser(i, "SELECT ANY EMAIL SUPPORT"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
// for attachment
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        ed6.setText(uri.toString());

        if (requestCode == 114 && resultCode == RESULT_OK) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                mynum = tm.getLine1Number();
                ed3.setText(mynum);
                return;
            }


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 114:
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ed.getText().toString() != "") {
                        SmsManager sm = SmsManager.getDefault();
                        sm.sendTextMessage(ed.getText().toString(), null, ed2.getText().toString(), null, null);
                        Toast.makeText(getApplicationContext(), "MESSAGE SENT", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "PLEASE ENTER PHONE NUMBER", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "YOU DONT HAVE PERMISSION TO MAKE THIS ACTION", Toast.LENGTH_LONG).show();
                }
                break;
            case 118:
              //  if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED) {
                    int permissioncheck1 = ContextCompat.checkSelfPermission(Telephone.this, permission.READ_SMS);
                    int permissioncheck2 = ContextCompat.checkSelfPermission(Telephone.this, permission.READ_PHONE_STATE);
                    int permissioncheck3 = ContextCompat.checkSelfPermission(Telephone.this, READ_PHONE_NUMBERS);
                    if (permissioncheck1 == PackageManager.PERMISSION_GRANTED && permissioncheck2 == PackageManager.PERMISSION_GRANTED && permissioncheck3 == PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
             //   assert tm != null;
                mynum = tm.getDeviceId().toString();

                ed3.setText(mynum);
                Toast.makeText(getApplicationContext(),"YOUR NUMBER IS SHOWN"+mynum,Toast.LENGTH_LONG).show();

                    //Toast.makeText(getApplicationContext(),"YOU DONT HAVE PERMISSION TO MAKE THIS ACTION",Toast.LENGTH_LONG).show();
              //  }
                break;
       }
    }
}
