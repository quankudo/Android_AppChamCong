package com.example.appchamcong.Api;

import com.example.appchamcong.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("Login")
    Call<String> login(@Body User user);

    @POST("users")
    Call<User> register(@Body User user);

    @PATCH("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @GET("users")
    Call<User> getUser();
}
