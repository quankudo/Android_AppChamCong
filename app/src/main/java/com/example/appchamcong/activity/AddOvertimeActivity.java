package com.example.appchamcong.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.adapter.ChooseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddOvertimeActivity extends AppCompatActivity {
    int type = 1;
    TextView title;
    TextView btnClose, btnPre, btnNext, tvDate;
    ArrayList<String> tangcaList, gioTcaList;
    LinearLayout btnHeSo, btnGioTCa, btnTangCa, btnNhapTien, ln_Type1, ln_type2;
    SimpleDateFormat sdf;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_overtime);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sdf = new SimpleDateFormat("E, dd/MM/yyyy");
        cal = Calendar.getInstance();
        initMapping();
        initData();
        initEvent();
    }

    private void initEvent() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        btnHeSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(gioTcaList, "Chọn hệ số:");
            }
        });

        btnGioTCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(tangcaList, "Chọn loại tăng ca:");
            }
        });

        btnTangCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTangCa.setBackgroundResource(R.drawable.custom_btn_blue);
                btnNhapTien.setBackgroundResource(R.drawable.border_outline_pink);
                type = 1;
                ln_Type1.setVisibility(View.VISIBLE);
                ln_type2.setVisibility(View.GONE);
            }
        });

        btnNhapTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNhapTien.setBackgroundResource(R.drawable.custom_btn_blue);
                btnTangCa.setBackgroundResource(R.drawable.border_outline_pink);
                type = 2;
                ln_Type1.setVisibility(View.GONE);
                ln_type2.setVisibility(View.VISIBLE);
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDate.getText().equals("Hôm nay")){
                    cal = Calendar.getInstance();
                }
                cal.add(Calendar.DAY_OF_MONTH, -1);
                String yesterday = sdf.format(cal.getTime());
                tvDate.setText(yesterday);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDate.getText().equals("Hôm nay") || tvDate.getText().equals(sdf.format(Calendar.getInstance().getTime()))){

                }
                else{
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    String date = sdf.format(cal.getTime());
                    tvDate.setText(date);
                }
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    public void initData(){
        title.setText("Thêm mới tăng ca");
        tangcaList = new ArrayList<>(List.of("x1","x1.5","x2","x2.5","x3","x3.5","x4","x4.5","x5","x5.5","x6","x6.5","x7","x7.5","x8"));
        gioTcaList = new ArrayList<>(List.of( "30 phút = 5,000 đ",
                "1 giờ = 10,000 đ",
                "1.5 giờ = 15,000 đ",
                "2 giờ = 20,000 đ",
                "2.5 giờ = 25,000 đ",
                "3 giờ = 30,000 đ",
                "3.5 giờ = 35,000 đ",
                "4 giờ = 40,000 đ",
                "4.5 giờ = 45,000 đ",
                "5 giờ = 50,000 đ",
                "5.5 giờ = 55,000 đ",
                "6 giờ = 60,000 đ",
                "6.5 giờ = 65,000 đ",
                "7 giờ = 70,000 đ",
                "7.5 giờ = 75,000 đ",
                "8 giờ = 80,000 đ"));
    }

    public void initMapping(){
        title = findViewById(R.id.title_center);
        btnClose = findViewById(R.id.title_close);
        btnHeSo = findViewById(R.id.btnHeso);
        btnGioTCa = findViewById(R.id.btn_GioTangCa);
        btnTangCa = findViewById(R.id.lnTangCa);
        btnNhapTien = findViewById(R.id.lnNhapTien);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPre);
        tvDate = findViewById(R.id.tvDate);
        ln_Type1 = findViewById(R.id.ln_type1);
        ln_type2 = findViewById(R.id.ln_type2);
    }

    public void show(ArrayList list, String title){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_choose_coefficient);
        RecyclerView rcv = dialog.findViewById(R.id.rcv_choose);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(new ChooseAdapter(tangcaList));

        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        tvTitle.setText(title);

        ImageView cancel = dialog.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button btnComplete = dialog.findViewById(R.id.btnXong);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}