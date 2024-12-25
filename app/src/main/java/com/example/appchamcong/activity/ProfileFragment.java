package com.example.appchamcong.activity;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.FormatDateTime;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.UserViewModel;
import com.example.appchamcong.domain.MyInfo;

public class ProfileFragment extends Fragment {
    LinearLayout thoit, tb, btnSaoChepMa, infoAccount, hotrokythuat, ln_update;
    ImageButton btnQRCode;
    TextView tv_update, btnSettings, contact, Username, Email, CreatedDate;
    private UserViewModel userViewModel;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        Username = v.findViewById(R.id.Username);
        Email = v.findViewById(R.id.Email);
        CreatedDate = v.findViewById(R.id.CreatedDate);

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(UserViewModel.class);

        userViewModel.getMyInfo().observe(getActivity(), new Observer<Resource<ApiResponse<MyInfo>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<MyInfo>> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        // Hiển thị trạng thái loading
                        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        Username.setText(apiResponseResource.data.getData().getHovaten());
                        Email.setText(apiResponseResource.data.getData().getEmail());
                        CreatedDate.setText(apiResponseResource.data.getData().getNgayTao());
                        break;
                    case ERROR:
                        // Hiển thị thông báo lỗi
                        Toast.makeText(getActivity(), "Error" + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        thoit = v.findViewById(R.id.btn_thoitiet);
        thoit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        tb = v.findViewById(R.id.btn_thongbao);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotifyManageActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnSaoChepMa = v.findViewById(R.id.btnSaoChepMa);
        btnSaoChepMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastShow();
            }
        });

        infoAccount = v.findViewById(R.id.infoAccount);
        infoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoAccountActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnQRCode = v.findViewById(R.id.QRCodeAccount);
        btnQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QRCodeOfMe.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        hotrokythuat = v.findViewById(R.id.hotrokythuat);
        hotrokythuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CustomerCareActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        tv_update = v.findViewById(R.id.tv_updates);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == 1001) {
                        Intent data = result.getData();
                        if (data != null) {
                            String returnedData = data.getStringExtra("update");
                            if(returnedData.equals("SUCCESS")){
                                ln_update = v.findViewById(R.id.ln_update);
                                ln_update.setBackgroundColor(Color.parseColor("#139E42"));
                                tv_update.setText("Tai khoan da duoc xac thuc");
                            }
                        }
                    }
                }
        );

        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateInfor.class);
                activityResultLauncher.launch(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });


        btnSettings = v.findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        contact = v.findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MemberActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        return v;
    }

    public void toastShow() {
        Toast toast = new Toast(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.toast_successful_layout, (ViewGroup) getActivity().findViewById(R.id.layout_toast_successful));
        TextView tv = v.findViewById(R.id.tvToast);
        tv.setText("Đã sao chép mã thành công");
        toast.setView(v);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

}