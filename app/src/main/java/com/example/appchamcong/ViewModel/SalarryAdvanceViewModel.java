package com.example.appchamcong.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appchamcong.R;
import com.example.appchamcong.Repository.SalaryAdvanceRepository;
import com.example.appchamcong.Repository.WorkRepository;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.Person_Timekeeping;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.List;

public class SalarryAdvanceViewModel extends AndroidViewModel {
    private final SalaryAdvanceRepository salaryAdvanceRepository;

    public SalarryAdvanceViewModel(@NonNull Application context) {
        super(context);
        salaryAdvanceRepository = new SalaryAdvanceRepository(context);
    }

    public LiveData<Resource<ApiResponse<List<SalaryAdvance>>>> getSalaryAdvance(int id){
        return salaryAdvanceRepository.GetSalaryAdvance(id);
    }

}
