package com.example.andriodconcept.Basics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.andriodconcept.R;

public class MenuAndActionBar extends AppCompatActivity {
MenuInflater mi;
ActionBar ab;
RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_and_action_bar);
        rb=findViewById(R.id.rb);
        ab=getSupportActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccent));
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener listener=new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                Toast.makeText(getApplicationContext(),tab.getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {


            }
        };
        ab.addTab(ab.newTab().setText("CHAT").setTabListener(listener));
        ab.addTab(ab.newTab().setText("HOME").setTabListener(listener));
        ab.addTab(ab.newTab().setText("CALL").setTabListener(listener));
        ab.addTab(ab.newTab().setText("DETAILS").setTabListener(listener));
        ab.addTab(ab.newTab().setText("CONTACT US").setTabListener(listener));


        // RATING BAR
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"YOUR RATING IS"+rating,Toast.LENGTH_LONG).show();
            }
        });






    }
    // to overeide press ctrl+O

    // menu code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mi=getMenuInflater();
        mi.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // you can use switch by item.getItemId()
        Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }


    // points to note in mymenu xml
    // showASaction attribute,is used to select the location for placing menu item ,you can place it as text in mneu or
    // show as icon in Action Bar .if Room means if place is there in Action bar it place item in action bar
    // if you keep attribute as always it will we always shown in action bar.
    ///////////////////////////////////////////////////////////////////////////////
   // RATING BAR

}
