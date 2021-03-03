package com.example.andriodconcept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andriodconcept.RetrofitPackage.Hero;
import com.example.andriodconcept.RetrofitPackage.HeroInterface;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitLibrary extends AppCompatActivity {
ListView lv;
ArrayAdapter<String> ad;
static String [] hr,hr2,hr3;
static RetrofitLibrary ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {  // we create pojo classes place,places,places interface
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_library);
        lv=findViewById(R.id.plv);
        ref=this;
    }

    public void getHeroes(View view) {
        Retrofit r=new Retrofit.Builder().baseUrl("https://simplifiedcoding.net/demos/").addConverterFactory(GsonConverterFactory.create()).build();
        HeroInterface pi=r.create(HeroInterface.class);
        Call<List<Hero>> call=pi.getHeroes();
       call.enqueue(new Callback<List<Hero>>() {
           @Override
           public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
               List<Hero> heroes=response.body();
                hr=new String[heroes.size()];
               hr2=new String[heroes.size()];
               for(int i=0;i<heroes.size();i++){
                  hr[i]=heroes.get(i).getName();
                  hr2[i]=heroes.get(i).getImageurl();
                //  hr3[i]=heroes.get(i).getFirstappearance();

               }
            //   ad=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,hr);
               lv.setAdapter(new MyAdapter());
           }

           @Override
           public void onFailure(Call<List<Hero>> call, Throwable t) {

           }
       });
    }

}
