package com.example.andriodconcept.WebServices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andriodconcept.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlParsing extends AppCompatActivity {


    EditText ed1,ed2,ed3;
    Button read,update,insert,delete;
    SQLiteDatabase sq;
    ContentValues cv;
    String storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/myxml.xml";
    Document d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parsing);

        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        read=findViewById(R.id.read);
        update=findViewById(R.id.update);
        insert=findViewById(R.id.insert);
        delete=findViewById(R.id.delete);

    }

    public void delete(View view) {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            File f = new File(storagelocation);
            if (f.exists()) {
                d = db.parse(f);
                NodeList nl = d.getElementsByTagName("employee"); // for bringing employee tags in employees tag.
                for (int i = 0; i < nl.getLength(); i++) {
                    Node emp = nl.item(i);                         //for getting 1st employee
                    NodeList childofemp = emp.getChildNodes();        // for getting childs of 1st employee like email,name,password
                    String msg = "";
                    boolean found = false;
                    for (int j = 0; j < childofemp.getLength(); j++) {
                        Node ce = childofemp.item(j); //reading name,emial,password one by one.

                        if (ce.getNodeName().equalsIgnoreCase("email") && ed1.getText().toString().equals(ce.getFirstChild().getNodeValue().toString())) {
                            found = true;
                            emp.getParentNode().removeChild(emp);
                            // here emp=employee,parent node=employees so we are removing emp from employees.
                        }

                    }


                }
            }

            else{
                Toast.makeText(getApplicationContext(), "FILE DOES NOT EXIST ", Toast.LENGTH_LONG).show();
            }
            // recreation of xml.
            TransformerFactory tf=TransformerFactory.newInstance();
            t=tf.newTransformer();
            DOMSource source = new DOMSource(d);
            FileOutputStream fos=new FileOutputStream(storagelocation);
            StreamResult result = new StreamResult(fos);
            t.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void update(View view) {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            File f = new File(storagelocation);
            if (f.exists()) {
                d = db.parse(f);
                NodeList nl = d.getElementsByTagName("employee"); // for bringing employee tags in employees tag.
                for (int i = 0; i < nl.getLength(); i++) {
                    Node emp = nl.item(i);                         //for getting 1st employee
                    NodeList childofemp = emp.getChildNodes();        // for getting childs of 1st employee like email,name,password
                    String msg = "";
                    boolean found = false;
                    for (int j = 0; j < childofemp.getLength(); j++) {
                        Node ce = childofemp.item(j); //reading name,emial,password one by one.

                            if (ce.getNodeName().equalsIgnoreCase("email") && ed1.getText().toString().equals(ce.getFirstChild().getNodeValue().toString())) {
                                found = true;
                            }
                            if (found) {
                                if (ce.getNodeName().equalsIgnoreCase("password")) {
                                    ce.getFirstChild().setNodeValue(ed2.getText().toString());  //updating
                                }
                                if (ce.getNodeName().equalsIgnoreCase("name")) {
                                    ce.getFirstChild().setNodeValue(ed3.getText().toString()); //updating
                                }
                            }
                    }


                }
            }

            else{
                Toast.makeText(getApplicationContext(), "FILE DOES NOT EXIST ", Toast.LENGTH_LONG).show();
            }
          // recreation of xml.
            TransformerFactory tf=TransformerFactory.newInstance();
            t=tf.newTransformer();
            DOMSource source = new DOMSource(d);
            FileOutputStream fos=new FileOutputStream(storagelocation);
            StreamResult result = new StreamResult(fos);
            t.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void read(View view) {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            File f = new File(storagelocation);
            if (f.exists()) {
                d = db.parse(f);
                NodeList nl = d.getElementsByTagName("employee"); // for bringing employee tags in employees tag.
                for (int i = 0; i < nl.getLength(); i++) {
                    Node emp = nl.item(i);                         //for getting 1st employee
                    NodeList childofemp = emp.getChildNodes();        // for getting childs of 1st employee like email,name,password
                    String msg = "";
                    boolean found = false;
                    for (int j = 0; j < childofemp.getLength(); j++) {
                        Node ce = childofemp.item(j); //reading name,emial,password one by one.
                       // boolean found = false;
                       if (ed1.getText().toString().equals("")) {
                            msg = msg + ce.getFirstChild().getNodeValue() + "\n";   //getting value of emial,password,name.
                        } else {
                            if (ce.getNodeName().equalsIgnoreCase("email") && ed1.getText().toString().equals(ce.getFirstChild().getNodeValue().toString())) {
                                found = true;
                            }
                            if (found) {
                                if (ce.getNodeName().equalsIgnoreCase("password")) {
                                    ed2.setText(ce.getFirstChild().getNodeValue().toString());
                                }
                                if (ce.getNodeName().equalsIgnoreCase("name")) {
                                    ed3.setText(ce.getFirstChild().getNodeValue().toString());
                                }
                            }
                        }
                    }
                         Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                }
            }
            else{
                Toast.makeText(getApplicationContext(), "FILE DOES NOT EXIST ", Toast.LENGTH_LONG).show();
            }
        }
             catch (Exception e) {
               e.printStackTrace();
        }


    }
    HashSet<String> h=new HashSet<>();
    Transformer t;
    public void insert(View view) {
        try {
            File f=new File(storagelocation); // this file is to check if already xml file exist the we can add some other employee tag in it.
            if(!f.exists()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                d = db.newDocument();
                h.clear();
            }
            else{
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                d = db.parse(f);
            }
            Element e1;

                 e1 = d.createElement("employees");

                Element e2 = d.createElement("employee");
                Element e3 = d.createElement("email");
                Element e4 = d.createElement("password");
                Element e5 = d.createElement("name");

            // and for avoiding duplicates you can use hashset/hashmap.based on our key attribute.

                     String key=ed1.getText().toString();
                     if(h.contains(key)){
                         Toast.makeText(getApplicationContext(),"THIS MAIL ALREADY EXISTS",Toast.LENGTH_LONG).show();
                         return;
                     }
                     else{
                         h.add(key);
                     }



                Node n1 = d.createTextNode(key);
                Node n2 = d.createTextNode(ed2.getText().toString());
                Node n3 = d.createTextNode(ed3.getText().toString());

                e3.appendChild(n1);
                e4.appendChild(n2);
                e5.appendChild(n3);

                e2.appendChild(e3);
                e2.appendChild(e4);
                e2.appendChild(e5);
              if(!f.exists()) {
                  e1.appendChild(e2);

                  d.appendChild(e1);

              }

              if(f.exists()){
              d.getDocumentElement().appendChild(e2); // this is important to add a new employee.
              }
            // for writing oject data into file

            TransformerFactory tf=TransformerFactory.newInstance();
             t=tf.newTransformer();
            int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if ( permissioncheck1==PackageManager.PERMISSION_GRANTED) {

                FileOutputStream fos = new FileOutputStream(storagelocation);
                DOMSource source = new DOMSource(d);
                StreamResult result = new StreamResult(fos);
                t.transform(source, result);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 200:
                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if ( permissioncheck1==PackageManager.PERMISSION_GRANTED) {
                    storagelocation = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/myxml.xml";
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(storagelocation);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    DOMSource source = new DOMSource(d);
                    StreamResult result = new StreamResult(fos);
                    try {
                        t.transform(source, result);
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }
    }


}
