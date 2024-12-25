package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.TimeKeepResponse;
import com.example.appchamcong.domain.TimeKeepingDetails;
import com.example.appchamcong.domain.TimekeepingReq;
import com.example.appchamcong.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimekeepingRepository {
    private ApiService apiService;

    public TimekeepingRepository(Application context) {
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    public MutableLiveData<Resource<ApiResponse<String>>> timeKeeping(TimekeepingReq req) {
        MutableLiveData<Resource<ApiResponse<String>>> resp = new MutableLiveData<>();
        resp.setValue(Resource.loading());

        apiService.timeKeeping(req).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if(response.isSuccessful()){
                    resp.postValue(Resource.success(response.body()));
                }
                else {
                    resp.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                resp.postValue(Resource.error("Error: " + t.getMessage()));
            }
        });
        return resp;
    }

    public MutableLiveData<Resource<ApiResponse<String>>> timeKeepingByOwner(TimekeepingReq req) {
        MutableLiveData<Resource<ApiResponse<String>>> resp = new MutableLiveData<>();
        resp.setValue(Resource.loading());

        apiService.timeKeepingByOwner(req).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if(response.isSuccessful()){
                    resp.postValue(Resource.success(response.body()));
                }
                else {
                    resp.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                resp.postValue(Resource.error("Error: " + t.getMessage()));
            }
        });
        return resp;
    }

    public MutableLiveData<Resource<ApiResponse<TimeKeepingDetails>>> getTimeKeepingDetails(int id) {
        MutableLiveData<Resource<ApiResponse<TimeKeepingDetails>>> resp = new MutableLiveData<>();
        resp.setValue(Resource.loading());

        apiService.getTimeKeepingDetails(id).enqueue(new Callback<ApiResponse<TimeKeepingDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<TimeKeepingDetails>> call, Response<ApiResponse<TimeKeepingDetails>> response) {
                if(response.isSuccessful()){
                    resp.postValue(Resource.success(response.body()));
                }
                else {
                    resp.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TimeKeepingDetails>> call, Throwable t) {
                resp.postValue(Resource.error("Error: " + t.getMessage()));
            }
        });
        return resp;
    }

    public MutableLiveData<Resource<ApiResponse<TimeKeepingDetails>>> getTimeKeepingDetailsByOwner(int idNhom, int idNhanVien) {
        MutableLiveData<Resource<ApiResponse<TimeKeepingDetails>>> resp = new MutableLiveData<>();
        resp.setValue(Resource.loading());

        apiService.getTimeKeepingDetailsByOwner(idNhom, idNhanVien).enqueue(new Callback<ApiResponse<TimeKeepingDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<TimeKeepingDetails>> call, Response<ApiResponse<TimeKeepingDetails>> response) {
                if(response.isSuccessful()){
                    resp.postValue(Resource.success(response.body()));
                }
                else {
                    resp.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TimeKeepingDetails>> call, Throwable t) {
                resp.postValue(Resource.error("Error: " + t.getMessage()));
            }
        });
        return resp;
    }


    public MutableLiveData<Resource<ApiResponse<List<TimeKeepResponse>>>> getTimeKeepings(int id) {
        MutableLiveData<Resource<ApiResponse<List<TimeKeepResponse>>>> resp = new MutableLiveData<>();
        resp.setValue(Resource.loading());

        apiService.getTimeKeepings(id).enqueue(new Callback<ApiResponse<List<TimeKeepResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<TimeKeepResponse>>> call, Response<ApiResponse<List<TimeKeepResponse>>> response) {
                if(response.isSuccessful()){
                    resp.postValue(Resource.success(response.body()));
                }
                else {
                    resp.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<TimeKeepResponse>>> call, Throwable t) {
                resp.postValue(Resource.error("Error: " + t.getMessage()));
            }
        });
        return resp;
    }

    public MutableLiveData<Resource<ApiResponse<List<TimeKeepResponse>>>> getTimeKeepingsByOwner(int idnv, int idNhom) {
        MutableLiveData<Resource<ApiResponse<List<TimeKeepResponse>>>> resp = new MutableLiveData<>();
        resp.setValue(Resource.loading());

        apiService.getTimeKeepingsByOwner(idnv, idNhom).enqueue(new Callback<ApiResponse<List<TimeKeepResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<TimeKeepResponse>>> call, Response<ApiResponse<List<TimeKeepResponse>>> response) {
                if(response.isSuccessful()){
                    resp.postValue(Resource.success(response.body()));
                }
                else {
                    resp.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<TimeKeepResponse>>> call, Throwable t) {
                resp.postValue(Resource.error("Error: " + t.getMessage()));
            }
        });
        return resp;
    }
}
