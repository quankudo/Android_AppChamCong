package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.OverTimeViewModel;
import com.example.appchamcong.ViewModel.RewardViewModel;
import com.example.appchamcong.adapter.OverTimeAdapter;
import com.example.appchamcong.adapter.RewardAdapter;
import com.example.appchamcong.domain.OverTime;
import com.example.appchamcong.domain.Reward;

import java.util.List;

public class OvertimeActivity extends AppCompatActivity {
    TextView title;
    Button btnAdd;
    ImageButton btnClose;
    public  static RecyclerView rec_TangCa;
    public  static OverTimeAdapter adapter;
    public static LinearLayout ln_emptys;
    public static List<OverTime> listOverTime;
    public  static OverTimeViewModel overTimeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_overtime);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initMapping();
        initData();
        initEvent();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        overTimeViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(OverTimeViewModel.class);

        overTimeViewModel.getOverTime(1).observe(OvertimeActivity.this, new Observer<Resource<ApiResponse<List<OverTime>>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<List<OverTime>>> apiResponseResource) {
                switch (apiResponseResource.status){
                    case LOADING:
                        Toast.makeText(OvertimeActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        listOverTime = apiResponseResource.data.getData();
                        Update();
                        adapter = new OverTimeAdapter(listOverTime, OvertimeActivity.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OvertimeActivity.this);
                        rec_TangCa.setLayoutManager(linearLayoutManager);
                        rec_TangCa.setAdapter(adapter);
                        break;
                    case ERROR:
                        Toast.makeText(OvertimeActivity.this, "Error: " + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void initEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OvertimeActivity.this, AddOvertimeActivity.class);
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

    public  synchronized static void Update() {
        if (listOverTime.size() > 0) {
            ln_emptys.setVisibility(View.GONE);
            rec_TangCa.setVisibility(View.VISIBLE);
        } else {
            ln_emptys.setVisibility(View.VISIBLE);
            rec_TangCa.setVisibility(View.GONE);
        }

    }
    public void initData(){
        title.setText("Quản lý tăng ca");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnAdd = findViewById(R.id.btn_add_ca);
        btnClose = findViewById(R.id.chevLeftClose);
        rec_TangCa = findViewById(R.id.rec_TangCa);
        ln_emptys = findViewById(R.id.ln_emptys);
    }
}