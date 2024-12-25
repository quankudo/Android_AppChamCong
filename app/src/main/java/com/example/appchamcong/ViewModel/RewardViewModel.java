package com.example.appchamcong.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appchamcong.Repository.RewardRepository;
import com.example.appchamcong.Repository.SalaryAdvanceRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.Reward;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.List;

public class RewardViewModel extends AndroidViewModel {
    private  final RewardRepository rewardRepository;

    public RewardViewModel(@NonNull Application context) {
        super(context);
        rewardRepository = new RewardRepository(context);
    }

    public LiveData<Resource<ApiResponse<List<Reward>>>> getReward(int id){
        return  rewardRepository.getReward(id);
    }



}
