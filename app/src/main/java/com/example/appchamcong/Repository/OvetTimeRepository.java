package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.OverTime;
import com.example.appchamcong.domain.Reward;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OvetTimeRepository {
    private final ApiService apiService;
    private Application context;

    public OvetTimeRepository( Application context) {
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
        this.context = context;
    }

    public MutableLiveData<Resource<ApiResponse<List<OverTime>>>> GetOverTime(int id){
        MutableLiveData<Resource<ApiResponse<List<OverTime>>>> overTime = new MutableLiveData<>();
        overTime.setValue(Resource.loading());

        apiService.GetOverTime(id).enqueue(new Callback<ApiResponse<List<OverTime>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<OverTime>>> call, Response<ApiResponse<List<OverTime>>> response) {
                if(response.isSuccessful()){
                    overTime.postValue(Resource.success(response.body()));
                }else {
                    overTime.postValue(Resource.error("error:" +response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<OverTime>>> call, Throwable t) {
                overTime.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return  overTime;
    }

}
