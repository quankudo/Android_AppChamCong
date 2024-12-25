package com.example.appchamcong.activity;

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
import com.example.appchamcong.domain.Reward;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddRewardActivity extends AppCompatActivity {
    TextView title;
    TextView btnClose, tvDate, btnPre, btnNext;
    TextView btnLeTet, btnDatCT, btnDiLai, btnAnUong, btnChuyenCan, btnKhac;
    ArrayList<TextView> listBtn;
    Button btn_luu;
    EditText et_price, editText;
    SimpleDateFormat sdf;
    Calendar cal;
    String reason = "Thưởng: dịp lễ, tết";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_reward);
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


        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                long price = Long.parseLong(et_price.getText().toString());
//                String date = tvDate.getText().toString();
//                String notes = editText.getText().toString();
//                RewardActivity.listReward.add(new Reward(price, date, notes, reason));
//                RewardActivity.adapter.notifyDataSetChanged();
//                RewardActivity.Update();
//                finish();
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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
        title.setText("Thưởng/Phụ cấp");
    }

    public void initMapping(){
        title = findViewById(R.id.title_center);
        btnClose = findViewById(R.id.title_close);
        btnLeTet = findViewById(R.id.btnLeTet);
        btnDiLai = findViewById(R.id.btnDiLai);
        btnKhac = findViewById(R.id.btnKhac);
        btnAnUong = findViewById(R.id.btnAnUong);
        btnChuyenCan = findViewById(R.id.btnChuyenCan);
        btnDatCT = findViewById(R.id.btnDatCT);
        btnNext = findViewById(R.id.btn_next);
        btnPre = findViewById(R.id.btn_pre);
        tvDate = findViewById(R.id.tvDate);
        btn_luu = findViewById(R.id.btn_luu);
        et_price = findViewById(R.id.et_price);
        editText = findViewById(R.id.editText);
        listBtn = new ArrayList<>(List.of(btnAnUong, btnChuyenCan, btnKhac, btnLeTet, btnDiLai, btnDatCT));
    }

    public void setBtnOnClick(TextView btn){
        btn.setBackgroundResource(R.drawable.custom_btn_blue);
        for (TextView item: listBtn) {
            if(item.getId() != btn.getId()){
                item.setBackgroundResource(R.drawable.border_outline_pink);
            }
        }
    }

    public void setTVOnclick(TextView tv) {
        reason = tv.getText().toString();
    }
}