package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.AddDeduct;
import com.example.appchamcong.domain.Deduct;
import com.example.appchamcong.domain.Reward;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeductRepository {
    private final ApiService apiService;
    private Application context;

    public DeductRepository(Application context) {

        this.apiService = ApiClient.getClient(context).create(ApiService.class);
        this.context = context;
    }

    public  MutableLiveData<Resource<ApiResponse<List<Deduct>>>> GetDeDuct(int id){
        MutableLiveData<Resource<ApiResponse<List<Deduct>>>> deDuct = new MutableLiveData<>();
        deDuct.setValue(Resource.loading());

        apiService.GetDeduct(id).enqueue(new Callback<ApiResponse<List<Deduct>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Deduct>>> call, Response<ApiResponse<List<Deduct>>> response) {
                if(response.isSuccessful()){
                    deDuct.postValue(Resource.success(response.body()));
                }else{
                    deDuct.postValue(Resource.error("error:" +response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Deduct>>> call, Throwable t) {
                deDuct.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return deDuct;
    }

    public void AddDeduct(AddDeduct deduct, MutableLiveData<Resource<String>> result) {
        result.setValue(Resource.loading());

        apiService.AddDeduct(deduct).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() ) {
                    result.setValue(Resource.success(response.body().toString()));
                } else {
                    result.setValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                result.setValue(Resource.error("Failure: " + t.getMessage()));
            }
        });

    }
}
