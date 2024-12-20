package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.Person_Timekeeping;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkRepository {

    private ApiService apiService;
    private Application context;

    public WorkRepository(Application context) {
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
        this.context = context;
    }

    public MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkPerson() {
        MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getWorkPerson().enqueue(new Callback<ApiResponse<List<Person_Timekeeping>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Person_Timekeeping>>> call, Response<ApiResponse<List<Person_Timekeeping>>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Person_Timekeeping>>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkGroup() {
        MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getWorkGroup().enqueue(new Callback<ApiResponse<List<Person_Timekeeping>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Person_Timekeeping>>> call, Response<ApiResponse<List<Person_Timekeeping>>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Person_Timekeeping>>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<String>>> addMember(String code) {
        MutableLiveData<Resource<ApiResponse<String>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.AddMember(code).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }
}
