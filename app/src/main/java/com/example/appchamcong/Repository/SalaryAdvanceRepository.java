package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.Deduct;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaryAdvanceRepository {
    private final ApiService apiService;
    private  Application context;

    public SalaryAdvanceRepository(Application context) {
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
        this.context = context;
    }

    public MutableLiveData<Resource<ApiResponse<List<SalaryAdvance>>>> GetSalaryAdvance(int id){
        MutableLiveData<Resource<ApiResponse<List<SalaryAdvance>>>> salaryAdvance = new MutableLiveData<>();
        salaryAdvance.setValue(Resource.loading());

        apiService.GetSalaryAdvance(id).enqueue(new Callback<ApiResponse<List<SalaryAdvance>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SalaryAdvance>>> call, Response<ApiResponse<List<SalaryAdvance>>> response) {
                if(response.isSuccessful()){
                    salaryAdvance.postValue(Resource.success(response.body()));
                }else {
                    salaryAdvance.postValue(Resource.error("error: "+ response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SalaryAdvance>>> call, Throwable t) {
                    salaryAdvance.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return  salaryAdvance;
    }
}
