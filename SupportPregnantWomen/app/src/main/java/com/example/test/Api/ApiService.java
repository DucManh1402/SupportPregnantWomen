package com.example.test.Api;

import com.example.test.Model.Babies;
import com.example.test.Model.HandleError;
import com.example.test.Model.User;

import java.util.HashMap;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Query;

public interface ApiService {
    @POST("api/auth/login")
    Call<User> executeLogin(@Body HashMap<String, String>map);

    @POST("api/users")
    Call<Void> executeSignUp(@Body HashMap<String, String>map);

    @POST("api/users/get-user")
    Call<User> getUser(@Body HashMap<String, String>map);

    @POST("api/users/update-user")
    Call<User> UpdateUser(@Body HashMap<String, String>map);

    @POST("api/users/update-password")
    Call<HandleError> UpdatePassUser(@Body HashMap<String, String>map);

    @POST("api/babies/update-baby")
    Call<Babies> UpdateBaby(@Body HashMap<String, String>map);

    @POST("api/babies")
    Call<Babies> insertBaby(@Body HashMap<String, String>map);

    @POST("api/babies/get-baby")
    Call<Babies> getBaby(@Body HashMap<String, String>map);

}
