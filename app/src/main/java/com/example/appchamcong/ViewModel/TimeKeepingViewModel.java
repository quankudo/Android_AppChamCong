package com.example.appchamcong.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Repository.TimekeepingRepository;
import com.example.appchamcong.Repository.UserRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.TimeKeepResponse;
import com.example.appchamcong.domain.TimeKeepingDetails;
import com.example.appchamcong.domain.TimekeepingReq;

import java.util.List;

public class TimeKeepingViewModel extends AndroidViewModel {
    private final TimekeepingRepository timekeepingRepository;

    public TimeKeepingViewModel(@NonNull Application context) {
        super(context);
        timekeepingRepository = new TimekeepingRepository(context);
    }

    public LiveData<Resource<ApiResponse<String>>> timeKeeping(TimekeepingReq req){
        return timekeepingRepository.timeKeeping(req);
    }

    public LiveData<Resource<ApiResponse<String>>> timeKeepingByOwner(TimekeepingReq req){
        return timekeepingRepository.timeKeepingByOwner(req);
    }

    public LiveData<Resource<ApiResponse<TimeKeepingDetails>>> getTimeKeepingDetails(int id){
        return timekeepingRepository.getTimeKeepingDetails(id);
    }

    public LiveData<Resource<ApiResponse<TimeKeepingDetails>>> getTimeKeepingDetailsByOwner(int idNhom, int idNhanVien){
        return timekeepingRepository.getTimeKeepingDetailsByOwner(idNhom, idNhanVien);
    }

    public LiveData<Resource<ApiResponse<List<TimeKeepResponse>>>> getTimeKeepings(int id){
        return timekeepingRepository.getTimeKeepings(id);
    }

    public LiveData<Resource<ApiResponse<List<TimeKeepResponse>>>> getTimeKeepingsByOwner(int idnv, int idNhom){
        return timekeepingRepository.getTimeKeepingsByOwner(idnv, idNhom);
    }
}
