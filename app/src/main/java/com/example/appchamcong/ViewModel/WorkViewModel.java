package com.example.appchamcong.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Repository.UserRepository;
import com.example.appchamcong.Repository.WorkRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.Person_Timekeeping;

import java.util.List;

public class WorkViewModel extends AndroidViewModel {
    private final WorkRepository workRepository;

    public WorkViewModel(@NonNull Application context) {
        super(context);
        workRepository = new WorkRepository(context);
    }

    public LiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkPerson(){
        return workRepository.getWorkPerson();
    }

    public LiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkGroup(){
        return workRepository.getWorkGroup();
    }

    public LiveData<Resource<ApiResponse<String>>> AddMember(String code){
        return workRepository.addMember(code);
    }
}
