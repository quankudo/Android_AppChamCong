package com.example.appchamcong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.appchamcong.Api.ApiClient;
import com.example.appchamcong.R;
import com.example.appchamcong.Utils.ApiResponse;
import com.example.appchamcong.Utils.JwtUtils;
import com.example.appchamcong.Utils.Resource;
import com.example.appchamcong.ViewModel.SalarryAdvanceViewModel;
import com.example.appchamcong.ViewModel.UserViewModel;
import com.example.appchamcong.adapter.SalaryAdvanceAdapter;
import com.example.appchamcong.domain.SalaryAdvance;

import java.util.ArrayList;
import java.util.List;

public class SalaryAdvanceActivity extends AppCompatActivity {
    TextView title;
    Button btnAdd;
    ImageButton btnClose;
    private SalarryAdvanceViewModel salaryViewModel;
    public static LinearLayout ln_empty;
    public static RecyclerView rec_UngLuong;
    public static SalaryAdvanceAdapter adapter;
    public static ArrayList<SalaryAdvance> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salary_advance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = new ArrayList<>();
        initMapping();
        initData();

        initEvent();
        Update();


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Tạo hiệu ứng chuyển cảnh
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        salaryViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(SalarryAdvanceViewModel.class);

        salaryViewModel.getSalaryAdvance(1).observe(SalaryAdvanceActivity.this, new Observer<Resource<ApiResponse<List<SalaryAdvance>>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<List<SalaryAdvance>>> apiResponseResource) {
                switch (apiResponseResource.status){
                    case LOADING:
                        Toast.makeText(SalaryAdvanceActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        adapter = new SalaryAdvanceAdapter(apiResponseResource.data.getData(), SalaryAdvanceActivity.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SalaryAdvanceActivity.this);
                        rec_UngLuong.setLayoutManager(linearLayoutManager);
                        rec_UngLuong.setAdapter(adapter);
                        break;
                    case ERROR:
                        Toast.makeText(SalaryAdvanceActivity.this, "Error: " + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void initEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalaryAdvanceActivity.this, AddApplySalaryActivity.class);
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

    public void initData(){
        title.setText("Quản lý ứng lương");
    }

    public synchronized static void Update() {
        if(list.size() > 0) {
            ln_empty.setVisibility(View.GONE);
            rec_UngLuong.setVisibility(View.VISIBLE);

        }
        else {
            ln_empty.setVisibility(View.VISIBLE);
            rec_UngLuong.setVisibility(View.GONE);
        }
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnAdd = findViewById(R.id.btn_add_ungluong);
        btnClose = findViewById(R.id.chevLeftClose);
        ln_empty = findViewById(R.id.ln_empty);
        rec_UngLuong = findViewById(R.id.rec_UngLuong);
    }
}