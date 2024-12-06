package com.example.appchamcong.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.domain.User;
import com.example.appchamcong.Utils.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class UserRepository {
    private final ApiService apiService;

    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<Resource<String>> login(User user) {
        MutableLiveData<Resource<String>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());

        apiService.login(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(Resource.success(response.body()));
                } else {
                    liveData.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                liveData.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });

        return liveData;
    }
}
