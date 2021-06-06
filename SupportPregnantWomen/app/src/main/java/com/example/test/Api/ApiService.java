package com.example.test.Api;

import com.example.test.Model.Babies;
import com.example.test.Model.Diary;
import com.example.test.Model.HandleError;
import com.example.test.Model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

import retrofit2.http.POST;
import retrofit2.http.Body;

public interface ApiService {

    //User
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

    //Baby
    @POST("api/babies/update-baby")
    Call<Babies> UpdateBaby(@Body HashMap<String, String>map);

    @POST("api/babies")
    Call<Babies> insertBaby(@Body HashMap<String, String>map);

    @POST("api/babies/get-baby")
    Call<Babies> getBaby(@Body HashMap<String, String>map);

    //Diary

    @POST("api/diaries")
    Call<Diary> addDiary(@Body HashMap<String, String>map);

    //lấy all nhật kí
    @POST("api/diaries/get-diaries")
    Call<List<Diary>> getDiaries(@Body HashMap<String, String>map);
//    Call<Diary> getDiaries(@Body HashMap<String, String>map);

    //lấy 1 nhật kí
    @POST("api/diaries/get-diary")
    Call<Diary> getDiary_Day(@Body HashMap<String, String>map);

    @POST("api/diaries/update-diary")
    Call<Diary> UpdateDiary(@Body HashMap<String, String>map);

    @POST("api/diaries/delete-diary")
    Call<Diary> DeleteDiary(@Body HashMap<String, String>map);
}
