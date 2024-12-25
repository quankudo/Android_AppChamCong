package com.example.appchamcong.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Repository.DeductRepository;
import com.example.appchamcong.Repository.RewardRepository;
import com.example.appchamcong.Repository.UserRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.AddDeduct;
import com.example.appchamcong.domain.Deduct;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.Person_Timekeeping;
import com.example.appchamcong.domain.Reward;
import com.example.appchamcong.domain.User;

import java.util.List;

public class DeDuctViewModel extends AndroidViewModel {
    private final DeductRepository deductRepository;
    private MutableLiveData<Resource<String>> deduct;


    public DeDuctViewModel(@NonNull Application context) {
        super(context);
        deductRepository = new DeductRepository(context);
        deduct = new MutableLiveData<>();
    }

    public LiveData<Resource<String>> getAddDeduct(){
        return deduct;
    }

    public void addDeduct(AddDeduct addDeduct){
        deductRepository.AddDeduct(addDeduct, deduct);

    }

    public LiveData<Resource<ApiResponse<List<Deduct>>>> getDeduct(int id){
        return deductRepository.GetDeDuct(id);
    }
}
