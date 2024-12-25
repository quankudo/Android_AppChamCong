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
import com.example.appchamcong.ViewModel.DeDuctViewModel;
import com.example.appchamcong.adapter.MinusMoneyAdapter;
import com.example.appchamcong.domain.AddDeduct;
import com.example.appchamcong.domain.Deduct;

import java.util.ArrayList;
import java.util.List;

public class DeductMoneyActivity extends AppCompatActivity {
    TextView title;
    Button btnAdd;
    ImageButton btnClose;
    private DeDuctViewModel deDuctViewModel;
    public static LinearLayout ln_emptys;
    public static RecyclerView rec_trutien;
    public static MinusMoneyAdapter adapter;
    public static ArrayList<AddDeduct> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deduct_money);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = new ArrayList<com.example.appchamcong.domain.AddDeduct>();
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

        deDuctViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(DeDuctViewModel.class);

        deDuctViewModel.getDeduct(1).observe(DeductMoneyActivity.this, new Observer<Resource<ApiResponse<List<Deduct>>>>() {
            @Override
            public void onChanged(Resource<ApiResponse<List<Deduct>>> apiResponseResource) {
                switch (apiResponseResource.status){
                    case LOADING:
                        Toast.makeText(DeductMoneyActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        adapter = new MinusMoneyAdapter(apiResponseResource.data.getData(), DeductMoneyActivity.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DeductMoneyActivity.this);
                        rec_trutien.setLayoutManager(linearLayoutManager);
                        rec_trutien.setAdapter(adapter);
                        break;
                    case ERROR:
                        Toast.makeText(DeductMoneyActivity.this, "Error: " + apiResponseResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeductMoneyActivity.this, AddDeductMoneyActivity.class);
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
        if(list.size() > 0) {
            ln_emptys.setVisibility(View.GONE);
            rec_trutien.setVisibility(View.VISIBLE);

        }
        else {
            ln_emptys.setVisibility(View.VISIBLE);
            rec_trutien.setVisibility(View.GONE);
        }
    }

    public void initData(){
        title.setText("Quản lý trừ tiền");
    }

    public void initMapping(){
        title = findViewById(R.id.title_header);
        btnAdd = findViewById(R.id.btn_add_trutien);
        btnClose = findViewById(R.id.chevLeftClose);
        ln_emptys = findViewById(R.id.ln_emptys);
        rec_trutien = findViewById(R.id.rec_TruLuong);
    }
}