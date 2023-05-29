package com.example.movieapp.retrofit;

import com.example.movieapp.dao.MovieInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApplication {

    public static RetrofitApplication instance = null;
    public MovieInterface movieInterface;
    private static String Baseurl = "https://jsonplaceholder.typicode.com";

    RetrofitApplication() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieInterface = retrofit.create(MovieInterface.class);
    }

    public static RetrofitApplication getClient(){
        if(instance == null){
            instance = new RetrofitApplication();
        }
        return instance;
    }




}
