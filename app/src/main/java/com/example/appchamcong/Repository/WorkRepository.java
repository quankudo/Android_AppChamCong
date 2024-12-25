package com.example.appchamcong.Repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.Api.ApiService;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.domain.JoinTeam;
import com.example.appchamcong.domain.Person_Timekeeping;
import com.example.appchamcong.domain.Timekeeping;
import com.example.appchamcong.domain.WorkDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkRepository {

    private ApiService apiService;
    private Application context;

    public WorkRepository(Application context) {
        this.apiService = ApiClient.getClient(context).create(ApiService.class);
        this.context = context;
    }

    public MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkPerson() {
        MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getWorkPerson().enqueue(new Callback<ApiResponse<List<Person_Timekeeping>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Person_Timekeeping>>> call, Response<ApiResponse<List<Person_Timekeeping>>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Person_Timekeeping>>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> getWorkGroup() {
        MutableLiveData<Resource<ApiResponse<List<Person_Timekeeping>>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getWorkGroup().enqueue(new Callback<ApiResponse<List<Person_Timekeeping>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Person_Timekeeping>>> call, Response<ApiResponse<List<Person_Timekeeping>>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Person_Timekeeping>>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<List<Timekeeping>>>> getEmployeeTimekeeping(int id) {
        MutableLiveData<Resource<ApiResponse<List<Timekeeping>>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getEmployeeTimekeeping(id).enqueue(new Callback<ApiResponse<List<Timekeeping>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Timekeeping>>> call, Response<ApiResponse<List<Timekeeping>>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Timekeeping>>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<List<JoinTeam>>>> getSubJoinWork(int id) {
        MutableLiveData<Resource<ApiResponse<List<JoinTeam>>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getSubJoinWork(id).enqueue(new Callback<ApiResponse<List<JoinTeam>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<JoinTeam>>> call, Response<ApiResponse<List<JoinTeam>>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<JoinTeam>>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<String>>> addMember(String code) {
        MutableLiveData<Resource<ApiResponse<String>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.AddMember(code).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public void AgreeJoinWork(int idNhom, int idNhanVien, double luong, MutableLiveData<Resource<ApiResponse<String>>> work) {
        work.setValue(Resource.loading());

        apiService.AgreeJoinWork(idNhom, idNhanVien, luong).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
    }

    public void RefuseJoinWork(int idNhom, int idNhanVien, MutableLiveData<Resource<ApiResponse<String>>> work) {
        work.setValue(Resource.loading());

        apiService.RefuseJoinWork(idNhom, idNhanVien).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
    }

    public MutableLiveData<Resource<ApiResponse<WorkDetails>>> getWorkDetail(int id) {
        MutableLiveData<Resource<ApiResponse<WorkDetails>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getWorkDetail(id).enqueue(new Callback<ApiResponse<WorkDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<WorkDetails>> call, Response<ApiResponse<WorkDetails>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<WorkDetails>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<WorkDetails>>> getWorkDetailByOwner(int idnv, int idNhom) {
        MutableLiveData<Resource<ApiResponse<WorkDetails>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getWorkDetailByOwner(idnv, idNhom).enqueue(new Callback<ApiResponse<WorkDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<WorkDetails>> call, Response<ApiResponse<WorkDetails>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<WorkDetails>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }

    public MutableLiveData<Resource<ApiResponse<WorkDetails>>> getWorkDetailByEmployee(int idNhom) {
        MutableLiveData<Resource<ApiResponse<WorkDetails>>> work = new MutableLiveData<>();
        work.setValue(Resource.loading());

        apiService.getWorkDetailByEmployee(idNhom).enqueue(new Callback<ApiResponse<WorkDetails>>() {
            @Override
            public void onResponse(Call<ApiResponse<WorkDetails>> call, Response<ApiResponse<WorkDetails>> response) {
                if (response.isSuccessful()) {
                    work.postValue(Resource.success(response.body()));
                } else {
                    work.postValue(Resource.error("Error: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<WorkDetails>> call, Throwable t) {
                work.postValue(Resource.error("Failed to fetch data: " + t.getMessage()));
            }
        });
        return work;
    }
}
