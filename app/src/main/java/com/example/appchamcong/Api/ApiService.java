package com.example.appchamcong.Api;

import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.domain.AddDeduct;
import com.example.appchamcong.domain.Deduct;
import com.example.appchamcong.domain.JoinTeam;
import com.example.appchamcong.domain.MyInfo;
import com.example.appchamcong.domain.OverTime;
import com.example.appchamcong.domain.Person_Timekeeping;
import com.example.appchamcong.domain.Reward;
import com.example.appchamcong.domain.SalaryAdvance;
import com.example.appchamcong.domain.TimeKeepResponse;
import com.example.appchamcong.domain.TimeKeepingDetails;
import com.example.appchamcong.domain.Timekeeping;
import com.example.appchamcong.domain.TimekeepingReq;
import com.example.appchamcong.domain.User;
import com.example.appchamcong.domain.WorkDetails;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("Login")
    Call<String> login(@Body User user);

    @POST("users")
    Call<User> createUser(@Body User user);

    @GET("User/getMyInfo")
    Call<ApiResponse<MyInfo>> getMyInfo();

    @GET("Work/getWordPerson")
    Call<ApiResponse<List<Person_Timekeeping>>> getWorkPerson();

    @GET("Work/getWorkGroup")
    Call<ApiResponse<List<Person_Timekeeping>>> getWorkGroup();

    @GET("Work/getSubJoinWork/{id}")
    Call<ApiResponse<List<JoinTeam>>> getSubJoinWork(@Path("id") int id);

    @POST("Work/AddMember")
    Call<ApiResponse<String>> AddMember(@Query("code") String code);

    @POST("Work/AgreeJoinWork")
    Call<ApiResponse<String>> AgreeJoinWork(@Query("idNhom") int idNhom, @Query("idNhanVien") int idNhanVien, @Query("luong") double luong);

    @DELETE("Work/RefuseJoinWork")
    Call<ApiResponse<String>> RefuseJoinWork(@Query("idNhom") int idNhom, @Query("idNhanVien") int idNhanVien);

    @GET("Work/getEmployee/{id}")
    Call<ApiResponse<List<Timekeeping>>> getEmployeeTimekeeping(@Path("id") int id); //id cong viec

    @POST("Timekeeping")
    Call<ApiResponse<String>> timeKeeping(@Body TimekeepingReq request);

    @GET("Work/getWorkDetail/{id}")
    Call<ApiResponse<WorkDetails>> getWorkDetail(@Path("id") int id);

    @GET("Work/getWorkDetailByEmployee/{id}")
    Call<ApiResponse<WorkDetails>> getWorkDetailByEmployee(@Path("id") int id);

    @GET("Work/getWorkDetailByOwner")
    Call<ApiResponse<WorkDetails>> getWorkDetailByOwner(@Query("idnv") int idnv, @Query("idNhom") int idNhom);

    @GET("Timekeeping/getByIdctcv/{id}")
    Call<ApiResponse<List<TimeKeepResponse>>> getTimeKeepings(@Path("id") int id);

    @GET("Timekeeping/getByOwner")
    Call<ApiResponse<List<TimeKeepResponse>>> getTimeKeepingsByOwner(@Query("idnv") int idnv, @Query("idNhom") int idNhom);

    @POST("Timekeeping/AddByOwner")
    Call<ApiResponse<String>> timeKeepingByOwner(@Body TimekeepingReq request);

    @GET("Timekeeping/details/{id}")
    Call<ApiResponse<TimeKeepingDetails>> getTimeKeepingDetails(@Path("id") int id);

    @GET("Timekeeping/detailsByOwner")
    Call<ApiResponse<TimeKeepingDetails>> getTimeKeepingDetailsByOwner(@Query("idNhom") int idNhom, @Query("idNhanVien") int idNhanVien);

    @GET("Deduct/getDeDuctbyod{id}")
    Call <ApiResponse<List<Deduct>>> GetDeduct(@Path("id") int id);

    @GET("overTime/getDeDuctbyod{id}")
    Call <ApiResponse<List<OverTime>>> GetOverTime(@Path("id") int id);

    @GET("Deduct/getDeDuctbyod{id}")
    Call <ApiResponse<List<Reward>>> GetReward(@Path("id") int id);

    @GET("Deduct/getDeDuctbyod{id}")
    Call <ApiResponse<List<SalaryAdvance>>> GetSalaryAdvance(@Path("id") int id);

    @GET("Deduct/getDeDuctbyod{id}")
    Call <ApiResponse<String>> AddDeduct(@Body AddDeduct deduct);
}
