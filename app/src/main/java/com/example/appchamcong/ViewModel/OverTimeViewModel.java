package com.example.appchamcong.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Repository.OvetTimeRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.OverTime;
import com.example.appchamcong.domain.Reward;

import java.util.List;

public class OverTimeViewModel extends AndroidViewModel {
    private  final OvetTimeRepository ovetTimeRepository;
    public OverTimeViewModel(@NonNull Application context) {
        super(context);
        ovetTimeRepository = new OvetTimeRepository(context);
    }

    public MutableLiveData<Resource<ApiResponse<List<OverTime>>>> getOverTime(int id){
        return ovetTimeRepository.GetOverTime(id);
    }

}
