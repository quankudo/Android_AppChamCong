package com.example.appchamcong.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchamcong.R;

public class StatisticalRewardActivity extends AppCompatActivity {
    TextView titleHeader;
    TextView type1, type2, type3, type4, type5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistical_deduct_money);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initMapping();
        initData();
    }

    public void initData(){
        titleHeader.setText("Thống kê");
        type1.setText("Thưởng: dịp lễ, tết");
        type2.setText("Đạt chỉ tiêu");
        type3.setText("Phụ cấp: đi lại");
        type4.setText("Phụ cấp: ăn uống");
        type5.setText("Chuyên cần");
    }

    public void initMapping(){
        titleHeader = findViewById(R.id.title_header);
        type1 = findViewById(R.id.textView51);
        type1 = findViewById(R.id.textView53);
        type1 = findViewById(R.id.textView55);
        type1 = findViewById(R.id.textView57);
        type1 = findViewById(R.id.textView59);
    }
}
