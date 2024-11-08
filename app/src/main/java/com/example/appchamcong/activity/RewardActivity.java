package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.appchamcong.adapter.MinusMoneyAdapter;
import com.example.appchamcong.adapter.RewardAdapter;
import com.example.appchamcong.domain.MinusMoney;
import com.example.appchamcong.domain.Reward;

import java.util.ArrayList;

public class RewardActivity extends AppCompatActivity {
    TextView title, xemThongKe;
    Button btnAdd;
    ImageButton btnClose;
    ArrayList<Reward> list;
    public static LinearLayout ln_emptys;
    public static RecyclerView rcv_phucap;
    public static RewardAdapter adapter;
    public static ArrayList<Reward> listReward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reward);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listReward = new ArrayList<>();
        initMapping();
        initData();
        initEvent();
        adapter = new RewardAdapter(listReward, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_phucap.setLayoutManager(linearLayoutManager);
        rcv_phucap.setAdapter(adapter);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    private void initEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RewardActivity.this, AddRewardActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        xemThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RewardActivity.this, StatisticalRewardActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }

    public synchronized static void Update() {
        if(listReward.size() > 0) {
            ln_emptys.setVisibility(View.GONE);
            rcv_phucap.setVisibility(View.VISIBLE);

        }
        else {
            ln_emptys.setVisibility(View.VISIBLE);
            rcv_phucap.setVisibility(View.GONE);
        }
    }

    public void initData(){
        title.setText("Quản lý thưởng/phụ cấp");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnAdd = findViewById(R.id.btn_add_phucap);
        btnClose = findViewById(R.id.chevLeftClose);
        xemThongKe = findViewById(R.id.xemThongKe);
        rcv_phucap = findViewById(R.id.rcv_phucap);
        ln_emptys = findViewById(R.id.linearLayout9);
    }
}