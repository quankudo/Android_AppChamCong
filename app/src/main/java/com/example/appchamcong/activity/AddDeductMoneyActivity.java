package com.example.appchamcong.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.DeDuctViewModel;
import com.example.appchamcong.domain.AddDeduct;
import com.example.appchamcong.domain.Deduct;
import com.example.appchamcong.Utils.FormatDateTime;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddDeductMoneyActivity extends AppCompatActivity {
    TextView title;
    TextView btnClose;
    LinearLayout lnDiMuon;
    TextView btnDiMuon, btnKhongDat, btnNghiVang, btnBaoHiem, btnKhac;
    TextView tvDiMuon, btnNext, btnPre, tvDate;
    ArrayList<TextView> listBtn;
    int idLoai =1;
    private DeDuctViewModel deDuctViewModel;
    private ArrayList<Deduct> listDeduct;
    int lastID;
    AddDeduct addDeduct = new AddDeduct();
    SimpleDateFormat sdf;
    Button btn_luu;
    EditText tv_price, tv_minutes, editText;
    Calendar cal;
    String reason = "Di muon";

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

        deDuctViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(DeDuctViewModel.class);
        deDuctViewModel.getAddDeduct().observe(this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> apiResponseResource) {
                switch (apiResponseResource.status) {
                    case LOADING:
                        Toast.makeText(AddDeductMoneyActivity.this, "Adding...", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        Toast.makeText(AddDeductMoneyActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                        DeductMoneyActivity.list.add(addDeduct);
                        DeductMoneyActivity.adapter.notifyDataSetChanged();
                        DeductMoneyActivity.Update();
                        finish();
                        break;
                    case ERROR:
                        Toast.makeText(AddDeductMoneyActivity.this, "Error: " + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
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
                    setTVOnclick(item);
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
//                if(tv_minutes.getText().toString().equals("")) //truong hop khong phai ly do la di muon thi khong co attribute: phut.
//                    tv_minutes.setText("1");
//                int mn = Integer.parseInt(tv_minutes.getText().toString());
                String date = tvDate.getText().toString();
                Date date1 = FormatDateTime.convertStringToDate(date, "yyyy-MM-dd");
                String notes = editText.getText().toString();


                addDeduct.setLoaiTT(idLoai);
                addDeduct.setIdctcv(lastID);
                addDeduct.setSoTien(price);
                addDeduct.setGhiChu(notes);
                addDeduct.setNgayTao(date1);
                AddDeduct addDeduct = new AddDeduct(lastID, price, date1, notes, idLoai);
                deDuctViewModel.addDeduct(addDeduct);

//
//                finish();
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
    public void setBtnOnClick(TextView btn){
        btn.setBackgroundResource(R.drawable.custom_btn_blue);
        for (TextView item: listBtn) {
            if(item.getId() != btn.getId()){
                item.setBackgroundResource(R.drawable.border_outline_pink);
            }
        }
        if(btn.getId()==btnDiMuon.getId()){
            idLoai=1;
        }
        else if(btn.getId()==btnKhongDat.getId()){
            idLoai=2;
        }else  if(btn.getId()==btnNghiVang.getId()){
            idLoai=3;
        } else if (btn.getId()==btnBaoHiem.getId()) {
            idLoai=4;
        }else{
            idLoai=5;
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

    public void setTVOnclick(TextView tv) {
        reason = tv.getText().toString();
    }
}