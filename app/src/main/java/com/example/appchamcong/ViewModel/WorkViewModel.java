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
import com.example.appchamcong.domain.JoinTeam;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.Person_Timekeeping;
import com.example.appchamcong.domain.Timekeeping;
import com.example.appchamcong.domain.WorkDetails;

import java.util.List;

public class WorkViewModel extends AndroidViewModel {
    private final WorkRepository workRepository;
    private MutableLiveData<Resource<ApiResponse<String>>> agreeJoin;
    private MutableLiveData<Resource<ApiResponse<String>>> refuseJoin;

    public WorkViewModel(@NonNull Application context) {
        super(context);
        workRepository = new WorkRepository(context);
        agreeJoin = new MutableLiveData<>();
        refuseJoin = new MutableLiveData<>();
    }

    public LiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkPerson(){
        return workRepository.getWorkPerson();
    }

    public LiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkGroup(){
        return workRepository.getWorkGroup();
    }

    public LiveData<Resource<ApiResponse<List<JoinTeam>>>> getSubJoinWork(int id){
        return workRepository.getSubJoinWork(id);
    }

    public LiveData<Resource<ApiResponse<List<Timekeeping>>>> getEmployeeTimekeeping(int id){
        return workRepository.getEmployeeTimekeeping(id);
    }

    public LiveData<Resource<ApiResponse<String>>> AddMember(String code){
        return workRepository.addMember(code);
    }

    public LiveData<Resource<ApiResponse<WorkDetails>>> getWorkDetail(int id){
        return workRepository.getWorkDetail(id);
    }

    public LiveData<Resource<ApiResponse<WorkDetails>>> getWorkDetailByOwner(int idnv, int idNhom){
        return workRepository.getWorkDetailByOwner(idnv, idNhom);
    }

    public LiveData<Resource<ApiResponse<WorkDetails>>> getWorkDetailByEmployee(int idNhom){
        return workRepository.getWorkDetailByEmployee(idNhom);
    }

    public LiveData<Resource<ApiResponse<String>>> getAgreeJoin(){
        return agreeJoin;
    }

    public LiveData<Resource<ApiResponse<String>>> getRefuseJoin(){
        return refuseJoin;
    }

    public void RefuseJoinWork(int idNhom, int idNhanVien){
        workRepository.RefuseJoinWork(idNhom, idNhanVien, refuseJoin);
    }

    public void AgreeJoinWork(int idNhom, int idNhanVien, double luong){
        workRepository.AgreeJoinWork(idNhom, idNhanVien, luong, agreeJoin);
    }
}
