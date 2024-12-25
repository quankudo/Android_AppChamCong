package com.example.appchamcong.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Repository.UserRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.User;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private MutableLiveData<Resource<String>> login;

    public UserViewModel(@NonNull Application context) {
        super(context);
        userRepository = new UserRepository(context);
        login = new MutableLiveData<>();
    }

    public LiveData<Resource<String>> getLogin() {
        return login;
    }
    public void login(User user) {
        userRepository.login(user, login);
    }

    public LiveData<Resource<ApiResponse<MyInfo>>> getMyInfo(){
        return userRepository.getMyInfo();
    }
}
