package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.Reward;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardRepository {
    private final ApiService apiService;
    private Application context;

    public RewardRepository(Application context) {
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
        this.context = context;
    }

    public MutableLiveData<Resource<ApiResponse<List<Reward>>>> getReward(int id){
        MutableLiveData<Resource<ApiResponse<List<Reward>>>> reward = new MutableLiveData<>();
        reward.setValue(Resource.loading());

        apiService.GetReward(id).enqueue(new Callback<ApiResponse<List<Reward>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Reward>>> call, Response<ApiResponse<List<Reward>>> response) {
                if(response.isSuccessful()){
                    reward.postValue(Resource.success(response.body()));
                }else{
                    reward.postValue(Resource.error("error:" +response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Reward>>> call, Throwable t) {
                    reward.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return  reward;
    }
}
