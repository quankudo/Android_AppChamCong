package com.example.appchamcong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class AddApplySalaryActivity extends AppCompatActivity {
    TextView title, tvDate, btnClose, btnPre, btnNext;
    SimpleDateFormat sdf;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_apply_salary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sdf = new SimpleDateFormat("E, dd/MM/yyyy");
        cal = Calendar.getInstance();
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
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
    }

    public void initData(){
        title.setText("Thêm mới ứng lương");
    }

    public void initMapping(){
        title = findViewById(R.id.title_center);
        btnClose = findViewById(R.id.title_close);
        btnPre = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);
        tvDate = findViewById(R.id.tv_date);
    }
}