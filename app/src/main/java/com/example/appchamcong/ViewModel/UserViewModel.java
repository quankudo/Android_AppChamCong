package com.example.appchamcong.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Repository.UserRepository;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.User;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;

    public UserViewModel() {
        ApiService apiService = ApiClient.getClient("djsjsk").create(ApiService.class);
        this.userRepository = new UserRepository((com.example.appchamcong.Api.ApiService) apiService);
    }

    public LiveData<Resource<String>> login(User user) {

        return userRepository.login(user);
    }
}
