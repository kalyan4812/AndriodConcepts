package com.example.andriodconcept.Multimedia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.andriodconcept.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Takephoto extends AppCompatActivity {
    ImageView img;
    Button cam,gal;
  String imageFilePath;
  File f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);
        img=findViewById(R.id.photoview);
        imageFilePath =
                Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/myimg.jpg";
    }
    public void camera(View view){
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1==PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent();
            i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
           // f = new File(imageFilePath);
          //  i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            startActivityForResult(i,44);
            f = new File(imageFilePath);
              i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            Toast.makeText(getApplicationContext(),"IN  RESULT",Toast.LENGTH_LONG).show();
        }

    }
    public void picfromgallery(View view){
        Intent i=new Intent();
        i.setAction(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,8);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      //  Uri file=data.getData();
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 44:
                int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"IN ACTIVITY RESULT",Toast.LENGTH_LONG).show();
                    Bitmap b=(Bitmap) data.getExtras().get("data");

                  //  assert data != null;
                  //  img.setImageURI(data.getData());
                    img.setImageBitmap(b);
                }


              break;
            case 8:
                img.setImageURI(data.getData());
                break;

        }
    }
}
