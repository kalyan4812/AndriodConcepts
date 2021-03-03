package com.example.andriodconcept.ContentProvider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.andriodconcept.R;

public class Content extends AppCompatActivity {
    ListView lv;
    TextView pname,pnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
       // pname=findViewById(R.)
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        if (permissioncheck == PackageManager.PERMISSION_GRANTED) {
            lv = findViewById(R.id.lv);
            ContentResolver cr = getContentResolver();
          // u can get calllogs also instead of contacts,to sort the list paste any item from from array in last parameter of cursor.
            Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            String[] from = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            int[] to = new int[]{R.id.pname, R.id.pnum};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.contentproviderlayout
                    , c, from, to);
            lv.setAdapter(adapter);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 11);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 11:
                int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

                if (permissioncheck == PackageManager.PERMISSION_GRANTED) {
                    lv = findViewById(R.id.lv);
                    ContentResolver cr = getContentResolver();

                    Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                    String[] from = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
                    int[] to = new int[]{R.id.pname, R.id.pnum};
                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.contentproviderlayout
                            , c, from, to);
                    lv.setAdapter(adapter);
                }
                break;

        }
    }
}
