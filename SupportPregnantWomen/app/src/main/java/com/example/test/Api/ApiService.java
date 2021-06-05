package com.example.test.Api;

import com.example.test.Model.Babies;
import com.example.test.Model.HandleError;
import com.example.test.Model.Note;
import com.example.test.Model.User;

import java.util.HashMap;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Query;

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
    @POST("api/diaries/get-diaries")
    Call<Note> getDiary(@Body HashMap<String, String>map);

    @POST("api/diaries/get-diary")
    Call<Note> getDiaryDay(@Body HashMap<String, String>map);

    @POST("api/diaries/update-diary")
    Call<Note> UpdateDiary(@Body HashMap<String, String>map);

    @POST("api/diaries/delete-diary")
    Call<Note> DeleteDiary(@Body HashMap<String, String>map);
}
