package com.practical.myapplication.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static String baseurl = "https://jsonplaceholder.typicode.com";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit()
    {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
