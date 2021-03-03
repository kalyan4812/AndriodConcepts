package com.example.andriodconcept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andriodconcept.R;
import com.example.andriodconcept.WebServices.SOAPRequest;

import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WeatherApp extends AppCompatActivity {
EditText iso;
TextView curr;
 String   storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/myxml2.xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);
        curr=findViewById(R.id.curr);
       // iso=findViewById(R.id.editText2);
        StrictMode.ThreadPolicy st=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(st);
    }

    public void getweather(View view) {
        Mytask task=new Mytask();
        task.execute();

    }
    String res;
    public class Mytask extends AsyncTask{
        //String res;
        @Override
        protected Object doInBackground(Object[] objects) {
            iso=findViewById(R.id.editText2);
            SOAPRequest request=new SOAPRequest();
            res= request.requestcurrencybyiso(iso.getText().toString()).toString();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            System.out.println(res);
            Log.i("TAG",res);
          //  FileWriter fw= null;
            try {
                FileWriter  fw = new FileWriter(storagelocation);
                fw.write(String.valueOf(IOUtils.toInputStream(res)));
                fw.flush();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        //    f
            parseXml(res);
        }
    }

    public void parseXml(String res) {
       Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
        try {
            SAXParserFactory sf=SAXParserFactory.newInstance();
            SAXParser saxparser = sf.newSAXParser();
        //   final String msg="";

          //  File f=new File(storagelocation);

         //   InputStream is=new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));
            //IOUtils.toInputStream(res, StandardCharsets.UTF_8)
            saxparser.parse(IOUtils.toInputStream(res,"utf-8"),new DefaultHandler(){
                boolean found = false;
                String msg="";
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                  //  if(qName.equalsIgnoreCase("sName")){
                    //    found =true;
                  //  }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                      //  if(found) {
                        //    curr.setText(new String(ch, start, length));
                    msg=msg+new String(ch,start,length)+"\n";
                    curr.setText(msg);
                            //Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
                       // }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    super.endElement(uri, localName, qName);
                  // if(qName.equalsIgnoreCase("CountryNameResult")){
                       Toast.makeText(getApplicationContext(),"jjjj", Toast.LENGTH_LONG).show();
                    //   msg="";
                  //  }
                }
            });



        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
