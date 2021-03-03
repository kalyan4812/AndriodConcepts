package com.example.andriodconcept.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.andriodconcept.R;

public class Webview extends AppCompatActivity {
EditText ed;
ImageButton imgbtn;
WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wv=(WebView)findViewById(R.id.wv);
        imgbtn= (ImageButton) findViewById(R.id.imgbtn);
        ed=(EditText) findViewById(R.id.ed);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(getApplicationContext(), "onPageStarted...", Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Toast.makeText(getApplicationContext(), "shouldOverrideUrlLoading...", Toast.LENGTH_LONG).show();
                return super.shouldOverrideUrlLoading(view, request);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(), "onPageFinished...", Toast.LENGTH_LONG).show();
            }
        });
           wv.getSettings().setJavaScriptEnabled(true);
           wv.getSettings().setBuiltInZoomControls(true);
           wv.addJavascriptInterface(this,"myinterface");

    }
    @JavascriptInterface
   public void displayMsg(String name,String pass){
        Toast.makeText(getApplicationContext(),name+"\n"+pass,Toast.LENGTH_LONG).show();
    }

    public void search(View view) {
        switch (view.getId()){

            case R.id.youtube:
                wv.loadUrl("https://www.youtube.com");
                break;

            case R.id.googlelink:
                wv.loadUrl("https://www.google.com");
                break;
            case R.id.htmllink:
                wv.loadUrl("file:///android_asset/login.html");
                break;
            case R.id.instagramlink:
                wv.loadUrl("https://www.instagram.com");
                break;
            case R.id.githublink:
                wv.loadUrl("https://www.github.com");
                break;
            case R.id.imgbtn:
                String str=ed.getText().toString();
                if (!str.equals("")) {

                    wv.loadUrl("https://"+str);
                } else {
                    Toast.makeText(getApplicationContext(), "ENTER SOME THING", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
