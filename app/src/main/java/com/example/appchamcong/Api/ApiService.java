package com.example.appchamcong.Api;

import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.Person_Timekeeping;
import com.example.appchamcong.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("Login")
    Call<String> login(@Body User user);

    @POST("users")
    Call<User> createUser(@Body User user);

    @GET("User/getMyInfo")
    Call<ApiResponse<MyInfo>> getMyInfo();

    @GET("Work/getWordPerson")
    Call<ApiResponse<List<Person_Timekeeping>>> getWorkPerson();

    @GET("Work/getWorkGroup")
    Call<ApiResponse<List<Person_Timekeeping>>> getWorkGroup();

    @POST("Work/AddMember")
    Call<ApiResponse<String>> AddMember(@Query("code") String code);
}
