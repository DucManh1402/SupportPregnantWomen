package com.example.test;

import com.example.test.Api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NguonApi {
    private Retrofit retrofit;
    public ApiService apiService;
     private String BASE_URL = "http://192.168.1.27:3000/";
//    private String BASE_URL = "http://192.168.1.4:3000/";

    //
    public void nguon() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }
}
