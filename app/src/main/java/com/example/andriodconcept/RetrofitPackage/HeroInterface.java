package com.example.andriodconcept.RetrofitPackage;

import com.example.andriodconcept.RetrofitPackage.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HeroInterface {
    @GET("marvel")
    Call<List<Hero>>  getHeroes();
}
