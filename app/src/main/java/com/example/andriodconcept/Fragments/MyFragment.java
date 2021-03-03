package com.example.andriodconcept.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.andriodconcept.Fragments.ContactsFragment;
import com.example.andriodconcept.Fragments.GalleryFragment;
import com.example.andriodconcept.Fragments.HomeFragment;
import com.example.andriodconcept.R;

public class MyFragment extends AppCompatActivity implements HomeFragment.FragmentAListener {

    FragmentManager fm;
    FragmentTransaction ft;
    //
    HomeFragment hf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();

        // sending data from activity to a fragement.(HOME)
        hf=HomeFragment.newInstance("RECIEVED DATA FROM ACTIVITY TO FRAGEMENT",1);
        // sending data between frgamrnts or activity to fragments by interfaces.



        ft.add(R.id.frame,hf);
        ft.commit();


    }


   public void home(View view){
       ft=fm.beginTransaction();
       ft.replace(R.id.frame,hf);
       ft.commit();
   }
    public void gallery(View view){
        ft=fm.beginTransaction();
        ft.replace(R.id.frame,new GalleryFragment());
        ft.commit();
    }
    public void contacts(View view){
        ft=fm.beginTransaction();
        ft.replace(R.id.frame,new ContactsFragment());
        ft.commit();
    }

    @Override
    public void onInputASent(CharSequence input) {
        hf.updateEditText(input);
    }
}
