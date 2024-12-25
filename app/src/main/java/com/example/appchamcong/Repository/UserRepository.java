package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.User;
import com.example.appchamcong.Utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class UserRepository {
    private ApiService apiService;
    private Application context;

    public UserRepository(Application context) {
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
        this.context = context;
    }

    public void login(User user, MutableLiveData<Resource<String>> login) {
        login.setValue(Resource.loading());

        apiService.login(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    login.postValue(Resource.success(response.body().toString()));
                    apiService = ApiClient.getClient(context).create(ApiService.class);
                    ApiClient.jwt = true;
                } else {
                    login.postValue(Resource.error("Error: " + response.message() + "Error1111"));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                login.postValue(Resource.error("1. Failed to fetch data: " + t.getMessage()));
            }
        });
    }

    public MutableLiveData<Resource<ApiResponse<MyInfo>>> getMyInfo() {
        MutableLiveData<Resource<ApiResponse<MyInfo>>> myInfoApiResponse = new MutableLiveData<>();
        myInfoApiResponse.setValue(Resource.loading());
        apiService.getMyInfo().enqueue(new Callback<ApiResponse<MyInfo>>() {
            @Override
            public void onResponse(Call<ApiResponse<MyInfo>> call, Response<ApiResponse<MyInfo>> response) {
                if(response.isSuccessful()){
                    myInfoApiResponse.postValue(Resource.success(response.body()));
                }
                else {
                    myInfoApiResponse.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<MyInfo>> call, Throwable t) {
                myInfoApiResponse.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return myInfoApiResponse;
    }
}
