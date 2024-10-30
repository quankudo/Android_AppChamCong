package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;
import com.example.appchamcong.domain.MinusMoney;
import com.example.appchamcong.domain.SalaryAdvance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddDeductMoneyActivity extends AppCompatActivity {
    TextView title;
    TextView btnClose;
    LinearLayout lnDiMuon, btnDiMuon, btnKhongDat, btnNghiVang, btnBaoHiem, btnKhac;
    TextView tvDiMuon, btnNext, btnPre, tvDate;
    ArrayList<LinearLayout> listBtn;
    SimpleDateFormat sdf;
    Button btn_luu;
    EditText tv_price, tv_minutes, editText;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_deduct_money);
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

        listBtn.forEach(item ->{
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setBtnOnClick(item);
                }
            });
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

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long price = Long.parseLong(tv_price.getText().toString());
                int mn = Integer.parseInt(tv_minutes.getText().toString());
                String date = tvDate.getText().toString();
                String notes = editText.getText().toString();
                DeductMoneyActivity.list.add(new MinusMoney(price, "Đi muộn", date, mn, notes));
                DeductMoneyActivity.adapter.notifyDataSetChanged();
                DeductMoneyActivity.Update();
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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
        title.setText("Thêm mới ứng lương");
    }

    public void initMapping(){
        title = findViewById(R.id.title_center);
        btnClose = findViewById(R.id.title_close);
        lnDiMuon = findViewById(R.id.linearLayoutDiMuon);
        tvDiMuon = findViewById(R.id.textViewDiMuon);
        btnBaoHiem = findViewById(R.id.btnBaoHiem);
        btnKhac = findViewById(R.id.btnKhac);
        btnDiMuon = findViewById(R.id.btnLeTet);
        btnKhongDat = findViewById(R.id.btnKoDat);
        btnNghiVang = findViewById(R.id.btnDiLai);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPre);
        tvDate = findViewById(R.id.tvDate);
        btn_luu = findViewById(R.id.btn_luu);
        tv_price = findViewById(R.id.tv_price);
        tv_minutes = findViewById(R.id.tv_minutes);
        editText = findViewById(R.id.editText);
        listBtn = new ArrayList<>(List.of(btnDiMuon, btnBaoHiem, btnKhac, btnKhongDat, btnNghiVang));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setBtnOnClick(LinearLayout btn){
        btn.setBackgroundResource(R.drawable.custom_btn_blue);
        for (LinearLayout item: listBtn) {
            if(item.getId() != btn.getId()){
                item.setBackgroundResource(R.drawable.border_outline_pink);
            }
        }
        if(btn.getId()==btnDiMuon.getId()){
            tvDiMuon.setVisibility(View.VISIBLE);
            lnDiMuon.setVisibility(View.VISIBLE);
        }
        else {
            tvDiMuon.setVisibility(View.GONE);
            lnDiMuon.setVisibility(View.GONE);
        }
    }
}