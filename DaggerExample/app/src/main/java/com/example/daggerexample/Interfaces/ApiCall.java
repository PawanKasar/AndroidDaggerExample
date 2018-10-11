package com.example.daggerexample.Interfaces;

import com.example.daggerexample.Models.HerosModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCall {

    @GET("marvel")
    Call<List<HerosModelClass>> getHeroes();
}
