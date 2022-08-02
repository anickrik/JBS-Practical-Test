package com.practical.myapplication.apis;

import com.practical.myapplication.models.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {
    @GET("/photos")
    Call<List<DataModel>> getDataList();
}
