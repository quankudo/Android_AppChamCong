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
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchamcong.R;

public class ProfileFragment extends Fragment {
    LinearLayout thoit, tb, btnSaoChepMa, infoAccount, hotrokythuat, ln_update;
    ImageButton btnQRCode;
    TextView tv_update, btnSettings;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
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
                            }
                        }
                    }
                }
        );
        tv_update = v.findViewById(R.id.tv_updates);
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