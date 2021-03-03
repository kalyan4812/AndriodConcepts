package com.example.andriodconcept.WebServices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriodconcept.R;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlParsing2 extends AppCompatActivity {
EditText ed1;

       String storagelocation ;

    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parsing2);
        Button saxread=findViewById(R.id.saxread);
        Button pullread=findViewById(R.id.pullread);
        ed1=findViewById(R.id.ed1);
        key=ed1.getText().toString();

    }

    public void saxread(View view) {

            try {
                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

                if ( permissioncheck1== PackageManager.PERMISSION_GRANTED) {
                    storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/myxml.xml";
                }
                else{
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 280);
                }

                SAXParserFactory sf=SAXParserFactory.newInstance();
                SAXParser saxparser = sf.newSAXParser();
                final File f = new File(storagelocation);
                saxparser.parse(f, new DefaultHandler() {
                    String msg = "";
                    boolean found = false;
                    boolean id = false;

                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        super.startElement(uri, localName, qName, attributes);
                       if (qName.equalsIgnoreCase("email")) {
                            id = true;
                        }
                    }

                    @Override
                    public void characters(char[] ch, int start, int length) throws SAXException {
                        super.characters(ch, start, length);
                        if (id) {
                            if (ed1.getText().toString().equalsIgnoreCase(new String(ch, start, length))) {
                                found = true;
                            }
                        }
                        msg = msg + new String(ch, start, length) + "\n";
                    }

                    @Override
                    public void endElement(String uri, String localName, String qName) throws SAXException {
                        super.endElement(uri, localName, qName);
                        if (qName.equalsIgnoreCase("email")) {
                            id = false;
                        }
                        if (qName.equalsIgnoreCase("employee")) {
                            if (found) {
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                                found = false;
                            }
                           msg = "";
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    public void pullread(View view) {
        try {
            int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

            if ( permissioncheck1== PackageManager.PERMISSION_GRANTED) {
                storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/myxml.xml";
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 280);
            }
            XmlPullParserFactory pf = XmlPullParserFactory.newInstance();
            XmlPullParser pullparser = pf.newPullParser();
            FileReader fr=new FileReader(storagelocation);
            pullparser.setInput(fr);
            int i=pullparser.getEventType();
            boolean found=false;
            boolean email=false;
            String msg="";
            while (i!=pullparser.END_DOCUMENT){

                if(i==pullparser.START_TAG){
                    if(pullparser.getName().equalsIgnoreCase("email")){
                        email=true;
                    }

                }
                if(i==pullparser.TEXT){
                    if(email){
                        if(ed1.getText().toString().equals(pullparser.getText().toString())){
                            found=true;
                        }
                    }
                    msg=msg+pullparser.getText().toString()+"\n";

                }
                if(i==pullparser.END_TAG){

                    if(pullparser.getName().equalsIgnoreCase("employee")){
                        if(found){
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                            found=false;
                        }
                        msg="";
                    }

                }
                i=pullparser.next();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 280:

                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

                if ( permissioncheck1== PackageManager.PERMISSION_GRANTED) {
                    storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/myxml.xml";
                }
                  break;

        }
    }
}

